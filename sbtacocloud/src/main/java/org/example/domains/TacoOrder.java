package org.example.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Delivery street is required")
    private String deliveryStreet;
    @NotBlank(message = "Delivery city is required")
    private String deliveryCity;
    @NotBlank(message = "Delivery state is required")
    private String deliveryState;
    //@NotBlank(message = "Delivery Zip is required")
    private String deliveryZip;
    //@CreditCardNumber(message = "Not a valid Credit Card number")
    private String ccNumber;
//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
//            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Date placedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacoList = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addTaco(Taco taco){
        tacoList.add(taco);
    }
}
