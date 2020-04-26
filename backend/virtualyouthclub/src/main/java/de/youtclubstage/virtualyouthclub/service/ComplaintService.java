package de.youtclubstage.virtualyouthclub.service;


import de.youtclubstage.virtualyouthclub.controller.model.CreateComplaintDto;
import de.youtclubstage.virtualyouthclub.controller.model.MessageDTO;
import de.youtclubstage.virtualyouthclub.controller.model.MessageDetailDto;
import de.youtclubstage.virtualyouthclub.entity.User;
import de.youtclubstage.virtualyouthclub.entity.UserMessage;
import de.youtclubstage.virtualyouthclub.repository.MessageRepository;
import de.youtclubstage.virtualyouthclub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplaintService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public ComplaintService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void createComplaint(CreateComplaintDto createComplaintDto) {
        UserMessage userMessage = new UserMessage();
        userMessage.setComplaint(true);
        userMessage.setFrom(getUserId());
        userMessage.setSubject(createComplaintDto.getSubject());
        userMessage.setMessage(createComplaintDto.getMessage());
        messageRepository.save(userMessage);
    }


    private UUID getUserId(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID id = UUID.fromString(jwt.getClaimAsString("sub"));
        userRepository.save(new User(id,jwt.getClaimAsString("email")));
        return id;
    }

    public Page<MessageDTO> getComplaint(Pageable pageable) {
        return convert(messageRepository.findAllByIsComplaint(true,pageable));
    }

    public Long getUnreadComplains() {
        return messageRepository.countByIsComplaintAndRead(true,false);
    }

    public Optional<MessageDetailDto> getComplaint(UUID id) {
        Optional<UserMessage> userMessage = messageRepository.findById(id).filter(UserMessage::isComplaint);
        if(userMessage.isPresent()){
            userMessage.get().setRead(true);
            messageRepository.save(userMessage.get());
        }
        return userMessage.map(this::convertDetail);
    }

    private Page<MessageDTO> convert(Page<UserMessage> messages){
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (UserMessage message : messages){
            MessageDTO messageDTO = convert(message);
            messageDTOS.add(messageDTO);
        }
        return new PageImpl<>(messageDTOS,messages.getPageable(),messages.getTotalElements());
    }

    private MessageDTO convert(UserMessage message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom(userRepository.findById(message.getFrom()).orElse(new User()).getEmail());
        messageDTO.setId(message.getId());
        messageDTO.setCreateDate(message.getCreateDate());
        messageDTO.setSubject(message.getSubject());
        messageDTO.setRead(message.isRead());
        return messageDTO;
    }

    private Page<MessageDetailDto> convertDetail(Page<UserMessage> messages){
        List<MessageDetailDto> messageDTOS = new ArrayList<>();
        for (UserMessage message : messages){
            MessageDetailDto messageDTO = convertDetail(message);
            messageDTOS.add(messageDTO);
        }
        return new PageImpl<>(messageDTOS,messages.getPageable(),messages.getTotalElements());
    }

    private MessageDetailDto convertDetail(UserMessage message) {
        MessageDetailDto messageDTO = new MessageDetailDto();
        messageDTO.setFrom(userRepository.findById(message.getFrom()).orElse(new User()).getEmail());
        if(message.getTo() != null){
            messageDTO.setTo(userRepository.findById(message.getTo()).orElse(new User()).getEmail());
        }else{
            messageDTO.setTo("Team");
        }
        messageDTO.setId(message.getId());
        messageDTO.setCreateDate(message.getCreateDate());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setSubject(message.getSubject());
        messageDTO.setRead(message.isRead());
        return messageDTO;
    }

    public boolean deleteComplaint(UUID id) {
        if(messageRepository.findById(id).isPresent()){
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
