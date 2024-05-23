package tddmicroexercises.telemetrysystem.TelemetryService;

public interface TelemetryService {
    void connect(String connectionString);
    void disconnect();
    boolean getOnlineStatus();
    void send(String message);
    String receive();
}
