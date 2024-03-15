package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

@Entity
public class FollowedUser {

    private Long id;

    private Users follower;

    private Users followed;

    public FollowedUser() {
    }

    public FollowedUser(Users follower, Users followed) {
        this.follower = follower;
        this.followed = followed;
        this.follower.getFollowedUsers().add(this);
        this.followed.getFollowerUsers().add(this);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "followerId")
    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "followedId")
    public Users getFollowed() {
        return followed;
    }

    public void setFollowed(Users followed) {
        this.followed = followed;
    }
}
