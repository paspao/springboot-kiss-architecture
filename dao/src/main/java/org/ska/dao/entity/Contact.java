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
	@Column(length=32, name = "NOME")
	private String firstName;// Nome
	@Column(length=25, name = "PROVINCIA_NASCITA")
	private String province;// Provincia di nascita
	@Column(length=25, name = "COMUNE_NASCITA")
	private String birthPlace;// Comune di nascita
	@Column(length=32, name = "COGNOME")
	private String lastName;// Cognome
	@Column(name = "DATA_NASCITA")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;// Data di nascita
	@Column(length=20, name = "NAZIONE_NASCITA")
	private String country;// Nazione di nascita
	@Column(length=10, name = "SESSO")
	private String gender;// Sesso
	@Column(length=50, name = "CODICE_FISCALE")
	private String fiscalCode;// Codice fiscale
	@Column(length=20, name = "TELEFONO")
	private String phoneNumber;// Telefono
	@Column(length=50, name = "EMAIL")
	private String email;// Email
	@Column(length=50, name = "PROVINCIA_RESIDENZA")
	private String provinceOfresidence;// Provincia di residenca
	@Column(length=30)
	private String municipality;// Comune di residenca
	@Column(length=50, name = "VIA_RESIDENZA")
	private String street;// Via
	@Column(length=5, name = "CIVICO_RESIDENZA")
	private int streetNumber;// N Civico
	@Column(length=20, name = "CAP_RESIDENZA")
	private int postalCode;// CAP

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

	public String getProvinceOfresidence() {
		return provinceOfresidence;
	}

	public void setProvinceOfresidence(String provinceOfresidence) {
		this.provinceOfresidence = provinceOfresidence;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
}






