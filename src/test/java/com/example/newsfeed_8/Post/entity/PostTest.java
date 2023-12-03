package com.example.newsfeed_8.Post.entity;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostTest {
    @Autowired
    TestEntityManager entityManager;

    ObjectMapper objectMapper = new ObjectMapper();
    Member member;
    PostRequestDto postRequestDto;
    @BeforeEach
    void setUp() throws Exception {

        member = new Member("bob", "1234", "bob@email.com", "hi");

        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\"," +
                "\"img\" : \"이미지주소\"}";
        postRequestDto = objectMapper.readValue(postRequestBody, PostRequestDto.class);

        entityManager.persistAndFlush(member);
    }

    @Test
    @DisplayName("Post Entity 테스트")
    void test1() throws Exception{

        Post post = new Post(postRequestDto, member);
        entityManager.persistAndFlush(post);

        Post savedPost = entityManager.find(Post.class,post.getId());

        assertNotNull(savedPost);
        assertEquals(post.getId(),savedPost.getId());
        assertEquals(post.getTitle(),savedPost.getTitle());

        //update 메서드
        String updatedRequestBody = "{\"title\" : \"수ㅠㅈ제목\"," +
                "\"content\" : \"내용\"," +
                "\"img\" : \"이미지주소\"}";
        PostRequestDto updatedRequestDto = objectMapper.readValue(updatedRequestBody, PostRequestDto.class);

        savedPost.update(updatedRequestDto);
        entityManager.persistAndFlush(savedPost);

        Post updatedPost = entityManager.find(Post.class,savedPost.getId());

        assertNotNull(updatedPost);
        assertEquals(savedPost.getId(),updatedPost.getId());
        assertEquals(savedPost.getTitle(),updatedPost.getTitle());
        assertEquals(savedPost.getMember(),updatedPost.getMember());

    }

}