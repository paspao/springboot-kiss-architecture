package org.ska.api.web;
/**
 * 
 * @author nikola
 *
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ska.api.util.UtilsWeb;
import org.ska.api.web.beans.ErrorMessage;
import org.ska.business.ContactService;
import org.ska.business.beans.ContactDTO;
import org.ska.business.exceptions.KissBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contacts")
@Api(value="/api/contacts", description = "Contacts Management")
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;


	
	
	
	@ApiOperation(value = "Retrieve all contacts", response = ContactDTO.class,responseContainer = "list")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Contact not found",response=Void.class)
	})
	
	@RequestMapping(value ="/all",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getAllContacts(){
		ResponseEntity<?> responseEntity=null;
		List<ContactDTO> contactList = null;
		contactList = contactService.findAll();

		if(UtilsWeb.isEmpty(contactList))
			responseEntity=new ResponseEntity(HttpStatus.NOT_FOUND);
		else {
			ContactDTO[] contactArray = contactList.toArray(new ContactDTO[contactList.size()]);
			responseEntity = new ResponseEntity<ContactDTO[]>(contactArray, HttpStatus.OK);
		}
		return responseEntity;
	}


	@ApiOperation(value = "Save contact", response = ContactDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Contact saving error",response= ErrorMessage.class)
	})
	@RequestMapping(value ="/save",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<?> saveContact(@RequestBody ContactDTO contact){
		ResponseEntity<?> responseEntity=null;

		ContactDTO insertedContact= null;
		try {
			insertedContact = contactService.createContact(contact);
			responseEntity = new ResponseEntity<ContactDTO>(insertedContact, HttpStatus.OK);
		} catch (KissBusinessException e) {
			logger.error("Error saving: {}",e.getMessage(),e);
			ErrorMessage errorMessage=new ErrorMessage();
			errorMessage.setMessage(e.getMessage());
			responseEntity=new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		}



		return responseEntity;
	}



	@ApiOperation(value = "Delete contact", response = Void.class)
	@RequestMapping(value ="/contatti/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
	public  @ResponseBody
	ResponseEntity<?>  deleteContact(@PathVariable int id) {
		ResponseEntity<?> responseEntity=null;
		contactService.deleteContact(id);
		responseEntity = new ResponseEntity<Void>( HttpStatus.OK);
		return responseEntity;
	}


	@ApiOperation(value = "Find contact", response = ContactDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "No contacts",response= ErrorMessage.class)
	})
	@RequestMapping(value ="/contatti/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
	public  @ResponseBody
	ResponseEntity<?>  findContact(@PathVariable int id) {
		ResponseEntity<?> responseEntity=null;
		ContactDTO contact=null;
		contact=contactService.findById(id);
		if(contact==null)
			responseEntity = new ResponseEntity( HttpStatus.NOT_FOUND);
		else
			responseEntity = new ResponseEntity<ContactDTO>(contact, HttpStatus.OK);

		return responseEntity;
	}

}
