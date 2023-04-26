package io.github.valtergabriell.mscolaborators.application.domain;

import io.github.valtergabriell.mscolaborators.application.domain.enumerations.Gender;
import io.github.valtergabriell.mscolaborators.application.domain.enumerations.TypePerson;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Colaborator {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean isActive;
    private Gender gender;
    private LocalDate bornDay;
    private TypePerson typePerson;
    private LocalDate hireDate;
    private String password;
    private Long lead;

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

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePerson typePerson) {
        this.typePerson = typePerson;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLead() {
        return lead;
    }

    public void setLead(Long lead) {
        this.lead = lead;
    }
}
