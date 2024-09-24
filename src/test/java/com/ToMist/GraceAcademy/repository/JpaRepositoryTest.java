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
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("testdb")
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

    @DisplayName("insert 테스트")
    @Test
    void 인서트테스트() {
        // givne
            long prevCnt = articleRepository.count();

        // when
            articleRepository.save(Article.of("kiki", "do you love me", "are u riding"));
        // then
            assertThat(articleRepository.count()).isEqualTo(prevCnt + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void 업데이트_테스트() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashTag = "#springboot";
        article.setHashtag(updatedHashTag);

        // when
        Article savedArticle = articleRepository.saveAndFlush(article);

        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashTag);
    }

    @DisplayName("delete 테스트")
    @Test
    void 삭제_테스트() {

        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long prevArticleCnt = articleRepository.count();
        long prevArticleCommentCnt = articleCommentRepository.count();
        long deletedCommnetSize = article.getArticleComments().size();

        // when
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(prevArticleCnt - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCnt - deletedCommnetSize);
    }
}