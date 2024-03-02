package org.example.repo;

import org.example.domains.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface OrderRepo extends CrudRepository<TacoOrder, String> {

    List<TacoOrder> findByDeliveryZip(String zip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String zip, Date start, Date end);

}
