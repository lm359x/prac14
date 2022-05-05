package org.example;

import org.example.departure.Departure;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class Testing {
    public static void main(String[] args) {
        List<Departure> list = new ArrayList<>();
        list.add(new Departure("asd","asd"));
        list.add(new Departure("zxc","zxc"));
        list.add(new Departure("asasd","zxc"));
        Departure departure = new Departure("cxz","cxz");
        list.add(departure);
        Stream<Departure> stream = list.stream();
        List<Departure> toUpdate = stream.filter(x-> x.notEqualId(departure)).toList();
        stream.close();
        System.out.println(toUpdate.size());
    }
}
