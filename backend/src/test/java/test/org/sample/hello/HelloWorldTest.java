package test.org.sample.hello;

import org.junit.Before;
import org.junit.Test;
import org.sample.hello.HelloWorld;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HelloWorldTest {


    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = standaloneSetup(new HelloWorld()).build();
    }

    @Test
    public void shouldRetrieveHelloWorld() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting", is("Hello World")));


    }
}