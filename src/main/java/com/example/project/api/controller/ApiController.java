package com.example.project.api.controller;

import com.example.project.api.dto.*;
import com.example.project.service.AllService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;

@RestController
public class ApiController {
    private final AllService allService;
    private static final Logger logger = LogManager.getLogger(ApiController.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    public ApiController(AllService allService) {
        this.allService = allService;
    }

    @GetMapping("/login")
    UserDto login(@RequestParam String login, @RequestParam String password) {
        logger.info(resourceBundle.getString("login"));
        return allService.getUser(login, password);
    }

    @PostMapping("/createUser")
    void createUser(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
        logger.info(resourceBundle.getString("createUser"));
        allService.save(new UserDto(login, password, email, 0));
    }

    @PostMapping("/createClass")
    void createClass(@RequestParam String title, @RequestParam String room, @RequestParam List<String> users) {
        logger.info(resourceBundle.getString("createClass"));
        allService.saveClass(title, room, users);
    }

    @GetMapping("/getClasses")
    List<ClassDto> getClasses() {
        logger.info(resourceBundle.getString("getClasses"));
        return allService.getClasses();
    }

    @PostMapping("/deleteClass")
    void deleteClass(@RequestParam String className) {
        logger.info(resourceBundle.getString("deleteClass"));
        allService.deleteClass(className);
    }

    @PostMapping("/updateClass")
    void updateClass(@RequestParam String oldClassName, @RequestParam String newClassName) {
        logger.info(resourceBundle.getString("updateClass"));
        allService.updateClass(oldClassName, newClassName);
    }

    @GetMapping("/getUsersEmail")
    List<String> getUsersEmail() {
        logger.info(resourceBundle.getString("getUsersEmail"));
        return allService.getUsersEmail();
    }

    @GetMapping("/getUsers")
    List<LoginEmailDto> getUsers() {
        logger.info(resourceBundle.getString("getUsers"));
        return allService.getUsers();
    }

    @GetMapping("/getLectors")
    List<String> getLectors() {
        logger.info(resourceBundle.getString("getLectors"));
        return allService.getLectors();
    }

    @GetMapping("/getRooms")
    List<String> getRooms() {
        logger.info(resourceBundle.getString("getRooms"));
        return allService.getRooms();
    }

    @PostMapping("/updateUser")
    void updateUser(@RequestParam String login, @RequestParam String email, @RequestParam String oldEmail) {
        logger.info(resourceBundle.getString("updateUser"));
        allService.updateUser(login, email, oldEmail);
    }

    @GetMapping("/updatePassword")
    String updateUser(@RequestParam String login, @RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        logger.info(resourceBundle.getString("updateUser"));
        return allService.updateUser(login, email, oldPassword, newPassword);
    }

    @PostMapping("/deleteUser")
    void deleteUser(@RequestParam String email) {
        logger.info(resourceBundle.getString("deleteUser"));
        allService.deleteUser(email);
    }

    @GetMapping("/getNews")
    List<NewsDto> getNews(){
        return allService.getNews();
    }

    @GetMapping("/getEntries")
    List<EntryDto> getEntries(@RequestParam String email){
        return allService.getEntries(email);
    }
}
