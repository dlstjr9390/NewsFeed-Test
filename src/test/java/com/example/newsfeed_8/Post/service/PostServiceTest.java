package com.example.newsfeed_8.Post.service;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostCreateResponseDto;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.entity.Post;
import com.example.newsfeed_8.Post.repository.PostRepository;
import com.example.newsfeed_8.PostLike.repository.PostLikeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;




@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    PostLikeRepository postLikeRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("게시글 생성 테스트")
    void test1() throws Exception{

        Member member = new Member("bob123", "123456789", "bob@email.com", "hi");
        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\"," +
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody, PostRequestDto.class);
        PostService postService = new PostService(postRepository,postLikeRepository);

        PostCreateResponseDto responseDto = postService.createPost(postRequestDto,member);

        assertNotNull(responseDto);
        verify(postRepository, times(1)).save(any(Post.class));

    }

}