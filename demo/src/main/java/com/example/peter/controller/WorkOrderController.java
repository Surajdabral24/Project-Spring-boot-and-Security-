package com.example.peter.controller;

import com.example.peter.constant.UserConstant;
import com.example.peter.entity.WorkOrder;
import com.example.peter.service.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkOrderController {

    private WorkOrderService workOrderService;

    @GetMapping("/workOrder/{id}")
    public ResponseEntity<WorkOrder> view(@PathVariable("id") long id){
        try{
            WorkOrder order = this.workOrderService.viewWork(id);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping("/workOrder")
    public ResponseEntity<String> add(WorkOrder workOrder){

        try{

            String str = this.workOrderService.addWork(workOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(str);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UserConstant.MESSAGE_205);
        }
    }

    @PutMapping("/workOrder/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id,@RequestBody WorkOrder workOrder){
        try{
            String str = this.workOrderService.updateWork(id,workOrder);
            return ResponseEntity.status(HttpStatus.OK).body(str);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UserConstant.MESSAGE_205);
        }

    }


    @GetMapping("/workOrder")
    public ResponseEntity<List<WorkOrder>> viewAll(){
        try{
           List<WorkOrder> li = this.workOrderService.viewAllWork();
            return ResponseEntity.status(HttpStatus.OK).body(li);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
