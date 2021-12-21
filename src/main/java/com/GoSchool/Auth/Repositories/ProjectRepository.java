package com.GoSchool.Auth.Repositories;

import com.GoSchool.Auth.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectName(String projectName);
    List<Project> findByOwnerId(Long ownerId);
}
