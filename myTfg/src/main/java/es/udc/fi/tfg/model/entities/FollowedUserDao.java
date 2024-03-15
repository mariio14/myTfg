package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowedUserDao extends JpaRepository<FollowedUser, Long> {

    Optional<FollowedUser> findByFollowed_IdAndFollower_Id(Long followedId, Long followerId);
}
