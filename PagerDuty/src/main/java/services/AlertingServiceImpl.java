package services;

import models.Developer;
import models.Team;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AlertingServiceImpl implements AlertingService {
    public Map<Integer, List<Developer>> teamDeveloperMap;

    public AlertingServiceImpl() {
        this.teamDeveloperMap = new HashMap<>();
    }

    public void createTeam(int teamId, String teamName, List<Developer> developers) {
        if(!teamDeveloperMap.containsKey(teamId)) {
            teamDeveloperMap.put(teamId, new ArrayList<>());
            Team team = new Team(teamId, teamName);
        }
        for(Developer dev: developers) {
            dev.setTeam(teamId);
            teamDeveloperMap.get(teamId).add(dev);
        }
    }

    public void receiveAlert(int teamId) {
        try {
            if (!teamDeveloperMap.containsKey(teamId)) {
                throw new Exception("TeamId does not Exist !!");
            }
            List<Developer> teamDevList = teamDeveloperMap.get(teamId);
            if (teamDevList.size() == 0) {
                throw new Exception("developers does not Exist !!");
            }
            Random r = new Random();
            Developer dev = teamDevList.get(r.nextInt(teamDevList.size()));

            try {
                sendSMSAlert(dev);
            } catch (IOException e) {
                System.out.println("Error Sending Alert");
                // retry mechanism
                // recoverable exceptions
            }
        } catch (Exception e) {
            System.out.println("TeamId does not Exist !!");
        }
    }

    public void sendSMSAlert(Developer dev) throws IOException {
        System.out.println("Sending SMS");
        HttpURLConnection con = null;
        var url = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";
        var urlParameters = "name=Jack&occupation=programmer";
//        String jsonInputString = "{\n" + "\"phone_number\":" + dev.getPhone_number() + ",\r\n" +
//                "    \"message\": 5xx,\r\n" +
//               "}";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

//        try(OutputStream os = con.getOutputStream()) {
//            byte[] input = jsonInputString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
        try {
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
        System.out.println(dev.getId());
        System.out.println("SMS sent successfully");
    }
}
