package com.reins.bookstore.controller;
import com.reins.bookstore.entity.Focus;


import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.FocusService;


import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.WxuserService;
import com.reins.bookstore.utils.Message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.*;

@RestController
@CrossOrigin
@RequestMapping("/Focus")
public class FocusController {
    FocusService focusService;
    WxuserService wxuserService;
    ProductService productService;
    public FocusController(FocusService focusService,WxuserService wxuserService,ProductService productService) {
        this.focusService = focusService;
        this.wxuserService = wxuserService;
        this.productService = productService;
    }
    @RequestMapping("/getall")
    public List<JSONObject> getAllFocus() {
        System.out.println("focuscontroller");
        List<Focus> focus = focusService.getAllFocus();
        List<JSONObject> resData = new ArrayList<>();
        for (Focus t : focus) {
            System.out.println("Focus:"+t.getUnionid());
            System.out.println("Focus:"+t.getTrackingnum());
            resData.add(wrapFocus(t));
        }
        for (JSONObject j : resData) {
            System.out.println(j);
        }
        System.out.println(resData);
        return resData;
    }
    @RequestMapping("/{id}")
    public JSONObject getOneFocus(@PathVariable Integer focus_id) {
        System.out.println("getone");
        System.out.println(focus_id);
        Focus focus = focusService.getOneFocus(focus_id);
        return wrapFocus(focus);
    }

    @RequestMapping("/union")
    public List<JSONObject> getUnionFocus(@PathVariable Integer union_id) {
        System.out.println("getunion");
        System.out.println(union_id);
        List<Focus> focuslist = focusService.getUnionFocus(union_id);

        List<JSONObject> result = new ArrayList<>();

        for(Focus f:focuslist){
            result.add(wrapFocus(f));
        }
        return result;
    }
    @PostMapping
    public JSONObject insertOneFocus(@RequestBody JSONObject jsonObject
                                            ) {
        //去user表中找是否有该userid，有的话继续，没的话返回
        Integer union_id = jsonObject.getInteger("union_id");
        String trackingnum = jsonObject.getString("tracking_num");
        String addition = jsonObject.getString("addition");
        Wxuser wxuser = wxuserService.getOneWxuser(union_id);
        System.out.println(wxuser);
        if (wxuser == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapWxuser(wxuser);
        }

        Product product = productService.getOneProduct(trackingnum);
        if (product == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapProduct(null);
        }

        Focus f = focusService.getOneFocus(union_id,trackingnum);
        if (f != null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }


        Focus focus = new Focus();
        focus.setUnionid(union_id);
        focus.setTrackingnum(trackingnum);
        focus.setAddition(addition);
        System.out.println("post");
        Focus focus1 = focusService.insertOneFocus(focus);
        System.out.println(focus1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapFocus(focus1);
    }

    @PutMapping
    public JSONObject updateOneFocus(@RequestBody JSONObject jsonObject
    ) {
        //去focus表中找id，有的话继续，没有的话返回
        Integer union_id = jsonObject.getInteger("union_id");
        String trackingnum = jsonObject.getString("tracking_number");
        String addition = jsonObject.getString("addition");
        Focus f = focusService.getOneFocus(union_id,trackingnum);
        if (f == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }
        Focus focus = new Focus();
        focus.setFocusid(f.getFocusid());
        focus.setUnionid(f.getUnionid());
        focus.setTrackingnum(f.getTrackingnum());
        focus.setAddition(addition);


        System.out.println("post");
        Focus focus1 = focusService.updateOneFocus(focus);
        System.out.println(focus1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapFocus(focus1);
    }

    @DeleteMapping
    public Object deleteOneFocus(@RequestParam(value = "union_id",required = false) int union_id,
                                 @RequestParam(value = "tracking_number",required = false) String tracking_num
    ) {
        System.out.println("deleteone");
        Focus f = focusService.getOneFocus(union_id,tracking_num);
        if (f == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }

        if (!focusService.deleteOneFocus(f.getFocusid())) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
