package com.example.newsfeed_8.CommentLike.entity;

import com.example.newsfeed_8.Comment.dto.CommentRequestDto;
import com.example.newsfeed_8.Comment.entity.Comment;
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
class CommentLikeTest {
    @Autowired
    TestEntityManager entityManager;

    ObjectMapper objectMapper = new ObjectMapper();
    Member member;
    Post post;
    Comment comment;

    @BeforeEach
    void setUp() throws Exception{

        member = new Member("bob","1234","bob@email.com","hi");

        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\","+
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody,PostRequestDto.class);

        post = new Post(postRequestDto, member);

        String commentRequestBody = "{\"content\" : \"댓글 내용\"}";
        CommentRequestDto commentRequestDto = objectMapper.readValue(commentRequestBody, CommentRequestDto.class);
        comment = new Comment(commentRequestDto,member,post);


        entityManager.persist(member);
        entityManager.persist(post);
        entityManager.persist(comment);
        entityManager.flush();
    }

    @Test
    @DisplayName("CommentLike Entity 테스트")
    void test1() throws Exception{

        CommentLike commentLike = new CommentLike(comment,member,true);
        entityManager.persistAndFlush(commentLike);

        CommentLike savedCommentLike = entityManager.find(CommentLike.class, commentLike.getId());

        assertTrue(savedCommentLike.getIsLike());

        //setIsLike 메서드
        savedCommentLike.setIsLike(false);
        entityManager.persistAndFlush(savedCommentLike);

        CommentLike updatedCommentLike = entityManager.find(CommentLike.class,commentLike.getId());

        assertFalse(updatedCommentLike.getIsLike());
        assertEquals(savedCommentLike.getIsLike(),updatedCommentLike.getIsLike());
    }
}