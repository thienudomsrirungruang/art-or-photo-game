package com.thien.entity;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleInfo that = (UserRoleInfo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
