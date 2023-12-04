package com.example.newsfeed_8.test;

import com.example.newsfeed_8.Member.entity.Member;
import com.example.newsfeed_8.security.entity.MemberDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Member testMember = new Member("bob1234",
            "123456789",
            "bob@email.com",
            "hi");

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        MemberDetailsImpl testMemberDetails = new MemberDetailsImpl(testMember);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                testMemberDetails, testMemberDetails.getPassword(), testMemberDetails.getAuthorities()
        ));
    }
}