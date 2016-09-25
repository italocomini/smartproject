package io.italocomini.smartproject.controllers;

import io.italocomini.smartproject.exceptions.ResourceNotFoundException;
import io.italocomini.smartproject.models.Project;
import io.italocomini.smartproject.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/projects")
class ProjectController {

    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<Project>> index(Pageable pageable) {

        Page<Project> projects = repository.findAll(pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Project project) {

        project = repository.save(project);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(project.getId())
                .toUri();
        responseHeaders.setLocation(uri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{projectId}")
    public ResponseEntity<Project> get(@PathVariable Long projectId) {

        Project project = repository.findOne(projectId);

        if (project == null) {
            throw new ResourceNotFoundException();
        }

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<Long> delete(@PathVariable Long projectId) {

        if (null == repository.findOne(projectId)) {
            throw new ResourceNotFoundException();
        }

        repository.delete(projectId);
        return new ResponseEntity<>(projectId, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> update(@PathVariable Long projectId, @RequestBody Project project) {

        Project currentProject = repository.findOne(projectId);

        if (null == currentProject) {
            throw new ResourceNotFoundException();
        }

        currentProject.setName(project.getName());
        Project projectSaved = repository.save(currentProject);

        return new ResponseEntity<>(projectSaved, HttpStatus.OK);
    }


}
