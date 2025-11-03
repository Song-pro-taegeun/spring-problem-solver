package com.spring_problem_solver.largeCapacity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name="batch_large_capacity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BatchLargeCapacity implements Serializable {
    @Id
    @Column(name = "lc_id")
    private String lcId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;
}
