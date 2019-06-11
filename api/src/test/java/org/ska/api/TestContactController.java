package org.ska.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ska.api.web.ContactController;
import org.ska.business.ContactService;
import org.ska.business.beans.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = KissApiConfiguration.class)
@SpringBootTest(classes = ContactController.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TestContactController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ContactService contactService;



    @Test
    public void testGetAll() throws Exception{
        ContactDTO contact=new ContactDTO();
        contactService.createContact(contact);
        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .get("/api/contacts/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<ContactDTO> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ContactDTO>>() {});
        Assert.assertNotNull(actual);
        org.springframework.util.Assert.notEmpty(actual);
    }
   
}
