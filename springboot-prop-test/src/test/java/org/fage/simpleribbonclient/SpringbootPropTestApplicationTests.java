package org.fage.simpleribbonclient;

import org.fage.springbootproptest.SpringbootPropTestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringbootPropTestApplicationTests {
    private MockMvc mvc;

    @Before
    public void init(){
        mvc = MockMvcBuilders.standaloneSetup(new SpringbootPropTestApplication()).build();
    }

    /**
     * 验证Controller层
     * @throws Exception
     */
    @Test
    public void testHello() throws Exception {
        System.out.println(mvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

	@Test
	public void contextLoads() {
	}

}
