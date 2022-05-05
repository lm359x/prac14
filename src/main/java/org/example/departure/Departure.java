package org.example.departure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Departure {
    private UUID id;
    private String type;
    private String departureDate;

    public Departure(String type, String departureDate) {
        this.type = type;
        this.departureDate = departureDate;
        this.id = UUID.randomUUID();
    }

    public boolean notEqualId(UUID is){
        return !(this.getId().equals(id));
    }
}
