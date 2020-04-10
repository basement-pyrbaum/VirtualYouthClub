package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
