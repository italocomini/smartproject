package io.italocomini.smartproject.controllers;

import io.italocomini.smartproject.exceptions.ResourceNotFoundException;
import io.italocomini.smartproject.models.Project;
import io.italocomini.smartproject.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
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
    public ResponseEntity<?> get(@PathVariable Long projectId) {

        Assert.notNull(projectId, "Project id can not be null");

        Project project = repository.findOne(projectId);

        if (project == null) {
            throw new ResourceNotFoundException();
        }

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
