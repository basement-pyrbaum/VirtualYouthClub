package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.KeyValue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KeyValueRepository extends MongoRepository<KeyValue,String> {
}
