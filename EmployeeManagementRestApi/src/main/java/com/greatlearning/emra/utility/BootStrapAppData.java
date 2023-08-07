package com.greatlearning.emra.utility;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.emra.entity.Employee;
import com.greatlearning.emra.entity.Role;
import com.greatlearning.emra.entity.User;
import com.greatlearning.emra.repo.EmployeeRepo;
import com.greatlearning.emra.repo.RoleRepo;
import com.greatlearning.emra.repo.UserRepo;

@Configuration
public class BootStrapAppData {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public BootStrapAppData(EmployeeRepo employeeRepo, UserRepo userRepository, PasswordEncoder passwordEncoder) {
		this.employeeRepo = employeeRepo;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	//  employees
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void usersData(ApplicationReadyEvent readyEvent) {

		Employee Toby = new Employee("Toby", "Flenderson", "Toby@gmail.com");
		Employee Dwight = new Employee("Dwight", "Schrute", "Dwight@gmail.com");
		Employee Jim = new Employee("Jim", "Halpert", "Jim@gmail.com");

		this.employeeRepo.save(Toby);
		this.employeeRepo.save(Dwight);
		this.employeeRepo.save(Jim);

	}

	//  users
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAllData(ApplicationReadyEvent event) {
		User Michael = new User("Michael", passwordEncoder.encode("Michael"));
		User admin = new User("admin", passwordEncoder.encode("admin"));

		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");

		roleRepository.saveAndFlush(userRole);
		roleRepository.saveAndFlush(adminRole);

		admin.addRole(adminRole);
		Michael.addRole(userRole);

		userRepository.saveAndFlush(Michael);
		userRepository.saveAndFlush(admin);

	}

}
