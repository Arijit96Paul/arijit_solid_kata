package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tddmicroexercises.telemetrysystem.TelemetryService.TelemetryService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class TelemetryDiagnosticControlsTest {
    private TelemetryService mockTelemetryService;
    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @BeforeEach
    public void setUp() {
        mockTelemetryService = Mockito.mock(TelemetryService.class);
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(mockTelemetryService);
    }
    
	@Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        when(mockTelemetryService.getOnlineStatus()).thenReturn(true);
        when(mockTelemetryService.receive()).thenReturn("Diagnostic test");

        telemetryDiagnosticControls.checkTransmission();

        Assertions.assertEquals("Diagnostic test", telemetryDiagnosticControls.getDiagnosticInfo());

    }
    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_Error() throws Exception {
        when(mockTelemetryService.getOnlineStatus()).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () -> {
            telemetryDiagnosticControls.checkTransmission();
        });

        Assertions.assertEquals("Unable to connect.", exception.getMessage());
    }
    @Test
    public void setDiagnosticInfoTest() {
        telemetryDiagnosticControls.setDiagnosticInfo("test");
        Assertions.assertEquals("test", telemetryDiagnosticControls.getDiagnosticInfo());

    }

}
