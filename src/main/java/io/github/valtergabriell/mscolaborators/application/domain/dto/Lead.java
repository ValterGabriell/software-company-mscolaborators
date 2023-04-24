package io.github.valtergabriell.mscolaborators.application.domain.dto;


import io.github.valtergabriell.mscolaborators.application.domain.enumerations.Gender;
import io.github.valtergabriell.mscolaborators.application.domain.enumerations.TypePerson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean isActive;
    private Gender gender;
    private LocalDate bornDay;
    private TypePerson type;
    private LocalDate accountCreationDate;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBornDay() {
        return bornDay;
    }

    public void setBornDay(LocalDate bornDay) {
        this.bornDay = bornDay;
    }

    public TypePerson getType() {
        return type;
    }

    public void setType(TypePerson type) {
        this.type = type;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
