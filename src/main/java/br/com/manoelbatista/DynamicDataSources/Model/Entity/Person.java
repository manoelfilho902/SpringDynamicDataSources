/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Model.Entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
public class Person {

    private String FirstName;
    private String LastName;
    private Integer age;
    private LocalDate birthday;

    public Person() {
    }

    public Person(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Integer getAge() {
        if (birthday != null) {
            LocalDate now = LocalDate.now(ZoneId.systemDefault());
            age = Period.between(birthday, now).getYears();
        }
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
