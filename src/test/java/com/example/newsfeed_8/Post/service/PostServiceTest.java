package com.example.newsfeed_8.Post.service;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostCreateResponseDto;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.entity.Post;
import com.example.newsfeed_8.Post.repository.PostRepository;
import com.example.newsfeed_8.PostLike.repository.PostLikeRepository;
import com.example.newsfeed_8.jwt.Util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    PostLikeRepository postLikeRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    Member member;
    PostRequestDto postRequestDto;


    @Test
    @DisplayName("게시글 생성 테스트")
    void test1() throws Exception{

        member = new Member("bob123", "123456789", "bob@email.com", "hi");
        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\"," +
                "\"img\" : \"이미지주소\"}";
        postRequestDto = objectMapper.readValue(postRequestBody, PostRequestDto.class);
        PostService postService = new PostService(postRepository,postLikeRepository);

        postService.createPost(postRequestDto,member);

        verify(postRepository, times(1)).save(any(Post.class));

    }


}