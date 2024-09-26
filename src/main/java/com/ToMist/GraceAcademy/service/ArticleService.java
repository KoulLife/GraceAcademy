package com.ToMist.GraceAcademy.service;

import com.ToMist.GraceAcademy.domain.Article;
import com.ToMist.GraceAcademy.domain.type.SearchType;
import com.ToMist.GraceAcademy.dto.ArticleDto;
import com.ToMist.GraceAcademy.dto.ArticleWithCommentsDto;
import com.ToMist.GraceAcademy.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {

        // 검색어가 있는 경우, 없는 경우 고려
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);   // TODO: 사용자가 #을 안 쓰도록 만들어야 한다.
        };

    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    // class 트랜잭션에 의해 메소드 단위로 트랜잭션이 묶여 있다.
    // article 변화에 대한 쿼리를 날린다. 따라서 save를 명시를 하지 않아도 된다.
    public void updateArticle(ArticleDto dto) {
        try{
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) article.setTitle(dto.title());
            if (dto.content() != null) article.setContent(dto.content());
            article.setHashtag(dto.hashtag());
//            articleRepository.save(article);
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }

    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

}
