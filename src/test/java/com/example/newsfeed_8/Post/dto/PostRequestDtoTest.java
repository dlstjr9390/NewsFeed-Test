package com.example.newsfeed_8.Post.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestDtoTest {

    @Test
    @DisplayName("PostRequestDto 테스트")
    void test1() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\","+
                "\"img\" : \"이미지주소\"}";

        PostRequestDto requestDto = objectMapper.readValue(postRequestBody,PostRequestDto.class);

        assertEquals("제목",requestDto.getTitle());
        assertEquals("내용",requestDto.getContent());
        assertEquals("이미지주소",requestDto.getImg());

    }

}