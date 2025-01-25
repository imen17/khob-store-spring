package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    // CascadeType.Persist: This automatically saves any photos when we create a user otherwise we get a "Transient" entity error
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Photo photo;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<UserRole> roles = new HashSet<>(List.of(UserRole.USER)); // Guarantee that any created user has at least role USER

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        for (UserRole role : this.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.name().toUpperCase()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

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

}
