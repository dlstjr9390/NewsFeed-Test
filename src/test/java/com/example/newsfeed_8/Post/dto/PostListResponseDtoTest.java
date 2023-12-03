package com.example.newsfeed_8.Post.dto;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

class PostListResponseDtoTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    @DisplayName("PostListResponseDto 테스트")
    void test1() throws Exception{

        Member member = new Member("bob","1234","bob@email.com","hi");
        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\","+
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody,PostRequestDto.class);
        Post post = new Post(postRequestDto,member);

        PostListResponseDto responseDto = new PostListResponseDto(post);

        assertEquals("제목",responseDto.getTitle());
        assertEquals("이미지주소",responseDto.getImg());
    }

}