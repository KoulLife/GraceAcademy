package com.ToMist.GraceAcademy.repository;

import com.ToMist.GraceAcademy.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
