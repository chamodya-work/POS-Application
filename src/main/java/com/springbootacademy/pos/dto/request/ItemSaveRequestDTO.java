package com.springbootacademy.pos.dto.request;

import com.springbootacademy.pos.entity.enums.MeasuringType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data // this lombok annotation represent above all things

public class ItemSaveRequestDTO {
    private String itemName;
    @Enumerated(EnumType.STRING)
    private MeasuringType measuringType;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;
}
