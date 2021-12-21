package com.GoSchool.Auth.Controllers;
import com.GoSchool.Auth.Models.Project;
import com.GoSchool.Auth.Services.ProjectService;
import com.GoSchool.Auth.Util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @GetMapping
    public ResponseEntity<Project> getProject(@RequestParam(Params.PROJECT_ID) Long projectId){
        return projectService.getProject(projectId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(Params.USER_ID) Long userId){
        return projectService.getAllProjects(userId);
    }
}
