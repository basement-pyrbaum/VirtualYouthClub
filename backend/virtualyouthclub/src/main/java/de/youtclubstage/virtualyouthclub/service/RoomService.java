package de.youtclubstage.virtualyouthclub.service;

import de.youtclubstage.virtualyouthclub.entity.Room;
import de.youtclubstage.virtualyouthclub.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Page<Room> getRooms(String search, Pageable pageable){
        if(search==null){
            return roomRepository.findAll(pageable);
        }else{
            return roomRepository.findAllByNameContainingIgnoreCase(search,pageable);
        }
    }

    public Optional<Room> getRoom(UUID id) {
        return roomRepository.findById(id);
    }

    public void addRoom(String name) {
        roomRepository.save(new Room(UUID.randomUUID(),name));
    }

    public boolean delete(UUID id) {
        if(roomRepository.existsById(id)){
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
