package com.ToMist.GraceAcademy.repository;

import com.ToMist.GraceAcademy.domain.Article;
import com.ToMist.GraceAcademy.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,   // 엔티티 모든 필드에 검색 기능 추가 (exactly 검색)
        QuerydslBinderCustomizer<QArticle>   // 부분 검색과 같은 커스텀 검색 기능 추가
{

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Article> findByHashtag(String hashtag, Pageable pageable);


    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);   // 모든 필드 검색 제한
        bindings.including(root.title, root.hashtag, root.createdAt, root.createdBy);   // title, hashtag, createdAt, createdBy만 검색
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // 부분검색, 대소문자 구분 X
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
