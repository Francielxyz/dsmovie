package com.francielxyz.dsmovie.repository;

import com.francielxyz.dsmovie.entities.Score;
import com.francielxyz.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
