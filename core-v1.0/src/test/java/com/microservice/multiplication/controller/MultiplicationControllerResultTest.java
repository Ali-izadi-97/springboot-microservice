package com.microservice.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.multiplication.controller.MultiplicationResultController;
import com.microservice.multiplication.controller.MultiplicationResultController.WrapResultResponse;
import com.microservice.multiplication.domain.Multiplication;
import com.microservice.multiplication.domain.MultiplicationResult;
import com.microservice.multiplication.domain.User;
import com.microservice.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MultiplicationResultController.class)
public class MultiplicationControllerResultTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResult> jsonResult;
    private JacksonTester<WrapResultResponse> jsonResponse;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }


    private void genericParameterizedTest(final boolean correct) throws Exception {
        given(multiplicationService.checkAttempt(any(MultiplicationResult.class)))
                .willReturn(correct);

        User user = new User("user");
        Multiplication multiplication = new Multiplication(10, 11);
        MultiplicationResult multiplicationResult = new MultiplicationResult(user,
                multiplication, 110, correct);

        MockHttpServletResponse response = mvc.perform(
                post("/results").contentType(MediaType.APPLICATION_JSON)
                .content(jsonResult.write(multiplicationResult).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponse.write(new WrapResultResponse(correct)).getJson());
    }
}
