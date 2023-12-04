package com.example.newsfeed_8.Post.controller;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.Post.dto.PostRequestDto;
import com.example.newsfeed_8.Post.service.PostService;
import com.example.newsfeed_8.test.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
class PostControllerTest extends ControllerTest{

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("게시글 생성 요청")
    void test1() throws Exception{

        String postRequestBody = "{\"title\" : \"제목\"," +
                "\"content\" : \"내용\"," +
                "\"img\" : \"이미지주소\"}";
        PostRequestDto postRequestDto = objectMapper.readValue(postRequestBody, PostRequestDto.class);
        Member member = new Member("bob123", "123456789", "bob@email.com", "hi");

        var action = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postRequestDto)));

        action.andExpect(status().isCreated());
        verify(postService, times(1)).createPost(any(PostRequestDto.class), eq(member));
    }
}