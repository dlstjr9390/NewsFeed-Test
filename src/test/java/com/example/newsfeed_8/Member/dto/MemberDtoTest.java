package com.example.newsfeed_8.Member.dto;


import com.example.newsfeed_8.Member.dto.MemberDto;
import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.security.entity.MemberDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
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
    @DisplayName("이메일 변경을 위한 MemberDto - 옳은 형식")
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
        Set<ConstraintViolation<MemberDto.UpdateEmailRequestDto>> violations = validator.validate(updateEmailRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("이메일 변경을 위한 MemberDto - 옳지 않은 형식")
    void test1_1() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"email\" : \"bob2email.com\"}";

        //when
        MemberDto.UpdateEmailRequestDto updateEmailRequestDto = objectMapper.readValue(
                requestBody,
                MemberDto.UpdateEmailRequestDto.class
        );

        //then
        assertEquals("bob2email.com",updateEmailRequestDto.getEmail());
        Set<ConstraintViolation<MemberDto.UpdateEmailRequestDto>> violations = validator.validate(updateEmailRequestDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("비밀번호 변경을 위한 MemberDto")
    void test2() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"originPassword\" : \"12345678\"," +
                "\"newPassword\" : \"123456789\"," +
                " \"newPasswordCheck\" : \"123456789\"}";

        //when
        MemberDto.UpdatePasswordRequestDto updatePasswordRequestDto = objectMapper.readValue(
            requestBody,
            MemberDto.UpdatePasswordRequestDto.class
        );

        //then
        assertEquals("12345678",updatePasswordRequestDto.getOriginPassword());
        assertEquals("123456789",updatePasswordRequestDto.getNewPassword());
        assertEquals(updatePasswordRequestDto.getNewPassword(),updatePasswordRequestDto.getNewPasswordCheck());
        Set<ConstraintViolation<MemberDto.UpdatePasswordRequestDto>> violations = validator.validate(updatePasswordRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("한줄소개 변경을 위한 MemberDto")
    void test3() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        //given
        String requestBody = "{\"introduction\" : \"hello\"}";

        //when
        MemberDto.UpdateIntroductionRequestDto updateIntroductionRequestDto = objectMapper.readValue(
                requestBody,
                MemberDto.UpdateIntroductionRequestDto.class
        );

        //then
        assertEquals("hello",updateIntroductionRequestDto.getIntroduction());
        Set<ConstraintViolation<MemberDto.UpdateIntroductionRequestDto>> violations = validator.validate(
                updateIntroductionRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("내정보 확인을 위한 MemberDto")
    void test4(){

        //given
        Member member = new Member("bob","1234","bob@email.com","hi");


        //when
        MemberDto.GetMyAccountResponseDto responseDto = MemberDto.GetMyAccountResponseDto.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .introduction(member.getIntroduction())
                .build();
        //then
        assertEquals("bob",responseDto.getUserId());
        assertEquals("bob@email.com",responseDto.getEmail());
        assertEquals("hi",responseDto.getIntroduction());
    }
}