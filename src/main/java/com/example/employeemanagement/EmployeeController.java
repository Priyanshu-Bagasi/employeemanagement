package com.example.employeemanagement;

//old without dto

//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    //create new employee
////    @PostMapping
////    public Employee createEmployee(@Valid @RequestBody Employee employee){
////        return employeeService.saveEmployee(employee);
////    }
//
//    //get all employees
////    @GetMapping
////    public List<Employee> getAllEmployees(){
////        return employeeService.getAllEmployees();
////    }
//
//    //get employee by id
//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable Long id){
//        return employeeService.getEmployeeById(id);
//    }
//
//    //update employee
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id,@Valid @RequestBody Employee employeeDetails){
//        return employeeService.updateEmployee(id,employeeDetails);
//    }
//
//
//    //delete employee
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id){
//        employeeService.deleteEmployeeById(id);
//    }
//
//
//    @GetMapping
//    public ResponseEntity<Page<Employee>> getAllEmployees(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "asc") String sortDir){
//
//        Page<Employee> employees=employeeService.getAllEmployees(page,size,sortBy,sortDir);
//        return ResponseEntity.ok(employees);
//    }
//
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String keyword){
//        List<Employee> result=employeeService.searchEmployeesByName(keyword);
//
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
//        Employee savedEmployee = employeeService.saveEmployee(employee);
//
//        ApiResponse<Employee> response = new ApiResponse<>(
//                "success",
//                "Employee created successfully",
//                savedEmployee
//        );
//
//        return ResponseEntity.ok(response);
//    }



import com.example.employeemanagement.EmployeeDto;
import com.example.employeemanagement.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create new employee
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);

        ApiResponse<Employee> response = new ApiResponse<>(
                "success",
                "Employee created successfully",
                savedEmployee
        );

        return ResponseEntity.ok(response);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
        EmployeeDto empDto = employeeService.getEmployeeById(id);
        ApiResponse<EmployeeDto> response = new ApiResponse<>(
                "success",
                "Employee fetched successfully",
                empDto
        );
        return ResponseEntity.ok(response);
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employeeDetails) {

        Employee updated = employeeService.updateEmployee(id, employeeDetails);

        ApiResponse<Employee> response = new ApiResponse<>(
                "success",
                "Employee updated successfully",
                updated
        );

        return ResponseEntity.ok(response);
    }

    //  Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        ApiResponse<String> response = new ApiResponse<>(
                "success",
                "Employee deleted successfully",
                "Employee with ID " + id + " deleted"
        );
        return ResponseEntity.ok(response);
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeDto>>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<EmployeeDto> employees = employeeService.getAllEmployees(page, size, sortBy, sortDir);

        ApiResponse<Page<EmployeeDto>> response = new ApiResponse<>(
                "success",
                "Employees fetched successfully",
                employees
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeDto>>> searchEmployees(@RequestParam String keyword) {
        List<EmployeeDto> result = employeeService.searchEmployeesByName(keyword);

        ApiResponse<List<EmployeeDto>> response = new ApiResponse<>(
                "success",
                "Search results found",
                result
        );

        return ResponseEntity.ok(response);
    }
}