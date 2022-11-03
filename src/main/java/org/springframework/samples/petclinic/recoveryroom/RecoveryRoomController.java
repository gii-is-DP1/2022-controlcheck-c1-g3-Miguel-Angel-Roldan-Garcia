package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {
    
	private static final String VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";
	
	RecoveryRoomService recoveryRoomService;
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService recoveryRoomService) {
		this.recoveryRoomService = recoveryRoomService;
	}
	
	
	@GetMapping(value = "/recoveryroom/create")
	public String initCreationForm(ModelMap model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
		model.put("recoveryRoom", recoveryRoom);
		return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/recoveryroom/create")
	public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("RecoveryRoom", recoveryRoom);
			return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
		}
		else {
			try {
				recoveryRoomService.save(recoveryRoom);
			} catch (DuplicatedRoomNameException e) {
				result.addError(new ObjectError("recoveryRoom", "Duplicated recovery room name"));
				model.put("RecoveryRoom", recoveryRoom);
				return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
			}
			return "welcome";
		}
	}
	
}
