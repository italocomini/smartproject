package com.github.italocomini.smartproject.repositories;

import com.github.italocomini.smartproject.models.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
}
