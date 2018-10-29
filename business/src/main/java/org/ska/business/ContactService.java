package org.ska.business;

import com.github.dozermapper.core.Mapper;
import org.ska.business.beans.ContactDTO;
import org.ska.business.exceptions.KissBusinessException;
import org.ska.business.utils.UtilsBusiness;
import org.ska.dao.entity.Contact;
import org.ska.dao.repository.ContactDao;
import org.ska.integration.beans.GeocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactDao contactDao;

	@Autowired
	private Mapper dozerBeanMapper;

	@Autowired
	private GeocodeService geocodeService;

	
	@Transactional(readOnly=true)
	public List<ContactDTO> findAll() {
		List<Contact> contacts=(List<Contact>) contactDao.findAll();
		List<ContactDTO> contactDTOS=null;
		if(!UtilsBusiness.isEmpty(contacts))
		{
			contactDTOS=new ArrayList<>();
			for(Contact contact:contacts)
			{
				ContactDTO contactDTO=dozerBeanMapper.map(contact,ContactDTO.class);
				contactDTOS.add(contactDTO);
			}
		}

		return contactDTOS;
	}
	
	
	@Transactional(readOnly=true)
	public ContactDTO findById(Integer moduloId) {
		
		Optional<Contact> optionalModulo = contactDao.findById(moduloId);
		if(optionalModulo.isPresent())
			return dozerBeanMapper.map(optionalModulo.get(),ContactDTO.class);
		else 
			return null;
	}



	public ContactDTO createContact(ContactDTO contact) {
		Contact mod=null;
		ContactDTO result=null;
		contact.setId(null);
		mod=dozerBeanMapper.map(contact,Contact.class);
		// Test address geocodeService.geocode()
		mod=contactDao.save(mod);
		result=dozerBeanMapper.map(mod,ContactDTO.class);
		return result;

	}

	public ContactDTO updateContact(ContactDTO contact) throws KissBusinessException {
		Contact mod=null;
		ContactDTO res=null;
		if(contact.getId()==null)
			throw new KissBusinessException("Id required");
		// Test address geocodeService.geocode()
		mod=dozerBeanMapper.map(contact,Contact.class);
		mod=contactDao.save(mod);
		res=dozerBeanMapper.map(mod,ContactDTO.class);
		return res;

	}
	
	public void deleteContct(Integer moduloId) {
    	
    	contactDao.deleteById(moduloId);
    	
    }


}
