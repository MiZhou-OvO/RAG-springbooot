package com.example.java.service.impl;

import com.example.java.entity.Address;
import com.example.java.entity.User;
import com.example.java.service.DemoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String simpleParam(String username, Integer age) {
        System.out.println(username + ":" + age);
        return "OK";
    }

    @Override
    public String simplePojo(User user) {
        System.out.println(user);
        return "OK";
    }

    @Override
    public String complexPojo(User user) {
        System.out.println(user);
        return "OK";
    }

    @Override
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    @Override
    public String listParam(List<String> hobby) {
        System.out.println(hobby);
        return "OK";
    }

    @Override
    public String dateParam(LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    @Override
    public String jsonParam(User user) {
        System.out.println(user);
        return "OK";
    }

    @Override
    public String pathParam(Integer id) {
        System.out.println(id);
        return "OK";
    }

    @Override
    public String pathParam2(Integer id, String name) {
        System.out.println(id);
        System.out.println(name);
        return "OK";
    }

    @Override
    public String get(Long id, String name) {
        System.out.printf("ID=%s, name=%s%n", id, name);
        return "GET request handled";
    }

    @Override
    public String save(Map<String, String> map) {
        System.out.println(map);
        return "POST request handled";
    }

    @Override
    public String update(Long id, Map<String, String> map) {
        System.out.printf("ID=%s, body=%s%n", id, map);
        return "PUT request handled";
    }

    @Override
    public String delete(Long id) {
        System.out.printf("Delete ID=%s%n", id);
        return String.format("DELETE request handled, removed ID=%s", id);
    }

    @Override
    public Address getAddr() {
        Address address = new Address();
        address.setProvince("Guangdong");
        address.setCity("Shenzhen");
        return address;
    }

    @Override
    public List<Address> listAddr() {
        List<Address> list = new ArrayList<>();

        Address first = new Address();
        first.setProvince("Guangdong");
        first.setCity("Shenzhen");

        Address second = new Address();
        second.setProvince("Zhejiang");
        second.setCity("Hangzhou");

        list.add(first);
        list.add(second);
        return list;
    }
}
