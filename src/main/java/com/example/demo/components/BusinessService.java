package com.example.demo.components;

import com.example.demo.entities.Table;
import com.example.demo.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class BusinessService extends ReloadModel {

    @Autowired
    private TableRepository tableRepository;

    //boolean ---> isAvailable
    Map<Integer,Map<Table, Boolean>> tableMap;

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
            tableMap.get(table.getBusinessId()).put(table,true);
        }
    }

    public void addTable(int businessId){
        Table table = new Table();
        table.setTableId(tableMap.get(businessId).size());
        table.setBusinessId(businessId);

        tableMap.get(businessId).put(table,true);

        tableRepository.save(table);
    }

    public void removeTable(int businessId){

    }
}
