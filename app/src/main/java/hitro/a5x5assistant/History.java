package hitro.a5x5assistant;

/**
 * Created by rohit on 1/3/2017.
 */

public class History {

    String date, workout, squat, bench, row, ohp, dl;
    boolean doneSquat, doneBench, doneRow, doneOHP, doneDL;


    public History() {

    }

    public History(String date, String workout, String squat, String bench, String row, String ohp,
                   String dl, boolean doneSquat, boolean doneBench, boolean doneRow, boolean doneOHP,
                   boolean doneDL ) {

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

    String getWorkout() {
        return this.workout;
    }

    String getDate() {
        return this.date;
    }

    String getSquat() {
        return this.squat;
    }

    String getBench() {
        if (this.bench.equals(null)){
            return "-";
        }
        else{
            return this.bench;
        }
    }

    String getRow() {
        if (this.row.equals(null)){
            return "-";
        }
        else{
            return this.row;
        }
    }

    String getOhp() {
        if (this.ohp.equals(null)){
            return "-";
        }
        else{
            return this.ohp;
        }
    }

    String getDl() {
        if (this.dl.equals(null)){
            return "-";
        }
        else{
            return this.dl;
        }
    }

    String getDoneSquat() {
        if (!doneSquat) return "-";
        else return "Completed";
    }

    String getDoneBench() {
        if (!doneBench) return "-";
        else return "Completed";
    }

    String getDoneRow() {
        if (!doneRow) return "-";
        else return "Completed";
    }

    String getDoneOHP() {
        if (!doneOHP) return "-";
        else return "Completed";
    }

    String getDoneDL() {
        if (!doneDL) return "-";
        else return "Completed";
    }

}
