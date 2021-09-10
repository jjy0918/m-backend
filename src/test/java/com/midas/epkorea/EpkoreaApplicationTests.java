package com.midas.epkorea;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EpkoreaApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @DisplayName("전체 관리자 목록 조회")
    @Test
    void getAllManager() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/manager")).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(equalTo("rqwe")));

    }


}
