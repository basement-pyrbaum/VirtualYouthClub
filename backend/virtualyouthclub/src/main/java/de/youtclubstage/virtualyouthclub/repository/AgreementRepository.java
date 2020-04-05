package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.Agreement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AgreementRepository extends MongoRepository<Agreement, UUID> {
}
