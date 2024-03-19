package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApunteDao extends JpaRepository<Apunte, Long> {

    List<Apunte> findByPost_Id(Long postId);
}
