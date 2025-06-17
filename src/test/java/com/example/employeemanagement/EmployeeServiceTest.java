package com.example.employeemanagement;

import com.example.employeemanagement.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getEmployeeById_shouldReturnEmployeeDto(){
        // Step 1: Setup test data
        Employee mockEmployee=new Employee("Akash","IT",50000);
        mockEmployee.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));


        // Step 2: Call the method
        EmployeeDto result=employeeService.getEmployeeById(1L);

        // Step 3: Check result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Akash", result.getName());
        assertEquals("IT", result.getDepartment());
        assertEquals(50000, result.getSalary());
    }


    @Test
    public void getEmployeeById_whenNotFound_shouldThrowException(){
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception=assertThrows(RuntimeException.class,()->{
            employeeService.getEmployeeById(99L);
        });
    }
}
