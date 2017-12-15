package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByFileName(String fileName);

    Image findById(int id);

    @Query("SELECT i FROM Eats e, Food f, User u, Image i WHERE f.id = e.food AND u.id = e.user AND i.id = e.image AND u.email = :email")
    List<Image> findByEmail(@Param("email") String email);

    @Transactional
    long deleteByFileName(String fileName);

}
