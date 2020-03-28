package de.youtclubstage.virtualroom.repository;

import de.youtclubstage.virtualroom.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RoomRepository extends MongoRepository<Room, UUID> {
    Page<Room> findAllByNameContaining(String search, Pageable pageable);
}
