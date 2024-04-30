package com.reins.bookstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Banner;

import com.reins.bookstore.service.BannerService;
import com.reins.bookstore.utils.Message;
import org.springframework.web.bind.annotation.*;



import static com.reins.bookstore.utils.Adapter.wrapBanner;
import static java.lang.Math.min;


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
        if (count ==1) return wrapBanner(null);
        Banner result = new Banner();
        Banner banner = new Banner();
        String url = "";
        for(int i=2;i<=count;i++){
            banner = bannerService.getOne(i);
            result.setId(2);
            url +=banner.getUrl();
        }
        result.setUrl(url);
        return wrapBanner(result);
    }

    @PostMapping
    public JSONObject changeOne(@RequestBody JSONObject jsonObject
    )
    {
        String url = jsonObject.getString("url");
        int count = bannerService.getAll();
        int len = url.length();
        Banner result = new Banner();
        String result_url = "";
        Banner banner = new Banner();
        if (count==1){
            System.out.println("count:1");
            for(int i =0;i<=len/20000;i++){
                Banner b = new Banner();
                b.setId(i+2);
                String url1 = url.substring(i*20000,min((i+1)*20000,len));
                b.setUrl(url1);
                System.out.println("id:"+i+2);
                System.out.println("len:"+(min((i+1)*20000,len)-i*20000));
                banner = bannerService.insertOne(i+2,b);
                result.setId(2);
                result_url +=banner.getUrl();
            }
            result.setUrl(result_url);

        }else{//已有
            bannerService.deleteall();

            for(int i =0;i<=len/20000;i++){
                Banner b = new Banner();
                b.setId(i+2);
                String url1 = url.substring(i*20000,min((i+1)*20000,len));
                b.setUrl(url1);
                System.out.println("id:"+i+2);
                System.out.println("len:"+(min((i+1)*20000,len)-i*20000));
                banner = bannerService.insertOne(i+2,b);
                result.setId(2);
                result_url +=banner.getUrl();
            }
            result.setUrl(result_url);
        }
         /*
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
        */

        return wrapBanner(result);
    }

    @DeleteMapping
    public Object deleteOneBanner()
    {
        int count = bannerService.getAll();
        if(count == 1){
            return new Message(-1, "Banner not found.");
        }else {

            if (!bannerService.deleteall()) {
                System.out.println("banner not found");
                return new Message(-1, "banner not found.");
            } else {
                System.out.println("OK");
                return new Message(0, "OK");
            }

        }
    }
}
