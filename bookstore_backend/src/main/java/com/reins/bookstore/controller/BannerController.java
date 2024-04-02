package com.reins.bookstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Banner;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.service.BannerService;
import com.reins.bookstore.utils.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.wrapBanner;
import static com.reins.bookstore.utils.Adapter.wrapFocus;

@RestController
@CrossOrigin
@RequestMapping("/Banner")
public class BannerController {
    BannerService bannerService;
    public BannerController(BannerService bannerService){
        this.bannerService = bannerService;
    }
    @RequestMapping("/get")
    public JSONObject getOne() {
        System.out.println("bannercontroller");

        int count = bannerService.getAll();

        Banner banner = bannerService.getOne(count);
        return wrapBanner(banner);
    }

    @PostMapping
    public JSONObject changeOne(@RequestBody JSONObject jsonObject
    )
    {
        String url = jsonObject.getString("url");
        int count = bannerService.getAll();
        Banner banner = new Banner();
        if (count==1){
            System.out.println("count:1");
            Banner b = new Banner();
            b.setId(2);
            b.setUrl(url);
            banner = bannerService.insertOne(b);
        }
        if(count ==2){
            System.out.println("count:2");
            Banner b1 = new Banner();
            b1.setId(2);
            b1.setUrl(url);
            banner = bannerService.updateOne(b1);
        }
        return wrapBanner(banner);
    }

    @DeleteMapping
    public Object deleteOneBanner()
    {
        int count = bannerService.getAll();
        if(count == 1){
            return new Message(-1, "Banner not found.");
        }else {
            if (count == 2) {
                if (!bannerService.deleteOne(2)) {
                    System.out.println("banner not found");
                    return new Message(-1, "banner not found.");
                } else {
                    System.out.println("OK");
                    return new Message(0, "OK");
                }
            }
            return new Message(0, "Banner count greater than 2");
        }
    }
}
