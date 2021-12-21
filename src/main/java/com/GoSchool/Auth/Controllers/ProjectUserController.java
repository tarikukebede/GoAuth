package com.GoSchool.Auth.Controllers;

import com.GoSchool.Auth.Models.ProjectUser;
import com.GoSchool.Auth.Services.ProjectUserService;
import com.GoSchool.Auth.Util.Params;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_users")
public class ProjectUserController {

    private ProjectUserService projectUserService;

    @Autowired
    public ProjectUserController(ProjectUserService projectUserService){
        this.projectUserService = projectUserService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProjectUser> add(ProjectUser projectUser){
        return projectUserService.save(projectUser);
    }


    @GetMapping
    public ResponseEntity<ProjectUser> get(@RequestParam(Params.PROJECT_USER_ID) Long projectUserId){
        return projectUserService.get(projectUserId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectUser>> getAll(){
        return projectUserService.getAll();
    }

    @DeleteMapping
    public ResponseEntity<Long> delete(@RequestParam(Params.PROJECT_USER_ID)Long projectUserId){
        return projectUserService.delete(projectUserId);
    }

}
