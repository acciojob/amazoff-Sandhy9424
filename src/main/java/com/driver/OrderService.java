package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    void addOrder(Order order){
        orderRepository.addOrder(order);
    }
    void addDp(String deliveryPartner){
        orderRepository.addDp(deliveryPartner);
    }
    void orderDpPair(String orderId,String dpId){
        orderRepository.orderDpPair(orderId,dpId);
    }
    Order getOrder(String orderId){
        return orderRepository.getOrder(orderId);
    }
    DeliveryPartner getDP(String dpId){
        return orderRepository.getDP(dpId);
    }
    Integer noOfOrdersToDp(String dpId){
        return orderRepository.noOfOrdersToDp(dpId);
    }
    List<String> ListOfOrdersToDp(String dpId){
        return orderRepository.ListOfOrdersToDp(dpId);
    }
    List<String> ListOfAllOrdersToDp(){
        return orderRepository.ListOfAllOrdersToDp();
    }
    Integer noOfUAO(){
        return orderRepository.noOfUAO();
    }
    Integer unDelivered(String time,String Partner){
        return unDelivered(time, Partner);
    }
    String lastDelivery(String Partner){
        return orderRepository.lastDelivery(Partner);
    }
    void DeletePartner(String id){
        orderRepository.DeletePartner(id);
    }
    void DeleteOrder(String id){
        orderRepository.DeleteOrder(id);
    }
}
