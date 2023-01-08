package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrderToMap(order);
    }

    public void addPartner(String partnerID){
        orderRepository.addDeliveryPartnerToMap(partnerID);
    }

    public void addOrderPartnerPair(String orderID,String partnerID){
        orderRepository.addPartnerOrderPair(partnerID,orderID);
    }

    public Order getOrderByID(String orderID){
        return orderRepository.getOrderByOrderID(orderID);
    }

    public DeliveryPartner getPartnerByID(String partnerID){
        return orderRepository.getPartnerByPartnerID(partnerID);
    }

    public int getOrderCountByPartnerID(String partnerID){
        return orderRepository.getOrderCountByPartnerID(partnerID);
    }

    public List<String> getOrdersByPartnerID(String partnerID){
        return orderRepository.getOrdersByPartnerID(partnerID);
    }

    public List<String> getAllOrders(){
        return orderRepository.getListOfAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public void deletePartnerByID(String partnerID){
        orderRepository.deletePartnerByID(partnerID);
    }

    public void deleteOrderByID(String orderID){
        orderRepository.deleteOrderByID(orderID);
    }
}
