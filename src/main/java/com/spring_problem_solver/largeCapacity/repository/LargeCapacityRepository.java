package com.spring_problem_solver.largeCapacity.repository;

import com.spring_problem_solver.largeCapacity.entity.LargeCapacity;
import com.spring_problem_solver.largeCapacity.repository.costom.LargeCapacityCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCapacityRepository extends JpaRepository<LargeCapacity, Long>, LargeCapacityCustom {

}
