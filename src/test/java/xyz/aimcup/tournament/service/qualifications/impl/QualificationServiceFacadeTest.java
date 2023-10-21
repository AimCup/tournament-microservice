package xyz.aimcup.tournament.service.qualifications.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aimcup.tournament.service.qualifications.QualificationService;
import xyz.aimcup.tournament.service.qualifications.QualificationServiceFacade;
import xyz.aimcup.tournament.service.qualifications.exceptions.QualificationServiceNotFoundException;

@ExtendWith(MockitoExtension.class)
class QualificationServiceFacadeTest {

    @Mock
    private List<QualificationService> qualificationServices;

    @InjectMocks
    private QualificationServiceFacade qualificationServiceFacade;


    @Test
    void shouldThrowQualificationServiceNotFoundExceptionWhenNoServiceForQualificationTypeWasFound() {
        // when and then
        assertThatThrownBy(() -> qualificationServiceFacade.getQualificationService(null))
            .isInstanceOf(QualificationServiceNotFoundException.class)
            .hasMessage("No QualificationService found for type: " + null);

    }

}