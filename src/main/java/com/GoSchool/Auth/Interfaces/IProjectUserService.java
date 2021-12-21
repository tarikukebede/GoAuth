package com.GoSchool.Auth.Interfaces;
import com.GoSchool.Auth.Models.ProjectUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjectUserService {
    ResponseEntity<ProjectUser> save(ProjectUser projectUser);
    ResponseEntity<ProjectUser> get(Long projectUserId);
    ResponseEntity<List<ProjectUser>> getAll();
    ResponseEntity<Long> delete(Long projectUserId);
}
