package models;

public class Developer {
    private int id;
    private int  teamId;
    private String name;
    private String phone_number;

    public Developer(int id, String name, String phone_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setTeam(int teamId) {
        this.teamId = teamId;
    }
}
