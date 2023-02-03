package com.restfullwebservices.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post1 {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
