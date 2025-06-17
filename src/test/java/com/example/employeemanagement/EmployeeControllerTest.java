package com.example.employeemanagement;

import com.example.employeemanagement.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById_shouldReturnWrappedApiResponse() throws Exception {
        EmployeeDto mockDto = new EmployeeDto(1L, "Akash", "IT", 50000);

        when(employeeService.getEmployeeById(1L)).thenReturn(mockDto);

        mockMvc.perform(get("/employees/1")
                        .accept(MediaType.APPLICATION_JSON)) // Use `.accept`, not `.contentType` for GET
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Employee fetched successfully"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Akash"))
                .andExpect(jsonPath("$.data.department").value("IT"))
                .andExpect(jsonPath("$.data.salary").value(50000));
    }
}
