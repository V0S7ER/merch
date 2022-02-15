package com.cod.merch.repository;

import com.cod.merch.model.Achievement;
import com.cod.merch.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
