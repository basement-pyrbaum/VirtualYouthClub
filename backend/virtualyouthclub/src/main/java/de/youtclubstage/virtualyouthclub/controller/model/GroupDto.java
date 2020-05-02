package de.youtclubstage.virtualyouthclub.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private UUID id;
    private String groupName;
    private boolean isOpen;
    private List<UUID> admins = new ArrayList<>();
}
