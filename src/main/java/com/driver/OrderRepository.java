package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
   private HashMap<String,Order>orderHashMap;
   private HashMap<String,String>dpOrderMap;
   private HashMap<String,DeliveryPartner>DPHashMap;
   private HashMap<String, List<String>>dpOrderPair;

    public OrderRepository() {
        orderHashMap=new HashMap<>();
        dpOrderMap=new HashMap<>();
        DPHashMap=new HashMap<>();
        dpOrderPair=new HashMap<>();
    }

    void addOrder(Order order){
       orderHashMap.put(order.getId(),order);
   }
   void addDp(String deliveryPartner){
        if(!DPHashMap.containsKey(deliveryPartner))
       DPHashMap.put(deliveryPartner,new DeliveryPartner(deliveryPartner));
   }
   void orderDpPair(String orderId,String dpId){
       if(orderHashMap.containsKey(orderId)&&DPHashMap.containsKey(dpId)){
           if(dpOrderPair.containsKey(dpId)){
               dpOrderPair.get(dpId).add(orderId);
               DPHashMap.get(dpId).setNumberOfOrders(dpOrderPair.get(dpId).size());
           }
           else {
               List<String> t = new ArrayList<>();
               t.add(orderId);
               dpOrderPair.put(dpId, t);
               DPHashMap.get(dpId).setNumberOfOrders(dpOrderPair.get(dpId).size());
           }
           dpOrderMap.put(orderId,dpId);
       }
   }
   Order getOrder(String orderId){
   return orderHashMap.get(orderId);
   }
   DeliveryPartner getDP(String dpId){
       return DPHashMap.get(dpId);
   }
   int noOfOrdersToDp(String dpId){
       return dpOrderPair.get(dpId).size();
   }
   List<String> ListOfOrdersToDp(String dpId){
       return dpOrderPair.get(dpId);
   }
   List<String> ListOfAllOrdersToDp(){
       List<String>ans=new ArrayList<>(orderHashMap.keySet());
       return ans;
   }
   int noOfUAO(){
       return orderHashMap.size()-dpOrderMap.size();
   }
   int unDelivered(String time,String Partner){
       int n=(Integer.valueOf(time.substring(0,2))*60)+Integer.valueOf(time.substring(3));
       int ans=0;
       for(String x:dpOrderPair.get(Partner)){
           if(orderHashMap.get(x).getDeliveryTime()>n){
               ans++;
           }
       }
       return ans;
   }
   String lastDelivery(String Partner){
       int ans=0;
       for(String x:dpOrderPair.get(Partner)){
           if(orderHashMap.get(x).getDeliveryTime()>ans){
               ans=orderHashMap.get(x).getDeliveryTime();
           }
       }
       int h=ans/60;
       int minute=ans%60;
       String hh=String.valueOf(h);
       if(h<10){
           hh="0"+hh;
       }
       String min=String.valueOf(minute);
       if(minute<10){
            min="0"+min;
       }
       return hh+":"+min;
   }
   void DeletePartner(String id){
        for(String x:dpOrderMap.keySet()){
            if(dpOrderMap.get(x).equals(id)){
                dpOrderMap.remove(id);
            }
        }
       DPHashMap.remove(id);
       dpOrderPair.remove(id);
   }
   void DeleteOrder(String id){
       orderHashMap.remove(id);
       dpOrderPair.remove(dpOrderMap.get(id));
   }
}
