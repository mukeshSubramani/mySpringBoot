package org.example.domains;


import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id = Uuids.timeBased();

   // @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    //@NotBlank(message = "Delivery street is required")
    private String deliveryStreet;
    //@NotBlank(message = "Delivery city is required")
    private String deliveryCity;
    //@NotBlank(message = "Delivery state is required")
    private String deliveryState;
    //@NotBlank(message = "Delivery Zip is required")
    private String deliveryZip;
    //@CreditCardNumber(message = "Not a valid Credit Card number")
    private String ccNumber;
//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
//            message = "Must be formatted MM/YY")
    private String ccExpiration;

   // @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Date placedAt;

    @Column("tacos")
    private List<TacoUTD> tacoList = new ArrayList<>();

    public void addTaco(Taco taco){
        tacoList.add(new TacoUTD(taco.getNameTaco(),taco.getIngredientListTaco()));
    }
}
