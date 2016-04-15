package es.avernostudios.aquarius.jpa.repositories;

import es.avernostudios.aquarius.bean.Product;
import es.avernostudios.aquarius.spring.config.RepositoryConfig;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dortega on 15/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfig.class})
public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testListAllProducts() throws Exception {

        Iterable<Product> toTest = productRepository.findAll();
        assertTrue(toTest != null);

    }
}