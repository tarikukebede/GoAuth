package com.GoSchool.Auth.Interfaces;

import com.GoSchool.Auth.Models.AppUser;
import com.GoSchool.Auth.Models.Role;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
