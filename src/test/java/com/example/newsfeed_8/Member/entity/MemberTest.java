package com.example.newsfeed_8.Member.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberTest {
    @Autowired
    TestEntityManager entityManager;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Member Entity 테스트")
    void test1(){

        Member member = new Member("bob","1234","bob@email.com","hi");
        entityManager.persistAndFlush(member);

        Member savedMember = entityManager.find(Member.class, member.getId());

        assertNotNull(savedMember);
        assertEquals(member.getUserId(),savedMember.getUserId());

    }

}