package org.ska.business;

import org.ska.business.exceptions.KissBusinessException;
import org.ska.dao.entity.Contact;
import org.ska.dao.repository.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactDao contactDao;

	
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		
		return (List<Contact>) contactDao.findAll();
	}
	
	
	@Transactional(readOnly=true)
	public Contact findById(Integer moduloId) {
		
		Optional<Contact> optionalModulo = contactDao.findById(moduloId);
		if(optionalModulo.isPresent())
			return optionalModulo.get();
		else 
			return null;
	}



	public Contact createContact(Contact contact) {
		Contact mod=null;
		contact.setId(null);
		mod=contactDao.save(contact);
		return mod;

	}

	public Contact updateContact(Contact contact) throws KissBusinessException {
		Contact mod=null;
		if(contact.getId()==null)
			throw new KissBusinessException("Id required");
		mod=contactDao.save(contact);
		return mod;

	}
	
	public void deleteContct(Integer moduloId) {
    	
    	contactDao.deleteById(moduloId);
    	
    }


}
