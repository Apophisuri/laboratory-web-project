package org.qvtu.laboratoryweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {
    private String labId; // 房间号
    private String labName; // 实验室名称
    private String labUsage; // 实验室用途
}
