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

    @PostConstruct
    public void defaultData(){
        roomRepository.deleteAll();
        if(roomRepository.count() == 0){
            roomRepository.save(new Room(UUID.randomUUID(),"Public"));
        }
    }


    public Optional<Room> getRoom(UUID id) {
        return roomRepository.findById(id);
    }

    public void addRoom(String name) {
        roomRepository.save(new Room(UUID.randomUUID(),name));
    }
}
