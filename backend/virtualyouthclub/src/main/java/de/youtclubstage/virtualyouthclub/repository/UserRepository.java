package de.youtclubstage.virtualyouthclub.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.management.modelmbean.ModelMBean;

public interface UserRepository extends MongoRepository<User,UUID> {
}
