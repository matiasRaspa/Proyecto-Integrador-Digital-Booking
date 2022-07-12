package com.proyectointegrador.proyecto_Integrador_CTD.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatesUtil {


    public List<LocalDate> getIntervalDateList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dateList = new java.util.ArrayList<>();
        if (endDate.getYear() != startDate.getYear()) {
            //calculate the days in the first year
            int daysInFirstYear = LocalDate.of(startDate.getYear(), 12, 31).getDayOfYear() - startDate.getDayOfYear();
            int daysInSecondYear = endDate.getDayOfYear();
            int daysInterval = daysInFirstYear + daysInSecondYear + 1;
            for (int i = 0; i < daysInterval; i++) {
                dateList.add(startDate.plusDays(i));
            }
        } else {
            int numberOfDays = (endDate.getDayOfYear() - startDate.getDayOfYear()) + 1;

            for (int i = 0; i < numberOfDays; i++) {
                dateList.add(startDate.plusDays(i));
            }

        }
        return dateList;
    }
}
