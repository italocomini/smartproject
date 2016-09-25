package com.github.italocomini.smartproject.seeds;

import com.github.italocomini.smartproject.models.Project;
import com.github.italocomini.smartproject.repositories.ProjectRepository;
import com.github.italocomini.smartproject.repositories.UserRepository;
import com.github.italocomini.smartproject.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Profile(value = {"development", "test"})
@Configuration
public class Seeder {

    @Bean
    CommandLineRunner addProjects(ProjectRepository repository) {
        return (evt) -> {

            repository.deleteAll();

            repository.save(new Project("SmartProject"));
            repository.save(new Project("Workfront"));
            repository.save(new Project("Redmine"));
            repository.save(new Project("Basecamp"));
            repository.save(new Project("RationalPlan"));
            repository.save(new Project("Bitrix24"));
        };
    }

    @Bean
    CommandLineRunner addUsers(UserRepository repository) {
        return (evt) -> {

            repository.deleteAll();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User admin = new User();
            admin.setUsername("admin");
            admin.setFirstName("Administrator");
            admin.setLastName("SA");
            admin.setAdmin(true);
            admin.setPassword(encoder.encode("admin"));

            repository.save(admin);


            User italo = new User();
            italo.setUsername("italo");
            italo.setFirstName("Italo");
            italo.setLastName("Comini");
            italo.setAdmin(false);
            italo.setPassword(encoder.encode("italo"));

            repository.save(italo);

        };
    }

}
