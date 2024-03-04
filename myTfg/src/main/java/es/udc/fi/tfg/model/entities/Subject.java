package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    private Long id;

    private String subjectName;

    private University university;

    private Set<FollowedSubject> followedSubjects = new HashSet<>();

    public Subject() {
    }

    public Subject(String subjectName, University university) {
        this.subjectName = subjectName;
        this.university = university;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "universityId")
    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @OneToMany(mappedBy = "subject")
    public Set<FollowedSubject> getFollowedSubjects() {
        return followedSubjects;
    }

    public void setFollowedSubjects(Set<FollowedSubject> followedSubjects) {
        this.followedSubjects = followedSubjects;
    }
}
