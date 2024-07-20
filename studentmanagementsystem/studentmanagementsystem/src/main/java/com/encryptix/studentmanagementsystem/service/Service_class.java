package com.encryptix.studentmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encryptix.studentmanagementsystem.model.Model_class;
import com.encryptix.studentmanagementsystem.repository.Repository_class;

@Service
public class Service_class {
	 @Autowired
	    private Repository_class studentRepository;

	   
	    public Model_class addStudent(Model_class student) {
	        return studentRepository.save(student);
	    }

	
	    public void removeStudent(Long id) {
	        studentRepository.deleteById(id);
	    }

	  
	    public Model_class searchStudent(Long id) {
	        Optional<Model_class> student = studentRepository.findById(id);
	        return student.orElse(null);
	    }
	    
	    public List<Model_class> searchStudentByName(String name) {
	        return studentRepository.findByNameContainingIgnoreCase(name);
	    }


	    public List<Model_class> getAllStudents() {
	        return studentRepository.findAll();
	    }
}
