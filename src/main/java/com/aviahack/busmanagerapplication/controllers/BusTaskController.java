package com.aviahack.busmanagerapplication.controllers;

import org.springframework.web.bind.annotation.*;
import com.aviahack.busmanagerapplication.entities.*;

import java.util.List;

@RestController
@CrossOrigin("localhost:3000")
@RequestMapping("/api/v1/task")
public class BusTaskController {
    private final List<BusTask> tasks = List.of(
            new BusTask(1L, 100, 200, 150),
            new BusTask(2L, 50, 150, 30),
            new BusTask(3L, 100, 200, 150)

    );
    @GetMapping("/all")
    public List<BusTask> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public BusTask getById(@PathVariable Long id) {
        return tasks.stream()
                .filter(task -> task.getBusTaskId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
