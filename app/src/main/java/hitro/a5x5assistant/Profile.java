package hitro.a5x5assistant;

/**
 * Created by rohit on 11/13/2016.
 */

public class Profile {

    private String name;
    private double body, squat, bench, row, ohp, dl;

    public Profile() {

    }


    public Profile(String name, double body, double squat, double bench, double row, double ohp, double dl) {
        this.name = name;
        this.body = body;
        this.squat = squat;
        this.bench = bench;
        this.row = row;
        this.ohp = ohp;
        this.dl = dl;
    }

    public String getName() {
        return name;
    }
    public double getBody() {
        return body;
    }
    public double getSquat() {
        return squat;
    }
    public double getBench() {
        return bench;
    }
    public double getRow() {
        return row;
    }
    public double getOhp() {
        return ohp;
    }
    public double getDl() {
        return dl;
    }
}

