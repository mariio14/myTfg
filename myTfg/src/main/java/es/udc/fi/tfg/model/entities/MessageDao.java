package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageDao extends JpaRepository<Message, Long> {

    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByDate(Long id, Long id2, Long id3, Long id4);

    @Query("SELECT m FROM Message m WHERE (m.sender.id = :id1 AND m.receiver.id = :id2) OR (m.sender.id = :id2 AND m.receiver.id = :id1) ORDER BY m.date")
    List<Message> findMessagesBetweenPairs(@Param("id1") Long id1, @Param("id2") Long id2);
}
