package com.ToMist.GraceAcademy.repository;

import com.ToMist.GraceAcademy.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
