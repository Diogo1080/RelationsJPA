package com.example.relationsjpa.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {

    private Long id;
    private String name;
    private int age;
    private Long teamId;
}
