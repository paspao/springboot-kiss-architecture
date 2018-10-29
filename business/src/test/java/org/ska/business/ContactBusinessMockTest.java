package org.ska.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ska.business.beans.ContactService;
import org.ska.business.configuration.KissBusinessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= KissBusinessConfiguration.class)
@SpringBootTest
@Transactional
public class ContactBusinessMockTest {

	
	@Autowired
	private ContactService contactService;

	public ContactBusinessMockTest(){

	}
	
	@Test
	public void testFindAllModulo() {

	}

}
