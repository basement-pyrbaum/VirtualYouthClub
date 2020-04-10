package de.youtclubstage.virtualyouthclub.repository;

import de.youtclubstage.virtualyouthclub.entity.UserMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.UUID;

public interface MessageRepository extends MongoRepository<UserMessage, UUID> {
    Page<UserMessage> findAllByIsComplaint(boolean complaint, Pageable page);

    Long countByIsComplaintAndRead(boolean complaint, boolean read);
}
