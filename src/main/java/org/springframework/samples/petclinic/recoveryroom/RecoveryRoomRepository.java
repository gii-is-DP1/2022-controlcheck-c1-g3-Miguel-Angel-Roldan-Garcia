package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer> {
    List<RecoveryRoom> findAll();
    Optional<RecoveryRoom> findById(int id);
    RecoveryRoom save(RecoveryRoom p);
    
    @Query("SELECT recovery_room_type FROM RecoveryRoomType recovery_room_type")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();
    
    @Query("SELECT recovery_room_type FROM RecoveryRoomType recovery_room_type WHERE recovery_room_type.name =:name")
    RecoveryRoomType getRecoveryRoomType(@Param("name") String name);
}
