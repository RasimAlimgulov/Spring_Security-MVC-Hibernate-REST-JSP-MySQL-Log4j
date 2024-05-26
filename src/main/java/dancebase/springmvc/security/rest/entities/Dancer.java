package dancebase.springmvc.security.rest.entities;

import javax.persistence.*;

@Entity
@Table(name = "dancer")
public class Dancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "year_of_birth")
    int year;
    @Column(name = "dance_club")
    String danceClub;

    public Dancer(){
    }

    public Dancer(String name, String surname, int year, String danceClub) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.danceClub = danceClub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDanceClub() {
        return danceClub;
    }

    public void setDanceClub(String danceClub) {
        this.danceClub = danceClub;
    }


}
