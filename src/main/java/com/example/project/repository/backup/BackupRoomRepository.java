package com.example.project.repository.backup;

import com.example.project.repository.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupRoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);
}
