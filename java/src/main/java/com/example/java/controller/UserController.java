package com.example.java.controller;

import com.example.java.entity.User;
import com.example.java.pojo.Result;
import com.example.java.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Result list() {
        List<User> userList = userService.list();
        return Result.success(userList);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("user not found");
        }
        return Result.success(user);
    }

    @PostMapping
    public Result save(@RequestBody User user) {
        boolean saved = userService.save(user);
        if (!saved) {
            return Result.error("save failed");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.error("id can not be null");
        }
        boolean updated = userService.updateById(user);
        if (!updated) {
            return Result.error("update failed");
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean removed = userService.removeById(id);
        if (!removed) {
            return Result.error("delete failed");
        }
        return Result.success();
    }
}
