package de.youtclubstage.virtualyouthclub.entity;

import de.youtclubstage.virtualyouthclub.controller.model.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    private UUID id;
    private String groupName;
    private List<UUID> groupAdmins = new ArrayList<>();
    private List<UUID> groupUsers = new ArrayList<>();
    private boolean isOpen = false;


    public GroupDto toGroupDto(){
        return new GroupDto(id,groupName,isOpen,groupAdmins);
    }
}
