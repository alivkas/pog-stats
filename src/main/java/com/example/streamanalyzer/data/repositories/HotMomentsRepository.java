package com.example.streamanalyzer.data.repositories;

import com.example.streamanalyzer.data.entity.HotMomentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotMomentsRepository extends JpaRepository<HotMomentsEntity, Long> {
}
