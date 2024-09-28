package com.telusko.quizzapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @ManyToMany

    private List<Question> questions;
}
