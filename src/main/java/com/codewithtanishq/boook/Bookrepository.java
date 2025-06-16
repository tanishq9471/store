package com.codewithtanishq.boook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Bookrepository extends JpaRepository<Book, Long> {
    @Override
    Optional<Book> findById(Long id);
}