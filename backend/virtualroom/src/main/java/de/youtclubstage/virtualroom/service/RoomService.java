package de.youtclubstage.virtualroom.service;

import de.youtclubstage.virtualroom.entity.Room;
import de.youtclubstage.virtualroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;

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
        if(roomRepository.count() == 0){
            roomRepository.save(new Room(UUID.randomUUID(),"Public"));
        }
        if(roomRepository.count() < 100){
            for(int x = 0; x < 100; x++) {
                roomRepository.save(new Room(UUID.randomUUID(), "Room"+x));
            }
        }

    }


    public Optional<Room> getRoom(UUID id) {
        return roomRepository.findById(id);
    }
}
