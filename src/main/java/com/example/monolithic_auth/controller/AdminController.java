package com.example.monolithic_auth.controller;

import com.example.monolithic_auth.domain.Users;
import com.example.monolithic_auth.service.AdminService;
import com.example.monolithic_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public Map<String, Object> finAllUser(){
        Map<String, Object>response = new HashMap<>();

        response.put("users",adminService.getUserList());
        return response;
    }

    @PutMapping
    public long updateUser(@RequestBody Users newUser){
        long res = adminService.updateUser(newUser);
        return res;
    }
}
