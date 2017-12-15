package com.Temple.NutriBuddi.UserManagement.model;

import javax.persistence.*;

@Entity
@Table(name="objectimage")
public class ObjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int xL;

    private int xR;

    private int yU;

    private int yD;

    @ManyToOne
    @JoinColumn(name="imageform_id")
    private ImageForm imageForm;

    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;
}
