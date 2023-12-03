package com.example.newsfeed_8.CommentLike.dto;

import com.example.newsfeed_8.CommentLike.entity.CommentLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommonLikeResponseDtoTest {

    @Test
    @DisplayName("CommonLikeResponse 테스트")
    void test1(){

        //given
        String msg = "성공";
        Integer status = HttpStatus.OK.value();
        long like = 1;

        //when
        CommonLikeResponseDto responseDto = new CommonLikeResponseDto(msg, status, like);

        //then
        assertEquals("성공",responseDto.getMsg());
        assertEquals(200,responseDto.getStatusCode());
        assertEquals(1,responseDto.getLikes());
    }

}