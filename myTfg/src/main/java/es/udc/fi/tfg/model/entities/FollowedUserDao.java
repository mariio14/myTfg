package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowedUserDao extends JpaRepository<FollowedSubject, Long> {
}
