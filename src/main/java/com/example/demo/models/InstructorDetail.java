package com.example.demo.models;

import jakarta.persistence.*;

@Entity(name = "instructor_details")
public class InstructorDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

//    Column 'instructor_id' is duplicated in mapping for entity 'com.example.demo.models.InstructorDetail'
//    (use '@Column(insertable=false, updatable=false)' when mapping multiple properties to the same column
//    @Column(name = "instructor_id")
//    private Integer instructorId;

    @OneToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public InstructorDetail() {
    }

    public InstructorDetail(String youtubeChannel, String hobby, Instructor instructor) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
        this.instructor = instructor;
    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
