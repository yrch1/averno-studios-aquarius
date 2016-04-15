package es.avernostudios.aquarius.jpa.repositories;

import es.avernostudios.aquarius.bean.Product;
import es.avernostudios.aquarius.bean.Store;
import es.avernostudios.aquarius.spring.config.RepositoryConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by dortega on 15/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfig.class})
public class StoreRepositoryTest {

    private StoreRepository storeRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

        Iterable<Store> toTest = storeRepository.findAll();
        assertTrue(toTest != null);

    }
}