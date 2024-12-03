package com.example.project.repository;

import com.example.project.repository.entities.UserMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMembershipRepository extends JpaRepository<UserMembership, UserMembershipId> {
    List<UserMembership> findByUserId(Long userId);
}