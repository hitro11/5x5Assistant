package hitro.a5x5assistant;

/**
 * Created by rohit on 1/3/2017.
 */

public class History {

    public String workout, doneSquat, doneBench, doneRow, doneOHP, doneDL;
    public int date, squat, bench, row, ohp, dl;

    public History () {

    }


    /* getters */

    public String getWorkout() {
        return this.workout;
    }

    public String getDate() {
        return Integer.toString(this.date);
    }


    public String getSquat() { return Integer.toString(this.squat);  }
    public String getBenchW() {
        if (this.bench == 0) return "-";
        else return Integer.toString(this.bench);
    }
    public String getRowW() {
        if (this.row == 0) return "-";
        else return Integer.toString(this.row);
    }
    public String getOhpW() {
        if (this.ohp == 0) return "-";
        else return Integer.toString(this.ohp);
    }
    public String getDlW() {
        if (this.dl == 0) return "-";
        else return Integer.toString(this.dl);
    }


    public String getDoneSquat() {
        if (doneSquat == null) return "-";
        else return doneSquat;
    }

    public String getDoneBench() {
        if (doneBench == null) return "-";
        else return doneBench;
    }

    public String getDoneRow() {
        if (doneRow == null) return "-";
        else return doneRow;
    }

    public String getDoneOHP() {
        if (doneOHP == null) return "-";
        else return doneOHP;
    }

    public String getDoneDL() {
        if (doneDL == null) return "-";
        else return doneDL;
    }
}
