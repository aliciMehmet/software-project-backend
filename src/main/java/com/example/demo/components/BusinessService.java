package com.example.demo.components;

import com.example.demo.entities.Table;
import com.example.demo.repositories.TableRepository;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class BusinessService extends ReloadModel {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    //boolean ---> isOccupied
    public Map<Integer,Map<Integer, Boolean>> tableMap;

    @PostConstruct
    public void started()
    {
        tableMap = new LinkedHashMap<>();
    }

    @Override
    public void reload(){
        for (Table table : tableRepository.findAll()) {
            if (!tableMap.containsKey(table.getBusinessId()))
            {
                tableMap.put(table.getBusinessId(), new HashMap<>());
            }

            tableMap.get(table.getBusinessId()).put(table.getTableId(),false);

            if(!orderService.orderMap.containsKey(table.getBusinessId()))
            {
                orderService.orderMap.put(table.getBusinessId(),new HashMap<>());
            }

            if(!orderService.orderMap.get(table.getBusinessId()).containsKey(table.getId())){
                orderService.orderMap.get(table.getBusinessId()).put(table.getId(),new ArrayList<>());
            }
        }
    }

    public void addTable(int businessId){
        Table table = new Table();
        table.setTableId(tableMap.get(businessId).size());
        table.setBusinessId(businessId);

        tableMap.get(businessId).put(table.getTableId(),true);

        tableRepository.save(table);
    }

    public void removeTable(int businessId){

    }

    public void resetTable(int businessId,int tableId){
        tableMap.get(businessId).put(tableId,false);
    }

    public List<User> getEmployee(String role){
        return userRepository.getByRole(role);
    }
}


