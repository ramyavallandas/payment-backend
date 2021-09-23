package com.dbs.web.rest;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Employee;
import com.dbs.web.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;
}
