package com.example.newsfeed_8.Comment.dto;

import com.example.newsfeed_8.Comment.dto.CommentRequestDto;
import com.example.newsfeed_8.Comment.dto.CommentResponsDto;
import com.example.newsfeed_8.Comment.entity.Comment;
import com.example.newsfeed_8.Comment.repository.CommentRepository;
import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommentResponsDtoTest {

    @Test
    @DisplayName("CommentResponseDto 테스트")
    void test1() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String commentRequestBody = "{\"content\" : \"내용테스트\"}";
        String PostRequestBody = "{\"title\" : \"제목\", \"content\" : \"내용\", \"img\" : \"이미지주소\"}";

        //given
        Member member = new Member("bob","1234","bob@email.com","hi");

        PostRequestDto postRequestDto = objectMapper.readValue(PostRequestBody,PostRequestDto.class);
        Post post = new Post(postRequestDto,member);

        CommentRequestDto commentRequestDto = objectMapper.readValue(commentRequestBody,CommentRequestDto.class);
        Comment comment = new Comment(commentRequestDto,member,post);

        //When
        CommentResponsDto commentResponsDto = new CommentResponsDto(comment);

        //then
        assertEquals(member.getUserId(),commentResponsDto.getUserId());
        assertEquals("내용테스트",commentResponsDto.getContent());

    }

}