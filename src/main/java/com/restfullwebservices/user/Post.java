package com.restfullwebservices.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

}
