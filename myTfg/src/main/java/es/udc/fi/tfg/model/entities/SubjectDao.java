package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject, Long> {

    List<Subject> findByUniversityId(Long universityId);
}
