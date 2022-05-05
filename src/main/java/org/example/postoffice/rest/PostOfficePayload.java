package org.example.postoffice.rest;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PostOfficePayload {
    private String name;
    private String cityName;
}
