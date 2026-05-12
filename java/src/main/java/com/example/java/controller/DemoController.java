package com.example.java.controller;

import com.example.java.entity.Address;
import com.example.java.entity.User;
import com.example.java.service.DemoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return demoService.hello();
    }

    @RequestMapping("/simpleParam")
    public String simpleParam(@RequestParam(name = "name", required = false) String username, Integer age) {
        return demoService.simpleParam(username, age);
    }

    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        return demoService.simplePojo(user);
    }

    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        return demoService.complexPojo(user);
    }

    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        return demoService.arrayParam(hobby);
    }

    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        return demoService.listParam(hobby);
    }

    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        return demoService.dateParam(updateTime);
    }

    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        return demoService.jsonParam(user);
    }

    @RequestMapping("/path/{id}")
    public String pathParam(@PathVariable Integer id) {
        return demoService.pathParam(id);
    }

    @RequestMapping("/path/{id}/{name}")
    public String pathParam2(@PathVariable Integer id, @PathVariable String name) {
        return demoService.pathParam2(id, name);
    }

    @GetMapping("/get")
    public String get(@RequestParam Long id, @RequestParam String name) {
        return demoService.get(id, name);
    }

    @PostMapping("/post")
    public String save(@RequestBody Map<String, String> map) {
        return demoService.save(map);
    }

    @PutMapping("/put/{id}")
    public String update(@PathVariable Long id, @RequestBody Map<String, String> map) {
        return demoService.update(id, map);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return demoService.delete(id);
    }

    @RequestMapping("/getAddr")
    public Address getAddr() {
        return demoService.getAddr();
    }

    @RequestMapping("/listAddr")
    public List<Address> listAddr() {
        return demoService.listAddr();
    }
}
