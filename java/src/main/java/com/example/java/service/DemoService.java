package com.example.java.service;

import com.example.java.entity.Address;
import com.example.java.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DemoService {

    String hello();

    String simpleParam(String username, Integer age);

    String simplePojo(User user);

    String complexPojo(User user);

    String arrayParam(String[] hobby);

    String listParam(List<String> hobby);

    String dateParam(LocalDateTime updateTime);

    String jsonParam(User user);

    String pathParam(Integer id);

    String pathParam2(Integer id, String name);

    String get(Long id, String name);

    String save(Map<String, String> map);

    String update(Long id, Map<String, String> map);

    String delete(Long id);

    Address getAddr();

    List<Address> listAddr();
}
