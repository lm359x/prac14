package org.example.postoffice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostOffice {
    private UUID id;
    private String name;
    private String cityName;

    public PostOffice(String name, String cityName) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cityName = cityName;
    }

    public boolean notSameId(UUID id){
        return !(this.getId().equals(id));
    }
}
