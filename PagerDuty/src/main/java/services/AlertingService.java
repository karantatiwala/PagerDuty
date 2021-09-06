package services;

import models.Developer;

import java.io.IOException;
import java.util.List;

public interface AlertingService {
    void createTeam(int teamId, String teamName, List<Developer> developers);

    void receiveAlert(int teamId);

    void sendSMSAlert(Developer dev) throws IOException;
}


