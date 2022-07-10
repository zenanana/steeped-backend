package com.zenan.steepedbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SESSION_TBL")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "tea_id")
    private int teaId;
    @Column(name = "date")
    private Date date;
    @Column(name = "rating")
    private int rating;
    @Column(name = "notes")
    private String notes;

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void replaceFields(Session desiredSession) {
        this.setDate(desiredSession.getDate());
        this.setNotes(desiredSession.getNotes());
        this.setRating(desiredSession.getRating());
        this.setTeaId(desiredSession.getTeaId());
        this.setUserId(desiredSession.getUserId());
    }
}
