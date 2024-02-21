package org.example.repo;

import org.example.domains.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public interface OrderRepo extends CrudRepository<TacoOrder, UUID> {

    List<TacoOrder> findByDeliveryZip(String zip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String zip, Date start, Date end);

}
