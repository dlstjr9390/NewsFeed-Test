package com.example.newsfeed_8.Post.dto;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

class PostResponseDtoTest {

    @Test
    @DisplayName("PostResponseDto 테스트")
    void test1() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Member member = new Member("bob","1234","bob@email.com","hi");
        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\","+
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody,PostRequestDto.class);
        Post post = new Post(postRequestDto,member);

        PostResponseDto responseDto = new PostResponseDto(post);

        assertEquals("bob",responseDto.getUser_id());
        assertEquals("제목",responseDto.getTitle());
        assertEquals("내용",responseDto.getContent());
        assertEquals("이미지주소",responseDto.getImg());
        assertNull(responseDto.getCreatedAt());
        assertNull(responseDto.getModifiedAt());
    }
}