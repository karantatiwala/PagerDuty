import models.Developer;
import services.AlertingServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class startClass {
    public static void main (String[] args) {
        Developer d1 = new Developer(1, "dev1", "1231231231");
        Developer d2 = new Developer(2, "dev2", "1231231232");
        Developer d3 = new Developer(3, "dev3", "1231231233");
        Developer d4 = new Developer(4, "dev4", "1231231234");
        Developer d5 = new Developer(5, "dev5", "1231231235");

        AlertingServiceImpl alertService = new AlertingServiceImpl();

        List<Developer> devList1 = new ArrayList<>(Arrays.asList(d1, d2, d3));
        List<Developer> devList2 = new ArrayList<>(Arrays.asList(d4, d5));
        alertService.createTeam(1, "team1", devList1);
        alertService.createTeam(2, "team2", devList2);

        System.out.println("--------------------------------------");
        alertService.receiveAlert(3);
        System.out.println("--------------------------------------");
        alertService.receiveAlert(2);
    }
}


// Design pattern
// 1. abstraction (interface we have used)
// 2. functions at single responsibility
// 3. service layer could also have been single responsibility
// 4.

// tableName: team
// 1. id
// 2. teamName
//
// teamName: developer
// 1. id
// 2. name
// 3. phoneNumber,
// 4, email,
// 5. teamId
//
// newtable: teamdevmapping
// 1. teamId
// 2. developerId



