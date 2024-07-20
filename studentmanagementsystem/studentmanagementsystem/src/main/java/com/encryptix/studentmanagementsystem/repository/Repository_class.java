package com.encryptix.studentmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encryptix.studentmanagementsystem.model.Model_class;

public interface Repository_class extends JpaRepository<Model_class, Long> {

	List<Model_class> findByNameContainingIgnoreCase(String name);
}
