package com.itau.desafioitau.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaDTO {
    
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;
}
