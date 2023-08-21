package com.example.security.Model;

import jakarta.persistence.*;


import javax.validation.constraints.Size;
import java.time.LocalDate;

import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false, length = 15)
    private String username;

    @Column(nullable = false,length = 15)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column
    private LocalDate login_date;

    public Person(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public LocalDate getLogin_date() {
        return login_date;
    }

    public void setLogin_date(LocalDate login_date) {
        this.login_date = login_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Person(String name, String password, Set<Role> role){
        this.username=name;
        this.password=password;
        this.roles=role;
    }
    public Person(){};

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
