package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
//    @Transactional
//    @Query("Delete FROM UserGoal ug, User u WHERE u.id = ug.user AND u.email = :email AND ug.goal = :goal")
//    long deleteByGoal(String email, String goal);

	UserGoal findByUserId(int userId);

    @Transactional
    long deleteByUserId(int userId);
    @Transactional
    long deleteByUserEmail(String Email);
}
