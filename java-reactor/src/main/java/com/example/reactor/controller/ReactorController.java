package com.example.reactor.controller;


import com.example.reactor.moudle.entity.User;
import com.example.reactor.service.ReactorService;
import com.example.reactor.utils.WrapMapper;
import com.example.reactor.utils.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/reactor")
public class ReactorController {


    @Autowired
    private ReactorService reactorService;


    @PostMapping("/user")
    public Wrapper saveUser(@Validated @RequestBody User user) {


        List<String> list = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        list2.removeIf(u -> u != null || "".equals(u));
        return WrapMapper.ok(user);
    }
}
