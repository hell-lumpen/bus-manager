package com.aviahack.busmanagerapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aviahack.busmanagerapplication.entities.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BusTaskController {
    private final List<BusTask> tasks = List.of(
            new BusTask(100, 200, 150),
            new BusTask(50, 150, 30),
            new BusTask(100, 200, 150)

    );
    public List<BusTask> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public BusTask getById(@PathVariable Long id) {
        return tasks.stream()
                .filter(developer -> developer.getBusTaskId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
