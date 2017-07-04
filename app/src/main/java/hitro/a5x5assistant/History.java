package hitro.a5x5assistant;

/**
 * Created by rohit on 1/3/2017.
 */

public class History {

    String date, workout;
    double squat, bench, row, ohp, dl;
    String doneSquat, doneBench, doneRow, doneOHP, doneDL;


    public History() {

    }

    public History(String date, String workout, double squat, double bench, double row, double ohp, double dl, String doneSquat, String doneBench, String doneRow, String doneOHP, String doneDL) {

        this.date = date;
        this.workout = workout;
        this.squat = squat;
        this.bench = bench;
        this.row = row;
        this.ohp = ohp;
        this.dl = dl;
        this.doneSquat = doneSquat;
        this.doneBench = doneBench;
        this.doneRow = doneRow;
        this.doneOHP = doneOHP;
        this.doneDL = doneDL;
    }

    /* getters */

    public String getDate() {
        return date;
    }

    public String getWorkout() {
        return workout;
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

    public String getDoneSquat() {
        return doneSquat;
    }

    public String getDoneBench() {
        return doneBench;
    }

    public String getDoneRow() {
        return doneRow;
    }

    public String getDoneOHP() {
        return doneOHP;
    }

    public String getDoneDL() {
        return doneDL;
    }



}
