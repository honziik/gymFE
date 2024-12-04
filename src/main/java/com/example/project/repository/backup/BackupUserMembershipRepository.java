package com.example.project.repository.backup;

import com.example.project.repository.entities.UserMembership;
import com.example.project.repository.entities.UserMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackupUserMembershipRepository extends JpaRepository<UserMembership, UserMembershipId> {
    List<UserMembership> findByUserId(Long userId);
}