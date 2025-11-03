package com.spring_problem_solver.largeCapacity.controller;

import com.spring_problem_solver.largeCapacity.entity.LargeCapacity;
import com.spring_problem_solver.largeCapacity.service.LargeCapacityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/large/capacity")
@RequiredArgsConstructor
public class LargeCapacityController {
    private final LargeCapacityService largeCapacityService;

    @GetMapping
    public ResponseEntity<List<LargeCapacity>> getData(){
        return ResponseEntity.ok(largeCapacityService.getData());
    }

    // 성능 3등
    @PostMapping("/basic")
    public ResponseEntity<Void> createBasicData(){
        largeCapacityService.createBasicData();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // 성능 1등
    @PostMapping("/jdbcBatch")
    public ResponseEntity<Void> createJdbcBatchData(){
        largeCapacityService.createJdbcBatchData();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // 성능 2등
    @PostMapping("/jpaBatch")
    public ResponseEntity<Void> createJpaBatchData(){
        largeCapacityService.createJpaBatchData();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
