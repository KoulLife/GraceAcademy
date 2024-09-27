package com.ToMist.GraceAcademy.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class) //테스트의 무게 줄이기
class PaginationServiceTest {

    private final PaginationService sut;

    public PaginationServiceTest(@Autowired PaginationService sut) {
        this.sut = sut;
    }

    @ParameterizedTest(name = "[{index}] {0}, {1} --> {2}")
    @MethodSource
    void 페이징_추출(int currentPageNumber, int totalPages, List<Integer> expected) {

        // given

        // when
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> 페이징_추출() {
        return Stream.of(
                arguments(0, 13, List.of(0,1,2,3,4)),
                arguments(1, 13, List.of(0,1,2,3,4)),
                arguments(2, 13, List.of(0,1,2,3,4)),
                arguments(3, 13, List.of(1,2,3,4,5)),
                arguments(4, 13, List.of(2,3,4,5,6)),
                arguments(5, 13, List.of(3,4,5,6,7)),
                arguments(12, 13, List.of(10,11,12))
        );
    }

    @Test
    void 페이지_바_길이_체크() {

        int barLength = sut.currentBarLength();

        assertThat(barLength).isEqualTo(5);

    }

}