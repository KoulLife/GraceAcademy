package com.ToMist.GraceAcademy.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.ToMist.GraceAcademy.config.JpaConfig;
import com.ToMist.GraceAcademy.domain.Article;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData() {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles).
                isNotNull()
                .hasSize(123);

    }
}