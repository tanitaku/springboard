package com.example.demo;

import com.example.demo.SpringBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<SpringBoard, Long> {
	public SpringBoard findById(int id);
	public void deleteById(int id);

}
