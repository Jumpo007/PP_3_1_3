package ru.kata.spring.boot_security.demo.models;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @NotEmpty(message = "Это поле обязательно к заполнению")
    @Size(min = 3, max = 15, message = "значение не в диапозоне от 3 до 15 символов")
    private String firstName;
    @Column(name = "surname")
    @NotEmpty(message = "Это поле обязательно к заполнению")
    @Size(min = 3, max = 15, message = "значение не в диапозоне от 3 до 15 символов")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users1_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
