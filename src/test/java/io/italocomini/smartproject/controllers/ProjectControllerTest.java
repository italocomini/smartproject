package io.italocomini.smartproject.controllers;


import io.italocomini.smartproject.IntegrationTest;
import io.italocomini.smartproject.models.Project;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectControllerTest extends IntegrationTest {

    @Test
    public void test() {
        ResponseEntity<Project> entity = getRestTemplate()
                .getForEntity("/v1/projects/{id}", Project.class, 1);

        assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
