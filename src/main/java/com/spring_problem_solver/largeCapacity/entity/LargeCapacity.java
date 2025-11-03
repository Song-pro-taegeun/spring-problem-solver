package com.spring_problem_solver.largeCapacity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name="large_capacity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LargeCapacity implements Serializable {
    @Id
    @Column(name = "lc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lcId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;
}
