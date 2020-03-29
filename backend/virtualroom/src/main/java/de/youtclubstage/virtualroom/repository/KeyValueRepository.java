package de.youtclubstage.virtualroom.repository;

import de.youtclubstage.virtualroom.entity.KeyValue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KeyValueRepository extends MongoRepository<KeyValue,String> {
}
