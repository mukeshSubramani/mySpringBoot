package org.example.domains;



import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Delivery street is required")
    private String deliveryStreet;
    @NotBlank(message = "Delivery city is required")
    private String deliveryCity;
    @NotBlank(message = "Delivery state is required")
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Date placedAt;

    private List<Taco> tacoList = new ArrayList<>();

    public void addTaco(Taco taco){
        tacoList.add(taco);
    }
}
