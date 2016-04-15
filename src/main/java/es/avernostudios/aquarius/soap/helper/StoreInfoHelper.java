package es.avernostudios.aquarius.soap.helper;

import es.avernostudios.aquarius.bean.Store;
import java.util.HashMap;
import es.avernostudios.aquarius.jpa.repositories.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author yrch
 */
@Slf4j
public class StoreInfoHelper {

    @Autowired
    static StoreRepository storeRepository;

    public static HashMap<Integer, Store> getInfo() {
        HashMap<Integer, Store> result = new HashMap<>();

        try {

            Iterable<Store> intermedia =storeRepository.findAll();
            for (Store store : intermedia) {
                result.put(store.getId(), store);
            }


        } catch (Exception e) {
            LOGGER.error("Exception",e);
        }



        return result;
    }
}
