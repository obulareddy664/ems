package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;

import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping(value="/employee")
	public String saveEmployee(@RequestBody Employee employee) {
		String status=null;
		if(employee.geteAge()>18) {
			status= employeeService.saveEmployee(employee);
		}else {
			status= "employee age is less than 18 come after 19 years thanks for reaching.";
		}
		return status;
	}
	
	@GetMapping("/employee")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@GetMapping("/employee/{eid}")
	public Employee getEmployeeById(@PathVariable Integer eid) {
		return employeeService.getEmployeeById(eid);
	}
}
