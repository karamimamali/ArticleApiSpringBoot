package com.example.demo.rest.v1;

import com.example.demo.services.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ArticleController.class)
public class  ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    void getArticleById() throws Exception {
        // Define the expected article ID
        long articleId = 1L;

        // Define the request to perform
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/{id}", articleId))
                // Expect HTTP status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
