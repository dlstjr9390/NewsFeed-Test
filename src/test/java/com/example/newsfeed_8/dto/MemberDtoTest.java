package com.example.newsfeed_8.dto;

import com.example.newsfeed_8.Member.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDtoTest {

    @Test
    @DisplayName("RequestBody로 받은 email")
    void test1() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"email\" : \"bob2@email.com\"}";

        //when
        MemberDto.UpdateEmailRequestDto updateEmailRequestDto = objectMapper.readValue(
                requestBody,
                MemberDto.UpdateEmailRequestDto.class
        );

        //then
        assertEquals("bob2@email.com",updateEmailRequestDto.getEmail());
    }

}