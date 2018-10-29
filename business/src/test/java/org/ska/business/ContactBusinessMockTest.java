package org.ska.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ska.business.beans.ContactDTO;
import org.ska.business.configuration.KissBusinessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= KissBusinessConfiguration.class)
@SpringBootTest
@Transactional
public class ContactBusinessMockTest {

	
	@Autowired
	private ContactService contactService;


	
	@Test
	public void testFindAllContacts() {
		ContactDTO contact=new ContactDTO();
		contactService.createContact(contact);
		Assert.notEmpty(contactService.findAll());
	}

	@Test
	public void testInsertContact(){
		ContactDTO contact=new ContactDTO();
		contact=contactService.createContact(contact);
		Assert.notNull(contact.getId());

	}

}
