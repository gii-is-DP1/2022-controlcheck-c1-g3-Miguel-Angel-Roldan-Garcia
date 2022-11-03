package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecoveryRoomService {
	
	RecoveryRoomRepository recoveryRoomRepository;
	
	@Autowired
	public RecoveryRoomService(RecoveryRoomRepository recoveryRoomRepository) {
		this.recoveryRoomRepository = recoveryRoomRepository;
	}
	
	@Transactional(readOnly = true)
    public List<RecoveryRoom> getAll(){
        return recoveryRoomRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return null;
    }

    @Transactional(readOnly = true)
    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return recoveryRoomRepository.getRecoveryRoomType(typeName);
    }

    @Transactional(rollbackFor = DuplicatedRoomNameException.class)
    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
    	List<RecoveryRoom> rooms = getAll();
    	for(RecoveryRoom r:rooms) {
    		if(r.getName().equals(p.getName())) {
    			throw new DuplicatedRoomNameException();
    		}
    	}
        return recoveryRoomRepository.save(p);       
    }

    
}
