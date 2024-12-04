package com.example.project.repository.backup;

import com.example.project.repository.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupMembershipRepository extends JpaRepository<Membership, Long> {
}