package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;

import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.WxuserService;
import com.reins.bookstore.utils.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



import static com.reins.bookstore.utils.Adapter.wrapWxuser;




@RestController
@CrossOrigin
@RequestMapping("/Wxuser")
public class WxuserController {
    WxuserService wxuserService;


    @Autowired

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

    @RequestMapping("/{open_id}")
    public JSONObject getOneWxuesr(@PathVariable String open_id) {
        System.out.println("getoneWxuesr");
        System.out.println(open_id);
        Wxuser wxuser = wxuserService.getOneWxuser(open_id);
        return wrapWxuser(wxuser);
    }


    @RequestMapping("/getOpenId")
    public String wxcallback(String code){
        // 第二步：通过code换取网页授权access_token


        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + "wxd65360ede5ca176a" +
                "&secret=" + "6156b06666b1dafd8f62686711a01502" +
                "&js_code=" + code +
                "&grant_type=authorization_code";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(res.getBody());
        System.out.println(jsonObject);
        return jsonObject.get("openid").toString();
    }



    @PostMapping
    public JSONObject insertOneWxuser( @RequestBody JSONObject jsonObject
    ) {
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        String open_id = jsonObject.getString("open_id");
        System.out.println("open_id"+open_id);
        //去user表中找是否有该userid，有的话继续，没的话返回
        Wxuser wxuser = new Wxuser();
        wxuser.setName(name);
        wxuser.setAvatar(avatar);
        wxuser.setOpenid(open_id);
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
        String open_id = jsonObject.getString("open_id");
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        //去focus表中找id，有的话继续，没有的话返回
        Wxuser w = wxuserService.getOneWxuser(open_id);
        if (w == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapWxuser(null);
        }
        Wxuser wxuser = new Wxuser();
        wxuser.setOpenid(open_id);
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
    public Object deleteOneWxuser( @RequestParam(value = "open_id",required = false) int open_id
    ) {
        System.out.println("wxuserdeleteone");

        if (!wxuserService.deleteOneWxuser(open_id)) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
