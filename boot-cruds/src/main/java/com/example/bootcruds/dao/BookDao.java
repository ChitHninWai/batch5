package com.example.bootcruds.dao;

import com.example.bootcruds.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book,Integer> {
}
