package xyz.aimcup.tournament.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.aimcup.tournament.data.entity.Example;
import xyz.aimcup.tournament.model.request.ExampleDataRequest;
import xyz.aimcup.tournament.reusablecontainers.DatabaseContainerIT;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExampleControllerIT extends DatabaseContainerIT {
    @Autowired
    private ExampleController exampleController;

    @Test
    void shouldAddExampleToDatabase() {
        // given
        ExampleDataRequest exampleDataRequest = ExampleDataRequest.builder()
                .data("example data")
                .build();
        // when
        String response = exampleController.addNewExample(exampleDataRequest);

        // then
        assertThat(response).isEqualTo("Example added");
    }

    @Test
    void shouldFindOnlyOneExampleInDatabase() {
        // given
        ExampleDataRequest exampleDataRequest = ExampleDataRequest.builder()
                .data("example data 2")
                .build();
        // when
        exampleController.addNewExample(exampleDataRequest);
        List<Example> exampleList = exampleController.getExamples();

        // then
        assertThat(exampleList).hasSize(1);
    }
}