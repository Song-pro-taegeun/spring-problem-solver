package com.spring_problem_solver.largeCapacity.service;

import com.spring_problem_solver.largeCapacity.entity.BatchLargeCapacity;
import com.spring_problem_solver.largeCapacity.entity.LargeCapacity;
import com.spring_problem_solver.largeCapacity.repository.LargeCapacityRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LargeCapacityService {
    private final LargeCapacityRepository largeCapacityRepository;
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager em;

    public List<LargeCapacity> getData() {
        return largeCapacityRepository.findAll();
    }

    @Transactional
    public void createBasicData() {
        List<LargeCapacity> insertDatas = new ArrayList<>();
        for (int i= 1; i <= 500000; i++){
            LargeCapacity data = LargeCapacity.builder()
                    .title("테스트 제목 " + i)
                    .contents("테스트 내용 " + i)
                    .build();

            insertDatas.add(data);
        }
        largeCapacityRepository.saveAll(insertDatas);
    }

    @Transactional
    public void createJdbcBatchData() {
        String sql = "INSERT INTO large_capacity (title, contents) VALUES (?, ?)";
        int total = 500_000;
        int batchSize = 1000;

        for (int start = 0; start < total; start += batchSize) {
            int end = Math.min(start + batchSize, total);
            final int startIndex = start;

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    int idx = startIndex + i + 1;
                    ps.setString(1, "테스트 제목 " + idx);
                    ps.setString(2, "테스트 내용 " + idx);
                }

                @Override
                public int getBatchSize() {
                    return end - startIndex;
                }
            });
        }
    }

    @Transactional
    public void createJpaBatchData() {
        int batchSize = 1000; // Hibernate batch_size와 맞추기
        for (int i = 1; i <= 500_000; i++) {
            BatchLargeCapacity data = BatchLargeCapacity.builder()
                    .lcId(UUID.randomUUID().toString().replace("-", ""))
                    .title("테스트 제목 " + i)
                    .contents("테스트 내용 " + i)
                    .build();

            em.persist(data);

            if (i % batchSize == 0) {
                em.flush();   // 실제 insert 쿼리 DB로 전송
                em.clear();   // 1차 캐시 비움 (메모리 절약)
            }
        }

        // 남은 데이터 처리
        em.flush();
        em.clear();
    }

}
