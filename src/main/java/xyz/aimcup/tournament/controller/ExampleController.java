package xyz.aimcup.tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.aimcup.tournament.data.entity.Example;
import xyz.aimcup.tournament.data.repository.ExampleRepostiory;
import xyz.aimcup.tournament.model.request.ExampleDataRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleRepostiory exampleRepostiory;

    @GetMapping("/examples")
    public List<Example> getExamples() {
        return exampleRepostiory.findAll();
    }

    @PostMapping("/examples")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewExample(@RequestBody ExampleDataRequest exampleDataRequest) {
        exampleRepostiory.save(Example.builder()
                .data(exampleDataRequest.getData())
                .build());
        return "Example added";
    }
}
