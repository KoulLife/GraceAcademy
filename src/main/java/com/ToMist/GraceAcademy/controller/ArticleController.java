package com.ToMist.GraceAcademy.controller;

import com.ToMist.GraceAcademy.domain.type.SearchType;
import com.ToMist.GraceAcademy.dto.response.ArticleResponse;
import com.ToMist.GraceAcademy.dto.response.ArticleWithCommentsResponse;
import com.ToMist.GraceAcademy.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(
            @RequestParam(required = false, name = "searchType") SearchType searchType,
            @RequestParam(required = false, name = "searchValue") String searchValue,
            // createdAt을 기준으로 내림차순
            @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            ModelMap map
    ) {

        map.addAttribute("articles", articleService.searchArticles(searchType, searchValue, pageable)
                .map(ArticleResponse::from));

        return "articles/index";

    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable("articleId") Long articleId, ModelMap map) {

        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());

        return "articles/detail";

    }

}
