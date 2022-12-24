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


    @DeleteMapping("/user/{id}")
    public Map<String, Object> delete(@PathVariable("id") long id){
        Map<String, Object> response = new HashMap<>();
        if(adminService.deleteById(id) > 0) {
            response.put("result","SUCCESS");
        } else {
            response.put("result","FAIL");
            response.put("reason","일치하는 회원정보가 없습니다.");
        }
        return response;
    }
}
