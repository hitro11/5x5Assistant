package hitro.a5x5assistant;

/**
 * Created by rohit on 1/3/2017.
 */

public class HistoryB {

    private double squat, ohp, dl;
    private String date, workout;
    private boolean squatDone, ohpDone, dlDone;

    public HistoryB() {  }

    public HistoryB(String date, double squat, double ohp, double dl, boolean squatDone,
                    boolean ohpDone, boolean dlDone) {

        this.date = date;
        this.workout = "B";
        this.squat = squat;
        this.ohp = ohp;
        this.dl = dl;
        this.squatDone = squatDone;
        this.ohpDone = ohpDone;
        this.dlDone = dlDone;
    }

    /* getters */
    public String getDate() {
        return date;
    }
    public double getSquat() {
        return squat;
    }
    public double getOhp() {
        return ohp;
    }
    public double getDL() {
        return dl;
    }
    public boolean getSquatDone() {
        return squatDone;
    }
    public boolean getOHPdone() {
        return ohpDone;
    }
    public boolean getDLdone() { return dlDone; }

}
