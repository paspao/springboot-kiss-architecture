package org.ska.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "CONTACTS")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(length=32, name = "FIRST_NAME")
	private String firstName;// Nome
	@Column(length=25, name = "BIRTH_PLACE")
	private String birthPlace;// Comune di nascita
	@Column(length=32, name = "LAST_NAME")
	private String lastName;// Cognome
	@Column(name = "BIRTH_DATE")
	private LocalDate dateOfBirth;// Data di nascita
	@Column(length=20, name = "COUNTRY")
	private String country;// Nazione di nascita
	@Column(length=1, name = "GENDER")
	private String gender;// Sesso
	@Column(length=20, name = "PHONE")
	private String phoneNumber;// Telefono
	@Column(length=50, name = "EMAIL")
	private String email;// Email
	@Column(length=50, name = "ADDRESS")
	private String address;
	@Column(name = "TIME")
	private LocalDateTime timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}






