package es.udc.fi.tfg.model.entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDao extends JpaRepository<Notification, Long>{
	
	int countByUserIdAndLeidoFalse(Long userId);
	
	List<Notification> findByUserIdOrderByIdDesc(Long userId);
}