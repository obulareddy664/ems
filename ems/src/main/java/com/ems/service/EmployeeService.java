package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public String saveEmployee(Employee employee) {
		
		int status= employeeRepository.saveEmployee(employee);
		
		if(status==1) {
			return "Inserted recorde in db successfully "+status;
		}else {
			return "Not insertesd in database "+status;
		}
	}
	
	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}
	
	public Employee getEmployeeById(Integer eid) {
		return employeeRepository.getEmployeeById(eid);
	}
	
	public int updateEmployee(Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}
	
	public int removeEmployeeById(Integer id) {
		return employeeRepository.removeEmployeeById(id);
	}
}
