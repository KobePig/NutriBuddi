package com.Temple.NutriBuddi.UserManagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="imageform")
public class ImageForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filePath;

    @OneToMany(mappedBy ="imageForm")
    private List<ObjectImage> objectImages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
