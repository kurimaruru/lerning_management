package com.example.demo.pile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PileRepository extends JpaRepository<PileEntity,Integer>{	
	List<PileEntity> findByMonthAndDay(Integer month,Integer day);
	
	List<PileEntity> findByMonthOrderByDay(Integer x);
	
}
