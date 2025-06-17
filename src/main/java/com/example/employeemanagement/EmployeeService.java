package com.example.employeemanagement;

//old one before dto

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeService {
//
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    //save new employee or update existing one
//    public Employee saveEmployee(Employee employee){
//        logger.info("Saving new employee: {}",employee);
//        Employee saved = employeeRepository.save(employee);
//        logger.info("Saved employee: {}", saved);
//        return saved;
////        return employeeRepository.save(employee);
//    }
//
//    //get all employees
//    public List<Employee> getAllEmployees(){
//        logger.info("Getting all employees: ");
//        return employeeRepository.findAll();
//    }
//
//    //get employee by id
//    public Employee getEmployeeById(Long id){
//        logger.info("Fetching employee with id: {}", id);
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
//    }
//
//    //delete employee by id
//    public void deleteEmployeeById(Long id){
//        logger.info("Deleting employee with id: {}",id);
//        employeeRepository.deleteById(id);
//    }
//
//    //update an employee
//    public Employee updateEmployee(Long id, Employee employeeDetails){
//        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
//
//        employee.setName(employeeDetails.getName());
//        employee.setDepartment(employeeDetails.getDepartment());
//        employee.setSalary(employeeDetails.getSalary());
//
//        logger.info("Updating employee with id: {} with details: {}", id, employeeDetails);
//
//
//        return employeeRepository.save(employee);
//    }
//
//    public Page<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir){
//        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//
//        Pageable pageable= PageRequest.of(page,size,sort);
//        return employeeRepository.findAll(pageable);
//    }
//
//    public List<Employee> searchEmployeesByName(String keyword){
//        return employeeRepository.findByNameContainingIgnoreCase(keyword);
//    }



//new one after dto

import com.example.employeemanagement.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save new employee or update existing one
    public Employee saveEmployee(Employee employee) {
        logger.info("Saving new employee: {}", employee);
        Employee saved = employeeRepository.save(employee);
        logger.info("Saved employee: {}", saved);
        return saved; // still returning full entity here (used in POST/PUT only)
    }

    // Get employee by ID as DTO
    public EmployeeDto getEmployeeById(Long id) {
        logger.info("Fetching employee with id: {}", id);
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return convertToDto(emp);
    }

    // Get all employees (non-paginated)
    public List<EmployeeDto> getAllEmployees() {
        logger.info("Getting all employees");
        List<Employee> all = employeeRepository.findAll();
        return all.stream().map(this::convertToDto).toList();
    }

    // Get all employees (paginated + sorted)
    public Page<EmployeeDto> getAllEmployees(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(this::convertToDto); // convert each entity to DTO
    }

    // Search employees by name (DTO list)
    public List<EmployeeDto> searchEmployeesByName(String keyword) {
        logger.info("Searching employees by name: {}", keyword);
        return employeeRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Update an employee (returns full entity since it's used internally)
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());

        logger.info("Updating employee with id: {} with details: {}", id, employeeDetails);
        return employeeRepository.save(employee);
    }

    // Delete employee by ID
    public void deleteEmployeeById(Long id) {
        logger.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }

    // DTO converter method
    private EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }
}
