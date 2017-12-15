package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.ImageForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImageFormRepository extends JpaRepository<ImageForm, Long> {

    ImageForm findById(int id);

    @Transactional
    long deleteById(int id);
}
