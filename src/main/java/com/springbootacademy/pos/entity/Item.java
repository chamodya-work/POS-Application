package com.springbootacademy.pos.entity;
import com.springbootacademy.pos.entity.enums.MeasuringType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data // this lombok annotation represent above all things



public class Item {
    @Id
    @Column(name = "item_id", length = 55)
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // this is for auto generation id
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;
    @Enumerated(EnumType.STRING)
    @Column(name="measuring_type",length = 100,nullable = false)
    private MeasuringType measuringType;

    @Column(name="balance_qty",length = 100,nullable = false)
    private double balanceQty;
    @Column(name="supplier_price",length = 100,nullable = false)
    private double supplierPrice;
    @Column(name="selling_price",length = 100,nullable = false)
    private double sellingPrice;
    @Column(name="active_state",columnDefinition = "TINYINT default 0")
    private boolean activeState;

}
