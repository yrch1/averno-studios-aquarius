package es.avernostudios.aquarius.jpa.repositories;

import es.avernostudios.aquarius.bean.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by dortega on 15/04/2016.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

}