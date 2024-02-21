package com.example.peter.service;


import com.example.peter.emun.Role;
import com.example.peter.emun.Status;
import com.example.peter.entity.User;
import com.example.peter.entity.WorkOrder;
import com.example.peter.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public String addWork(WorkOrder workOrder){
        this.workOrderRepository.save(workOrder);
        return "work add successfully";
        }

    public WorkOrder viewWork(long workId){
        return this.workOrderRepository.findById(workId).orElseThrow(null);

    }

    public String updateWork(long workId,WorkOrder order){
            WorkOrder workOrder = this.workOrderRepository.findById(workId).orElseThrow(null);
            if(workOrder == null){
                return "work is not added yet..";
            }
            else{
                workOrder.setOrderStatus(order.getOrderStatus());
                workOrder.setServiceType(order.getServiceType());
                workOrder.setServiceProviderType(order.getServiceProviderType());
                workOrder.setDescription(order.getDescription());
                workOrder.setQuestionsAndInstruction(order.getQuestionsAndInstruction());
                workOrder.setUser(new User());
                this.workOrderRepository.save(workOrder);
                return "work is updated";
            }
    }

    public List<WorkOrder> viewAllWork(){
        return this.workOrderRepository.findAll();

    }


}

