package com.example.codingbatcom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean done=false;

    private String question;

    private String solution;

    @ManyToOne
    private Category category;

    public Task(String name, String question, String solution, Category category) {
        this.name = name;
        this.question = question;
        this.solution = solution;
        this.category = category;
    }
}
