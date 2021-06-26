package com.example.demo.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer>{

	List<ArticleEntity> findByTitleLike(String title);
}
