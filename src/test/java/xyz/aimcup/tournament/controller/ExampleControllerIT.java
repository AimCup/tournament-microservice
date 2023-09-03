package xyz.aimcup.tournament.controller;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.aimcup.tournament.reusablecontainers.DatabaseContainerIT;

@ExtendWith(MockitoExtension.class)
class ExampleControllerIT extends DatabaseContainerIT {
    @Autowired
    private TournamentController tournamentController;

//    @Test
//    void shouldAddExampleToDatabase() {
//        // given
//        ExampleDataRequest exampleDataRequest = new ExampleDataRequest();
//        exampleDataRequest.setData("example data");
//
//        // when
//        String response = tournamentController.addNewExamples(exampleDataRequest).getBody();
//
//        // then
//        assertThat(response).isEqualTo("Example added");
//    }
//
//    @Test
//    void shouldFindOnlyOneExampleInDatabase() {
//        // given
//        ExampleDataRequest exampleDataRequest = new ExampleDataRequest();
//        exampleDataRequest.setData("example data 2");
//
//        // when
//        tournamentController.addNewExamples(exampleDataRequest).getBody();
//        tournamentController.addNewExamples(exampleDataRequest).getBody();
//        List<ExampleDataResponse> exampleList = tournamentController.getExamples().getBody();
//
//        // then
//        assertThat(exampleList).hasSizeGreaterThan(1);
//    }
}