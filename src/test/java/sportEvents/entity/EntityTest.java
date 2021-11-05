package sportEvents.entity;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityTest {

    @Autowired
    protected EntityManager em;

    @Test
    protected void persist(Object entity){
        //Key: Id, value: Encja
        em.persist(entity); // add to cache
        em.flush(); // send cache to db: insert into address...
        em.clear(); //clear cache

    }


}
