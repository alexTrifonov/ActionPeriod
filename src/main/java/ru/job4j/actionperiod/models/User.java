package ru.job4j.actionperiod.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Entity user.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "at_work")
    private boolean atWork;
    @Column(name = "work_start")
    private long workStart;
    @Column(name = "work_full")
    private long workFull;
    private boolean enabled;

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAtWork(boolean atWork) {
        this.atWork = atWork;
    }

    public void setWorkStart(long workStart) {
        this.workStart = workStart;
    }

    public void setWorkFull(long workFull) {
        this.workFull = workFull;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAtWork() {
        return atWork;
    }

    public long getWorkStart() {
        return workStart;
    }

    public long getWorkFull() {
        return workFull;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
