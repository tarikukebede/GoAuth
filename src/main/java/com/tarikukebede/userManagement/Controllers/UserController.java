package com.tarikukebede.userManagement.Controllers;

import com.tarikukebede.userManagement.Dto.ReqDto.AddRoleToUserReqDto;
import com.tarikukebede.userManagement.Models.AppUser;
import com.tarikukebede.userManagement.Models.Role;
import com.tarikukebede.userManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getUsers(){
        System.out.println("all endPoint got called");
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser){
        System.out.println("add endPoint got called");
        return ResponseEntity.ok().body(userService.saveUser(appUser));
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        System.out.println("addRole endPoint got called");
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleToUserReqDto reqDto){
        userService.addRoleToUser(reqDto.getUsername(), reqDto.getRoleName());
        return ResponseEntity.ok().build();
    }

}
