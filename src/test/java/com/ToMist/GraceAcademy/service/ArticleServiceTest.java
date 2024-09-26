package com.ToMist.GraceAcademy.service;

import static org.assertj.core.api.Assertions.*;

import com.ToMist.GraceAcademy.domain.type.SearchType;
import com.ToMist.GraceAcademy.dto.ArticleDto;
import com.ToMist.GraceAcademy.dto.ArticleWithCommentsDto;
import com.ToMist.GraceAcademy.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DisplayName("비즈니스 로직 - 게시판")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {


    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @Test
    void 게시글_검색_게시글_반환() {
        // given

        // when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword", Pageable.unpaged());

        // then
        assertThat(articles).isNotNull();
    }


}