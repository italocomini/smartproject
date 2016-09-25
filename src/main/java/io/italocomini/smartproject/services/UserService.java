package io.italocomini.smartproject.services;

import io.italocomini.smartproject.models.User;
import io.italocomini.smartproject.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (user.isAdmin()) {
//            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
//        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
