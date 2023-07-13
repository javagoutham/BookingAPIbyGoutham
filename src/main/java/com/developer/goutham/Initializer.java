package com.developer.goutham;

import com.developer.goutham.model.Event;
import com.developer.goutham.model.Group;
import com.developer.goutham.model.GroupRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Goutham", "digicert", "sandton",
            "capetown").forEach(name ->
            repository.save(new Group(name))
        );

        Group djug = repository.findByName("Goutham");
        Event e = Event.builder().title("Booking API Application for Hotel")
            .description("hotel booking service that allows you to list, create, update and delete a reservation")
            .date(Instant.parse("2023-07-11T17:00:00.000Z"))
            .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
