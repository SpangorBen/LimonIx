package com.spring.Citronix.validation;

import com.spring.Citronix.annotations.ValidDateSeason;
import com.spring.Citronix.dtos.HarvestRequestDTO;
import com.spring.Citronix.entities.enums.Season;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Month;

public class DateSeasonValidator implements ConstraintValidator<ValidDateSeason, HarvestRequestDTO> {

    @Override
    public boolean isValid(HarvestRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getDate() == null || dto.getSeason() == null) {
            return true;
        }

        LocalDate date = dto.getDate();
        Season season;
        try {
            season = Season.valueOf(dto.getSeason().toUpperCase());
        } catch (IllegalArgumentException e) {
            return true;
        }

        return isDateInSeason(date, season);
    }

    private boolean isDateInSeason(LocalDate date, Season season) {
        Month month = date.getMonth();
        switch (season) {
            case SPRING:
                return month == Month.MARCH || month == Month.APRIL || month == Month.MAY;
            case SUMMER:
                return month == Month.JUNE || month == Month.JULY || month == Month.AUGUST;
            case AUTUMN:
                return month == Month.SEPTEMBER || month == Month.OCTOBER || month == Month.NOVEMBER;
            case WINTER:
                return month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
            default:
                return false;
        }
    }
}
