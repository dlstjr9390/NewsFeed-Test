package com.example.newsfeed_8.jwt.Util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp(){
        jwtUtil.init();
    }

    @Test
    @DisplayName("토큰 생성")
    void test1(){
        String token = jwtUtil.createToken("bob1234");

        assertNotNull(token);
    }

    @Test
    @DisplayName("토큰 추출")
    void test2(){
        String token = "Bearer test-token";

        given(request.getHeader(JwtUtil.AUTHORIZATION_HEADER)).willReturn(token);
        String resolvedToken = jwtUtil.resolveToken(request);

        assertEquals("test-token", resolvedToken);
    }

    @Nested
    @DisplayName("토큰 검증")
    class validateToken {

        @Test
        @DisplayName("토큰 검증 성공")
        void test3_1() {

            // given
            String token = jwtUtil.createToken("bob1234").substring(7);

            // when
            boolean isValid = jwtUtil.validateToken(token);

            // then
            assertTrue(isValid);

        }

        @Test
        @DisplayName("토큰 검증 실패")
        void test3_2(){

            // given
            String invalidToken = "invalid-token";

            // when
            boolean isValid = jwtUtil.validateToken(invalidToken);

            //then
            assertFalse(isValid);
        }
    }

    @Test
    @DisplayName("토큰에서 MemberInfo 조회")
    void test4(){

        // given
        String token = jwtUtil.createToken("bob1234").substring(7);

        // when
        Claims claims = jwtUtil.getMemberInfoFromToken(token);

        // then
        assertNotNull(claims);
        assertEquals("bob1234", claims.getSubject());
    }
}