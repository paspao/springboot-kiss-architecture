package org.ska.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "CONTACTS")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(length=32, name = "NAME")
	private String firstName;// Nome
	@Column(length=25, name = "PROVINCIA")
	private String province;// Provincia di nascita
	@Column(length=25, name = "COMUNE")
	private String birthPlace;// Comune di nascita
	@Column(length=32, name = "SURNAME")
	private String lastName;// Cognome
	@Column(name = "BIRTH_DATE")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;// Data di nascita
	@Column(length=20, name = "NATION")
	private String country;// Nazione di nascita
	@Column(length=1, name = "SEX")
	private String gender;// Sesso
	@Column(length=50, name = "FISCAL_CODE")
	private String fiscalCode;// Codice fiscale
	@Column(length=20, name = "PHONE")
	private String phoneNumber;// Telefono
	@Column(length=50, name = "EMAIL")
	private String email;// Email

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
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

}






