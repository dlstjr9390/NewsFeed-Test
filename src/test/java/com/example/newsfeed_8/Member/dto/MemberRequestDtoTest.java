package com.example.newsfeed_8.Member.dto;

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

class MemberRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    @DisplayName("MemberRequestDto 테스트")
    void test1() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"userId\" : \"bob123\"," +
                "\"password\" : \"123456789\"," +
                "\"email\" : \"bob2@email.com\"," +
                "\"introduction\" : \"hello\"}";

        //when
        MemberRequestDto requestDto = objectMapper.readValue(
                requestBody,
                MemberRequestDto.class
        );

        //then
        assertEquals("bob123",requestDto.getUserId());
        assertEquals("123456789",requestDto.getPassword());
        assertEquals("bob2@email.com",requestDto.getEmail());
        assertEquals("hello",requestDto.getIntroduction());
        Set<ConstraintViolation<MemberRequestDto>> violations = validator.validate(requestDto);
        assertTrue(violations.isEmpty());
    }
}