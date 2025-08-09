package com.walkway.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@Table(name = "inventory_location")
public class InventoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "location_address", nullable = true)
    private String locationAddress;

    @Column(name = "location_city", nullable = false)
    private String locationCity;

    @Column(name = "location_postal", nullable = false)
    private Integer locationPostal;

    @Column(name = "location_state", nullable = false)
    private String locationState;

}
