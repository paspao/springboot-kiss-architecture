package org.ska.dao.repository;

import org.ska.dao.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends CrudRepository<Contact, Integer> {




}
