package com.booking.models;

import java.util.List;

import com.booking.repositories.ServiceRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    };



    private double calculateReservationPrice(){
        List<Service> listService = ServiceRepository.getAllService();
        double result = 0;
        double silver = 0.05;
        double gold = 0.1;
        String title = getCustomer().getMember().getMembershipName();
        for(Service list: listService){
            if(title.equalsIgnoreCase("Silver")){
                result = list.getPrice() - (list.getPrice() * silver); 
            }else if(title.equalsIgnoreCase("Gold")){
                result = list.getPrice() - (list.getPrice() * gold);
            }
        }
        
        return result;
    }
}
