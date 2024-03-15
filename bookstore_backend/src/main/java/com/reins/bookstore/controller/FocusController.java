package com.reins.bookstore.controller;
import com.reins.bookstore.entity.Focus;


import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.FocusService;


import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.WxuserService;
import com.reins.bookstore.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            System.out.println("Focus:"+t.getUserid());
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
    public JSONObject getOneFocus(@PathVariable Integer id) {
        System.out.println("getone");
        System.out.println(id);
        Focus focus = focusService.getOneFocus(id);
        return wrapFocus(focus);
    }
    @PostMapping
    public JSONObject insertOneFocus(@RequestBody JSONObject jsonObject
                                            ) {
        //去user表中找是否有该userid，有的话继续，没的话返回
        Integer userid = jsonObject.getInteger("userid");
        String trackingnum = jsonObject.getString("tracking_num");
        Wxuser wxuser = wxuserService.getOneWxuser(userid);
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
        Focus f = focusService.getOneFocus(userid,trackingnum);
        if (f != null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }
        Focus focus = new Focus();
        focus.setUserid(userid);
        focus.setTrackingnum(trackingnum);
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
        Integer focusid = jsonObject.getInteger("focusid");
        String trackingnum = jsonObject.getString("tracking_number");
        Focus f = focusService.getOneFocus(focusid);
        if (f == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }
        Focus focus = new Focus();
        focus.setId(focusid);
        focus.setUserid(f.getUserid());
        focus.setTrackingnum(trackingnum);
        System.out.println("post");
        Focus focus1 = focusService.updateOneFocus(focus);
        System.out.println(focus1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapFocus(focus1);
    }

    @DeleteMapping
    public Object deleteOneFocus(@RequestParam(value = "userid",required = false) int userid,
                                 @RequestParam(value = "tracking_number",required = false) String tracking_num
    ) {
        System.out.println("deleteone");
        Focus f = focusService.getOneFocus(userid,tracking_num);
        if (f == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapFocus(null);
        }

        if (!focusService.deleteOneFocus(f.getId())) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
