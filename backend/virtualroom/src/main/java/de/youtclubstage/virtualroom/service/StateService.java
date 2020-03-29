package de.youtclubstage.virtualroom.service;

import de.youtclubstage.virtualroom.entity.KeyValue;
import de.youtclubstage.virtualroom.repository.KeyValueRepository;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {

    private KeyValueRepository keyValueRepository;

    @Autowired
    public StateService(KeyValueRepository keyValueRepository){
        this.keyValueRepository = keyValueRepository;
    }

    public boolean isOpen(){
        Optional<KeyValue> isOpen = keyValueRepository.findById("open");
        if(!isOpen.isPresent()){
            keyValueRepository.save(new KeyValue("open","false"));

        }else if(isOpen.get().getValue().equalsIgnoreCase("true")){
            return true;
        }
        return false;
    }

    public void setOpen(boolean open) {
        keyValueRepository.save(new KeyValue("open",open?"true":"false"));
    }
}
