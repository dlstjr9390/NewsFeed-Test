package com.example.newsfeed_8.Member.dto;


import com.example.newsfeed_8.Member.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    @DisplayName("이메일 변경을 위한 Dto")
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

    @Test
    @DisplayName("비밀번호 변경을 위한 Dto")
    void test2() throws Exception{

    }

}