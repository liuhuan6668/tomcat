package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Stu;

public interface StuDao extends JpaRepository<Stu, Long> {

	Stu findAllById(Long id);

}
