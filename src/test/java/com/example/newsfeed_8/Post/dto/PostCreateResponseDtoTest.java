package com.example.newsfeed_8.Post.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostCreateResponseDtoTest {

    @Test
    @DisplayName("PostCreateResponseDto 테스트")
    void test1(){

        //give
        Long Id = 1L;

        //when
        PostCreateResponseDto responseDto = new PostCreateResponseDto(Id);

        //then
        assertEquals(1,responseDto.getPostId());
    }

}