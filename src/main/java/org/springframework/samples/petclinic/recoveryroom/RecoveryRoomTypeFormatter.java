package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{
	
	private final RecoveryRoomService recoveryRoomService;

	@Autowired
	public RecoveryRoomTypeFormatter(RecoveryRoomService recoveryRoomService) {
		this.recoveryRoomService = recoveryRoomService;
	}
	
    @Override
    public String print(RecoveryRoomType type, Locale locale) {
        return type.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
    	RecoveryRoomType recoveryRoomType = recoveryRoomService.getRecoveryRoomType(text);
        
        if(recoveryRoomType != null) {
        	return recoveryRoomType;
        }
     
        throw new ParseException("type not found: " + text, 0);
    }
    
}
