package de.youtclubstage.virtualyouthclub.service;

import de.youtclubstage.virtualyouthclub.entity.Room;
import de.youtclubstage.virtualyouthclub.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            return roomRepository.findAllByGroupIsNull(pageable);
        }else{
            return roomRepository.findAllByNameContainingIgnoreCaseAndGroupIsNull(search,pageable);
        }
    }

    public Optional<Room> getRoom(UUID id) {
        return roomRepository.findByIdAndGroupIsNull(id);
    }

    public Optional<Room> getRoom(UUID group,UUID id) {
        return roomRepository.findByIdAndGroup(id,group);
    }

    public void addRoom(String name) {
        addRoom(null,name);
    }

    public boolean delete(UUID id) {
        Optional<Room> room = getRoom(id);
        if(room.isPresent()){
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Room> getRoomsByGroup(UUID groupId, String search, Pageable page) {
        if(search==null){
            return roomRepository.findAllByGroup(groupId,page);
        }else{
            return roomRepository.findAllByNameContainingIgnoreCaseAndGroup(search,groupId,page);
        }
    }

    public boolean delete(UUID groupId, UUID id) {
        Optional<Room> room = getRoom(groupId,id);
        if(room.isPresent()){
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addRoom(UUID groupId, String name) {
        roomRepository.save(new Room(UUID.randomUUID(),name,groupId));
    }
}
