package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowedSubjectDao extends JpaRepository<FollowedSubject, Long> {

    Optional<FollowedSubject> findByUser_IdAndSubject_Id(Long userId, Long subjectId);
}
