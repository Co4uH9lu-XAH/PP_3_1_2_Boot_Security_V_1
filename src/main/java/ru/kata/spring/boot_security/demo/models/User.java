package ru.kata.spring.boot_security.demo.models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


// Я осознал, что надо заводить класс-обертку над User и передавать туда User или от него же наследоваться,
// чтобы получить доступ к методам User, но в ТЗ написано User имплементит UserDetails
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable (name = "users_roles", joinColumns = @JoinColumn( name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String surname, int age) {
        this.username = username;
        this.surname = surname;
        this.age = age;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
