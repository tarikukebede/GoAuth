package com.tarikukebede.userManagement.Services;

import com.tarikukebede.userManagement.Models.AppUser;
import com.tarikukebede.userManagement.Models.Role;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
