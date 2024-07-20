package com.encryptix.studentmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encryptix.studentmanagementsystem.model.Model_class;
import com.encryptix.studentmanagementsystem.service.Service_class;

import jakarta.validation.Valid;

@Controller
public class Controller_class {

    @Autowired
    private Service_class studentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("find",new Model_class());
        	return "index";
    }
    @RequestMapping("/search")
    public String search_form(Model model) {
    	model.addAttribute("find", new Model_class());
    	return"search";
    }
    @PostMapping("/search")
    public String search(Model model,@ModelAttribute("find")Model_class details) {
    	String find=details.getSearch();
    	List<Model_class> search;
    	search=studentService.searchStudentByName(find);
        model.addAttribute("students", search);
    	return "display";
    	
    }
    @RequestMapping("/display")
    public String dispaly(Model model) {
    	List<Model_class> students = studentService.getAllStudents();
        model.addAttribute("students", students);
    	return "display";
    }
    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
    	model.addAttribute("student", new Model_class());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Model_class student, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("student", new Model_class());
            return "add-student";
        }
        studentService.addStudent(student);
        return "added";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Model_class student = studentService.searchStudent(id);
        if (student == null) {
            throw new IllegalArgumentException("Invalid student Id:" + id);
        }
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Model_class student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        studentService.addStudent(student);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        studentService.removeStudent(id);
        return "delete";
    }
}