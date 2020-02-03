package com.cams.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "cams", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName","lastName"})
})
@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "dateOfBirth", "contactDetails"})
public class Customer extends AuditEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;

    @ElementCollection
    private List<ContactDetails> contactDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "customer")
    @JsonIgnore
    private List<Address> addressesList;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String gender, LocalDate dateOfBirth, List<ContactDetails> contactDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactDetails = contactDetails;
    }

}
