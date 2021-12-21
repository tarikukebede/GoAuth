package com.GoSchool.Auth.Services;
import com.GoSchool.Auth.Interfaces.IProjectService;
import com.GoSchool.Auth.Models.Project;
import com.GoSchool.Auth.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseEntity<Project> addProject(Project project) {
         Project result = projectRepository.save(project);
         if(result.getId() != null){
             return ResponseEntity.ok().body(result);
         }
         return ResponseEntity.internalServerError().build();

    }

    @Override
    public ResponseEntity<Project> getProject(Long projectId) {
        if(projectId != null){
            Optional<Project> result = projectRepository.findById(projectId);
            return result.map(project -> ResponseEntity.ok().body(project)).orElseGet(() -> ResponseEntity.notFound().build());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Long> deleteProject(Project project) {
        projectRepository.delete(project);
        return ResponseEntity.ok().body(project.getId());
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects(Long ownerId) {
        List<Project> projects = projectRepository.findByOwnerId(ownerId);
        if(projects != null && projects.size() > 0){
            return ResponseEntity.ok().body(projects);
        }
        return ResponseEntity.notFound().build();
    }
}
