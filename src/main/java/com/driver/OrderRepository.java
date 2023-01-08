package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private HashMap<String,Order> orderMap;
    private HashMap<String,DeliveryPartner> deliveryPartnerMap;

    private HashMap<String, List<String>> partnerOrderPairMap;

    private HashMap<String,Order> unasignedOrderMap;


    public OrderRepository(){
        this.orderMap = new HashMap<>();
        this.deliveryPartnerMap = new HashMap<>();
        this.partnerOrderPairMap = new HashMap<>();
        this.unasignedOrderMap = new HashMap<>();
    }

    public void addOrderToMap(Order order){
        orderMap.put(order.getId(),order);
    }

    public void addDeliveryPartnerToMap(String partnerID){

        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerID);
        deliveryPartnerMap.put(partnerID,deliveryPartner);
    }

    public void addPartnerOrderPair(String partnerID,String orderID){

        if(deliveryPartnerMap.containsKey(partnerID) && orderMap.containsKey(orderID)){

            List<String> orderList = new ArrayList<>();

            if(partnerOrderPairMap.containsKey(partnerID)){
                orderList = partnerOrderPairMap.get(partnerID);
            }

            orderList.add(orderID);
            partnerOrderPairMap.put(partnerID,orderList);
            deliveryPartnerMap.get(partnerID).setNumberOfOrders(orderList.size());

        }
    }

    public Order getOrderByOrderID(String orderID){
        return orderMap.get(orderID);
    }

    public DeliveryPartner getPartnerByPartnerID(String partnerID){
        return deliveryPartnerMap.get(partnerID);
    }

    public int getOrderCountByPartnerID(String partnerID){
        int count = partnerOrderPairMap.get(partnerID).size();
        return count;
    }

    public List<String> getOrdersByPartnerID(String partnerID){
        List<String> ordersOfPartner = new ArrayList<>();
        if(partnerOrderPairMap.containsKey(partnerID)){

            ordersOfPartner = partnerOrderPairMap.get(partnerID);
        }

        return ordersOfPartner;
    }
    public List<String> getListOfAllOrders(){
        List<String> allOrders = new ArrayList<>(orderMap.keySet());
        return allOrders;
    }

    public int getCountOfUnassignedOrders(){
        int totalOrdersAssigned=0;
        int totalOrders=0;
        for(DeliveryPartner dp:deliveryPartnerMap.values()){
            totalOrdersAssigned += dp.getNumberOfOrders();
        }
        for(String order:orderMap.keySet()){
            totalOrders++;
        }
        return totalOrders-totalOrdersAssigned;

    }

    public void deletePartnerByID(String partnerID){

        for(String partner : partnerOrderPairMap.keySet()){

            if(partner.equals(partnerID)){
                for(String dp: deliveryPartnerMap.keySet()){
                    if(dp.equals(partnerID)){
                        deliveryPartnerMap.remove(dp);
                    }
                }
                partnerOrderPairMap.remove(partner);
            }
        }
    }

    public void deleteOrderByID(String orderID){

        for(String order:orderMap.keySet()){
            if(order.equals(orderID)){
                for(List<String> list: partnerOrderPairMap.values() ){
                    if(list.contains(orderID)){
                        list.remove(orderID);
                    }
                }
                orderMap.remove(orderID);
            }
        }
    }



}
