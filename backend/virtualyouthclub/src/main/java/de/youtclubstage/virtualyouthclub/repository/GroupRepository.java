package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GroupRepository extends MongoRepository<Group, UUID> {
}
