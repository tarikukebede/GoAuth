package com.GoSchool.Auth.Services;

import com.GoSchool.Auth.Interfaces.IProjectUserService;
import com.GoSchool.Auth.Models.ProjectUser;
import com.GoSchool.Auth.Repositories.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectUserService implements IProjectUserService {

    private final ProjectUserRepository projectUserRepository;

    @Autowired
    public ProjectUserService(ProjectUserRepository projectUserRepository){
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public ResponseEntity<ProjectUser> save(ProjectUser projectUser) {
        ProjectUser result = projectUserRepository.save(projectUser);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<ProjectUser> get(Long projectUserId) {
        ProjectUser result = projectUserRepository.getById(projectUserId);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<List<ProjectUser>> getAll() {
        List<ProjectUser> projectUsers = projectUserRepository.findAll();
        return ResponseEntity.ok().body(projectUsers);
    }

    @Override
    public ResponseEntity<Long> delete(Long projectUserId) {
        projectUserRepository.deleteById(projectUserId);
        return ResponseEntity.ok(projectUserId);
    }
}
