package com.example.demo.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TableStatusResponseVo {
    private List<Map<Integer,Boolean>> tableStatus;
}
