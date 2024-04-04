package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;

import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.WxuserService;
import com.reins.bookstore.utils.Message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.wrapWxuser;

@RestController
@CrossOrigin
@RequestMapping("/Wxuser")
public class WxuserController {
    WxuserService wxuserService;
    public WxuserController(WxuserService wxuserservice){
        this.wxuserService = wxuserservice;
    }
    @RequestMapping("/getall")
    public List<JSONObject> getAllWxuser() {
        System.out.println("controllerWxuser");
        List<Wxuser> wxusers = wxuserService.getAllWxuser();
        List<JSONObject> resData = new ArrayList<>();
        for (Wxuser t : wxusers) {
            resData.add(wrapWxuser(t));
        }
        for (JSONObject j : resData) {
            System.out.println(j);
        }
        System.out.println(resData);
        return resData;
    }

    @RequestMapping("/{id}")
    public JSONObject getOneWxuesr(@PathVariable Integer union_id) {
        System.out.println("getoneWxuesr");
        System.out.println(union_id);
        Wxuser wxuser = wxuserService.getOneWxuser(union_id);
        return wrapWxuser(wxuser);
    }

    @PostMapping
    public JSONObject insertOneWxuser( @RequestBody JSONObject jsonObject
    ) {
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        Integer union_id = jsonObject.getInteger("union_id");
        //去user表中找是否有该userid，有的话继续，没的话返回
        Wxuser wxuser = new Wxuser();
        wxuser.setName(name);
        wxuser.setAvatar(avatar);
        wxuser.setUnionid(union_id);
        System.out.println("wxuserpostinsert");
        Wxuser wxuser1 = wxuserService.insertOneWxuser(wxuser);
        System.out.println(wxuser1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapWxuser(wxuser1);
    }

    @PutMapping
    public JSONObject updateOneWxuser( @RequestBody JSONObject jsonObject
    ) {
        Integer union_id = jsonObject.getInteger("union_id");
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        //去focus表中找id，有的话继续，没有的话返回
        Wxuser w = wxuserService.getOneWxuser(union_id);
        if (w == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapWxuser(null);
        }
        Wxuser wxuser = new Wxuser();
        wxuser.setUnionid(union_id);
        if(name ==null){
            wxuser.setName(w.getName());
        }
       else{
            wxuser.setName(name);
        }
       if(avatar == null){
           wxuser.setAvatar(w.getAvatar());
       }else{
           wxuser.setAvatar(avatar);
       }

        System.out.println("wxuserpostupdate");
        Wxuser wxuser1 = wxuserService.updateOneWxuser(wxuser);
        System.out.println(wxuser1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapWxuser(wxuser1);
    }

    @DeleteMapping
    public Object deleteOneWxuser( @RequestParam(value = "union_id",required = false) int union_id
    ) {
        System.out.println("wxuserdeleteone");

        if (!wxuserService.deleteOneWxuser(union_id)) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
