package com.example.demo.pile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<TargetEntity,Integer>{

	public List<TargetEntity>findByMonth(Integer month);
}
