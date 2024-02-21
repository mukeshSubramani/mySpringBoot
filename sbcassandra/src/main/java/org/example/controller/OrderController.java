package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.domains.TacoOrder;
import org.example.repo.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Controller
@RequestMapping("/orders")
@Log4j2
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepo orderRepo;

    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderFinalForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder( TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            log.info("Taco object validation failed {}" ,errors.getAllErrors());
            return "orderForm";
        }
        log.info("Saving order , please wait");
        tacoOrder.setPlacedAt(new Date());
        orderRepo.save(tacoOrder);
        log.info("Order Submitted: {}",tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
