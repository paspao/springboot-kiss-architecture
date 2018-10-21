package org.ska.business;

import org.ska.business.configuration.KissBusinessConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= KissBusinessConfiguration.class)
@SpringBootTest
@Transactional
public class ContactBusinessMockTest {



	private List<String> roles;

	
	@Autowired
	private ContactService contactService;

	public ContactBusinessMockTest(){

	}
	
	@Test
	public void testFindAllModulo() {

	}

}
