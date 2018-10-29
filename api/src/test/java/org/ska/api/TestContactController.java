package org.ska.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ska.api.web.ContactController;
import org.ska.business.beans.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = KissApiConfiguration.class)
@SpringBootTest(classes = ContactController.class)
@Transactional
public class TestContactController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestContactController.class);



    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ContactService contactService;



    @Test
    public void testGetAll() {

    }
   
}
