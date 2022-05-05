package org.example.departure.rest;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DeparturePayload {
    private String type;
    private String departureDate;

}
