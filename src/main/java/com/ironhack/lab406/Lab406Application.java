package com.ironhack.lab406;

import com.ironhack.lab406.models.Department;
import com.ironhack.lab406.models.Product;
import com.ironhack.lab406.repositories.DepartmentRepository;
import com.ironhack.lab406.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab406Application implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab406Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		departmentRepository.saveAll(List.of(
				new Department("tools",new ArrayList<>()),
				new Department("edible plants",new ArrayList<>()),
				new Department("non-edible plants",new ArrayList<>()),
				new Department("edible seeds",new ArrayList<>()),
				new Department("non-edible seeds",new ArrayList<>()),
				new Department("miscellaneous",new ArrayList<>())
		));

		productRepository.saveAll(List.of(
				new Product(departmentRepository.findById(1L).get(),"small shovel",50L),
				new Product(departmentRepository.findById(1L).get(),"large shovel",150L),
				new Product(departmentRepository.findById(2L).get(),"apple tree sapling",10L),
				new Product(departmentRepository.findById(4L).get(),"assorted root vegetable seed packet",2000L),
				new Product(departmentRepository.findById(5L).get(),"geranium seed packet",1000L),
				new Product(departmentRepository.findById(2L).get(),"sprouted carrots",200L),
				new Product(departmentRepository.findById(6L).get(),"large brim gardening hat",25L),
				new Product(departmentRepository.findById(5L).get(),"cannabis seeds",250L),
				new Product(departmentRepository.findById(3L).get(),"cannabis sprout",25L)

		));
	}
}
