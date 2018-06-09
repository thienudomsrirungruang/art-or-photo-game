package com.thien.entity;

import javax.persistence.*;

@Entity
@Table(name="pics")
public class PictureInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="path")
    private String path;
    @Column(name="is_photo")
    private boolean isPhoto;

    public void setPath(String path) {
        this.path = path;
    }

    public void setIsPhoto(boolean isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getPath() {
        return path;
    }

    public boolean isPhoto() {
        return isPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureInfo that = (PictureInfo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
