package com.example.security.Service;

import com.example.security.Model.Person;
import com.example.security.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Person currentUser;
    private int loginAttemptCount = 0;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(username);
        if (user == null) {
            loginAttemptCount++;
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        loginAttemptCount++;
        currentUser = user;
        personRepository.save(user);

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }


    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public void setLoginAttemptCount(int loginAttemptCount) {
        this.loginAttemptCount = loginAttemptCount;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int getLoginAttemptCount() {
        return loginAttemptCount;
    }
}
