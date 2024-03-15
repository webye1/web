package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.FocusService;
import com.reins.bookstore.service.ManagerService;
import com.reins.bookstore.utils.Message;
import com.sun.javafx.collections.MappingChange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.reins.bookstore.utils.Adapter.*;

@RestController
@CrossOrigin
@RequestMapping("/Manager")
public class ManagerController {

    ManagerService managerService;
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @RequestMapping("/getall")
    public List<JSONObject> getAllManager() {
        System.out.println("controllermanager");
        List<Manager> managers = managerService.getAllManager();
        List<JSONObject> resData = new ArrayList<>();
        for (Manager t : managers) {
            resData.add(wrapManager(t));
        }
        for (JSONObject j : resData) {
            System.out.println(j);
        }
        System.out.println(resData);
        return resData;
    }

    @RequestMapping("/{id}")
    public JSONObject getOneManager(@PathVariable Integer id) {
        System.out.println("getonemanager");
        System.out.println(id);
        Manager manager = managerService.getOneManager(id);
        return wrapManager(manager);
    }

    @PostMapping("/find")
    public JSONObject findManager(@RequestBody JSONObject jsonObject
    ) {
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        System.out.println("name:"+name+" password:"+password);
        //去user表中找是否有该userid，有的话继续，没的话返回
        Manager m = managerService.findOne(name,password);
        System.out.println(m);
        if (m != null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapManager(m);
        }

        return wrapManager(m);
    }

    @PostMapping
    public JSONObject insertOneManager(@RequestBody JSONObject jsonObject
    ) {
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Integer type = jsonObject.getInteger("type");
        //去user表中找是否有该userid，有的话继续，没的话返回
        Manager m = managerService.getOneManager(name);
        System.out.println(m);
        if (m != null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("username exist");
            return wrapManager(null);
        }
        Manager manager = new Manager();
        manager.setName(name);
        manager.setPassword(password);
        if (type<3 && type >=0){
            manager.setType(type);
        }
        else{
            return null;
        }
        System.out.println("managerpostinsert");
        Manager manager1 = managerService.insertOneManager(manager);
        System.out.println(manager1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapManager(manager1);
    }

    @PutMapping
    public JSONObject updateOneManager(@RequestBody JSONObject jsonObject
    ) {
        //Integer id = jsonObject.getInteger("id");
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Integer type = jsonObject.getInteger("type");
        //去focus表中找id，有的话继续，没有的话返回
        Manager m = managerService.getOneManager(name);

        if (m == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapManager(null);
        }
        Manager manager = new Manager();
        manager.setManagerId(m.getManagerId());
        manager.setName(name);
        if (password == null ){
            manager.setPassword(m.getPassword());
        }else{
            manager.setPassword(password);
        }
        if (type == null ){
            manager.setType(m.getType());
        } else if (type<3 && type >=0) {
            manager.setType(type);
        }else {
            return null;
        }

        System.out.println("managerpostupdate");
        Manager manager1 = managerService.updateOneManager(manager);
        System.out.println(manager1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapManager(manager1);
    }

    @DeleteMapping
    public Object deleteManager(@RequestParam("username") String name
    )
    {

        System.out.println("name"+name);

        Manager m = managerService.getOneManager(name);

        if (m == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapManager(null);
        }
        if (!managerService.deleteOneManager(m.getManagerId())) {
            System.out.println("not found");
            JSONObject json = new JSONObject();
            json.put("message","User not found.");
            return json;
        } else {
            System.out.println("OK");
            JSONObject json = new JSONObject();
            json.put("message","OK");
            return json;

        }
    }

}
