package com.springapp.controller;

import com.springapp.entity.Layer;

import com.springapp.entity.User;
import com.springapp.services.layer.LayerService;
import com.springapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    LayerService layerService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/json*")
    @ResponseBody
    public Layer getLayer(){
        Layer layer = new Layer(1,"layer 1");
        String d="";
        return layer;
    }

    @RequestMapping(value="/pup*")
    @ResponseBody
    public List<Layer> getObject(){
        layerService.add(new Layer(1,"Name1"));
        layerService.add(new Layer(2,"Name2"));
        layerService.add(new Layer(3,"Name3"));
        return layerService.getAll();
    }

    @RequestMapping(value = "user/save",method = RequestMethod.POST)
    @ResponseBody
    public boolean Save (User user){
        userService.save(user);
        return  true;
    }

    @RequestMapping(value = "user/all")
    @ResponseBody
    public Map<String,List<User>> GetAll (){
        Map<String,List<User>> users = new HashMap<>();
        users.put("users",userService.list());
        return users;
    }

    @RequestMapping(value = "user/get/{id}")
    public @ResponseBody User getUser(@PathVariable long id){
        return userService.get(id);
    }
}
