package com.example.newsfeed_8.Comment.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestDtoTest {

    @Test
    @DisplayName("CommentRequestDto 테스트")
    void test1() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"content\" : \"내용테스트\"}";

        //when
        CommentRequestDto requestDto = objectMapper.readValue(requestBody,CommentRequestDto.class);

        //Then
        assertEquals("내용테스트",requestDto.getContent());
    }
}