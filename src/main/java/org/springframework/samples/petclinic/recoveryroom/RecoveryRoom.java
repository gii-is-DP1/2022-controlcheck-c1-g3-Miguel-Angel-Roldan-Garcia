package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recovery_rooms")
public class RecoveryRoom extends BaseEntity {
	
	@Column(name = "name")
	@NotEmpty
	@Size(min=3, max= 50)
    String name;
	
	@Column(name = "size")
	@NotNull
	@PositiveOrZero
    double size;
	
	@Column(name = "secure")
	@NotNull
    boolean secure;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "room_type_id")
	@NotNull
    RecoveryRoomType roomType;
}
