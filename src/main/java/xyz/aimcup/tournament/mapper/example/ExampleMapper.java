package xyz.aimcup.tournament.mapper.example;

import org.mapstruct.Mapper;
import xyz.aimcup.generated.model.ExampleDataResponse;
import xyz.aimcup.tournament.data.entity.Example;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExampleMapper {
    ExampleDataResponse exampleToExampleResponse(Example example);
    List<ExampleDataResponse> examplesToExampleResponses(List<Example> examples);
}
