package com.example.newsfeed_8.Comment.entity;

import com.example.newsfeed_8.Comment.dto.CommentRequestDto;
import com.example.newsfeed_8.Comment.repository.CommentRepository;
import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Member.repository.MemberRepository;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.entity.Post;
import com.example.newsfeed_8.Post.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentTest {
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
    @DisplayName("Comment Entity 테스트")
    void test1() throws Exception{

        String commentRequestBody = "{\"content\" : \"내용테스트\"}";
        CommentRequestDto requestDto = objectMapper.readValue(commentRequestBody,CommentRequestDto.class);
        Comment comment = new Comment(requestDto,member,post);
        entityManager.persistAndFlush(comment);

        Comment savedComment = entityManager.find(Comment.class, comment.getId());

        assertNotNull(savedComment);
        assertEquals(requestDto.getContent(),savedComment.getContent());
        assertEquals(member, savedComment.getMember());
        assertEquals(post, savedComment.getPost());

        //upadate 메서드
        String updateRequestBody = "{\"content\" : \"업데이트된 내용 테스트\"}";
        CommentRequestDto updateRequestDto = objectMapper.readValue(updateRequestBody,CommentRequestDto.class);
        savedComment.update(updateRequestDto);
        entityManager.persistAndFlush(savedComment);

        Comment updatedComment = entityManager.find(Comment.class, comment.getId());

        assertNotNull(updatedComment);
        assertEquals(updateRequestDto.getContent(), updatedComment.getContent());


    }
}