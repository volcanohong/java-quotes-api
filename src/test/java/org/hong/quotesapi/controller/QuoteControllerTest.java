package org.hong.quotesapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hong.quotesapi.service.QuoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest {

    @Autowired
    private QuoteService service;

//    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        QuoteController controller = new QuoteController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldReturnRandomQuotesInJson() throws Exception {
        this.mockMvc.perform(get("/quotes/rand").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
        ;
    }

    @Test
    public void shouldReturnRandomQuotesByNumber() throws Exception {
        final int num = 4;
        this.mockMvc.perform(get("/quotes/rand?num=" + num).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(num)))
        ;
    }

    @Test
    public void shouldReturnQuotesByAuthor() throws Exception {
        final int num = 1;
        final String author = "Jordan";
        this.mockMvc.perform(get("/quotes?num=" + num + "&author=" + author)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(num)))
                .andExpect(jsonPath("$[0].author", containsString(author)))
        ;
    }

    @Test
    public void shouldReturnNotFoundError() throws Exception {
        final String author = "any";
        this.mockMvc.perform(get("/quotes?author=" + author)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}
