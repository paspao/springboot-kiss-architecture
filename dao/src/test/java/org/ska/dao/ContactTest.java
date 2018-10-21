package org.ska.dao;

import org.ska.dao.configuration.KissDaoConfiguration;
import org.ska.dao.repository.ContactDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


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
    public void testInsertModulo() {

    }

}
