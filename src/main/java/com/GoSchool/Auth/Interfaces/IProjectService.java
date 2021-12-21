package com.GoSchool.Auth.Interfaces;

import com.GoSchool.Auth.Models.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjectService {
    ResponseEntity<Project> addProject(Project project);
    ResponseEntity<Project> getProject(Long projectId);
    ResponseEntity<Long> deleteProject(Project project);
    ResponseEntity<List<Project>> getAllProjects(Long ownerId);
}
