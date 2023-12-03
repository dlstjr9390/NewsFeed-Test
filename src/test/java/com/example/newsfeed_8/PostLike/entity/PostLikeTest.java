package com.example.newsfeed_8.PostLike.entity;

import com.example.newsfeed_8.CommentLike.entity.CommentLike;
import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostLikeTest {
    @Autowired
    TestEntityManager entityManager;

    ObjectMapper objectMapper = new ObjectMapper();
    Member member;
    Post post;

    @BeforeEach
    void setUp() throws Exception{

        member = new Member("bob","1234","bob@email.com","hi");
        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\","+
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody,PostRequestDto.class);
        post = new Post(postRequestDto, member);

        entityManager.persist(member);
        entityManager.persist(post);
        entityManager.flush();
    }

    @Test
    @DisplayName("PostLike Entity 테스트")
    void test1(){

        PostLike postLike = new PostLike(post,member,true);
        entityManager.persistAndFlush(postLike);

        PostLike savePostLike = entityManager.find(PostLike.class,postLike.getId());

        assertNotNull(savePostLike);
        assertEquals(postLike.getId(),savePostLike.getId());

        //setIsLike 메서드
        savePostLike.setIsLike(false);
        entityManager.persistAndFlush(savePostLike);

        PostLike updatedPostLike = entityManager.find(PostLike.class,postLike.getId());

        assertFalse(updatedPostLike.getIsLike());
        assertEquals(savePostLike.getIsLike(),updatedPostLike.getIsLike());
    }
}