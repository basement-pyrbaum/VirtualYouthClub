package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends MongoRepository<Room, UUID> {
    Optional<Room> findByIdAndGroupIsNull(UUID id);
    Optional<Room> findByIdAndGroup(UUID id,UUID group);
    Page<Room> findAllByGroupIsNull(Pageable pageable);
    Page<Room> findAllByNameContainingIgnoreCaseAndGroupIsNull(String search, Pageable pageable);
    Page<Room> findAllByGroup(UUID group, Pageable pageable);
    Page<Room> findAllByNameContainingIgnoreCaseAndGroup(String search,UUID group, Pageable pageable);
}
