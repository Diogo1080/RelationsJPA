package com.example.relationsjpa.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeachesDto {
    private Long mentorId;
    private Long teamId;
}
