package EmployeeTester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import supun.com.main.repository.EmployeeRepository;

import java.util.Collections;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest
public class EmployeeTester {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() throws Exception {

        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/employees")
                        .accept((MediaType.APPLICATION_JSON))
        ).andReturn();

        System.out.println(mvcResult.getResponse());

        Mockito.verify(employeeRepository.findAll());
    }
}