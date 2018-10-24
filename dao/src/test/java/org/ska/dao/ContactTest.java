package org.ska.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ska.dao.configuration.KissDaoConfiguration;
import org.ska.dao.entity.Contact;
import org.ska.dao.repository.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;


/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 06/09/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KissDaoConfiguration.class)
@Transactional
public class ContactTest {


    @Autowired
    private ContactDao contactDao;



    @Test
    public void testInsertContact() {
        Contact contact=new Contact();
        contact.setBirthPlace("ASD");
        contact.setCountry("AAS");
        contact.setDateOfBirth(LocalDate.of(1991,5,21));
        contact.setEmail("asda@pip.it");
        contact.setFirstName("Francesco");
        contact.setLastName("LastName");
        contact.setFiscalCode("ASDASSDASD");
        contact.setGender("M");
        contact.setProvince("Napoli");
        contact.setPhoneNumber("234234234");
        contact.setCountry("ITALY");
        contact=contactDao.save(contact);
        Assert.notNull(contact.getId());
    }

    @Test
    public void tesFindAllContact() {
        Contact contact=new Contact();
        contact.setBirthPlace("ASD");
        contact.setCountry("AAS");
        contact.setDateOfBirth(LocalDate.of(1991,5,21));
        contact.setEmail("asda@pip.it");
        contact.setFirstName("Francesco");
        contact.setLastName("LastName");
        contact.setFiscalCode("ASDASSDASD");
        contact.setGender("M");
        contact.setProvince("Napoli");
        contact.setPhoneNumber("234234234");
        contact.setCountry("ITALY");
        contact=contactDao.save(contact);
        Assert.notEmpty((List)contactDao.findAll());
    }

}
