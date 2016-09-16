package io.italocomini.smartproject.repositories;

import io.italocomini.smartproject.models.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
}
