package com.ems.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.ems.model.Employee;

@Repository
public class EmployeeRepository {

	public int saveEmployee(Employee employee) {
		int value = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertest", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employee values(?,?,?);");
			preparedStatement.setInt(1, employee.geteId());
			preparedStatement.setString(2, employee.geteName());
			preparedStatement.setInt(3, employee.geteAge());

			value = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public int updateEmployee(Employee employee) {
		int value = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertest", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement("update  employee  set name=? where id=?;");
			preparedStatement.setString(1, employee.geteName());
			preparedStatement.setInt(2, employee.geteId());
			value = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public List<Employee> getEmployees() {

		List<Employee> employees = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertest", "root", "root");
			preparedStatement = connection.prepareStatement("select * from employee;");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Employee employee=new Employee();
				employee.seteId(resultSet.getInt("id"));
				employee.seteName(resultSet.getString("name"));
				employee.seteAge(resultSet.getInt("age"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null) {
				resultSet.close();
				}else if (preparedStatement !=null) {
					preparedStatement.close();
				}else if(connection !=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return employees;
	}
	
	public Employee getEmployeeById(Integer eid) {

		Employee employee=new Employee();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertest", "root", "root");
			preparedStatement = connection.prepareStatement("select * from employee where id=?");
			preparedStatement.setInt(1, eid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				employee.seteId(resultSet.getInt("id"));
				employee.seteName(resultSet.getString("name"));
				employee.seteAge(resultSet.getInt("age"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null) {
				resultSet.close();
				}else if (preparedStatement !=null) {
					preparedStatement.close();
				}else if(connection !=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return employee;
	}
	
	public int removeEmployeeById(Integer id) {
		int value = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertest", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement("delete from employee where id=?;");
			preparedStatement.setInt(1, id);
		
			value = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
