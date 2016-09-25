package io.italocomini.smartproject.controllers;


import io.italocomini.smartproject.IntegrationTest;
import io.italocomini.smartproject.models.Project;
import io.italocomini.smartproject.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ProjectControllerTest extends IntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProjectRepository repository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testUnauthorizedAccess() {
        ResponseEntity<Project> entity = getRestTemplate()
                .getForEntity("/v1/projects/{id}", Project.class, 1);

        assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testListProject() throws Exception {
        mockMvc.perform(get("/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content[0].name").value("SmartProject"));
    }

    @Test
    public void testGetByIdProject() throws Exception {

        Project project = repository.findAll().iterator().next();

        mockMvc.perform(get("/v1/projects/{id}", project.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(project.getName()));
    }

    @Test
    public void testGetResourceNotFound() throws Exception {
        mockMvc.perform(get("/v1/projects/{id}", 0))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testCreateProject() throws Exception {
        mockMvc.perform(post("/v1/projects")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"name\": \"Eclipse\" }")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProject() throws Exception {

        Project project = repository.findAll().iterator().next();

        String json = "{ \"id\": \"" + project.getId() + "\", \"name\": \"" + project.getName().concat(" Updated") + "\" }";

        mockMvc.perform(put("/v1/projects/{id}", project.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProjectNotFound() throws Exception {

        String json = "{ \"id\": \"0\", \"name\": \"P0\" }";

        mockMvc.perform(put("/v1/projects/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteProject() throws Exception {

        Project project = repository.findAll().iterator().next();

        mockMvc.perform(delete("/v1/projects/{id}", project.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteProjectNotFound() throws Exception {

        mockMvc.perform(delete("/v1/projects/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
}
