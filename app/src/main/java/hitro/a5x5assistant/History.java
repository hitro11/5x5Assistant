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

    String getWorkout() {
        return this.workout;
    }
    int getDate() {
        return this.date;
    }

    String getSquat() { return Integer.toString(this.squat);  }

    String getBenchW() {
        if (this.bench == 0) return "-";
        else return Integer.toString(this.bench);
    }

    String getRowW() {
        if (this.row == 0) return "-";
        else return Integer.toString(this.row);
    }

    String getOhpW() {
        if (this.ohp == 0) return "-";
        else return Integer.toString(this.ohp);
    }

    String getDlW() {
        if (this.dl == 0) return "-";
        else return Integer.toString(this.dl);
    }

    String getDoneSquat() {
        if (doneSquat == null) return "-";
        else return doneSquat;
    }

    String getDoneBench() {
        if (doneBench == null) return "-";
        else return doneBench;
    }

    String getDoneRow() {
        if (doneRow == null) return "-";
        else return doneRow;
    }

    String getDoneOHP() {
        if (doneOHP == null) return "-";
        else return doneOHP;
    }

    String getDoneDL() {
        if (doneDL == null) return "-";
        else return doneDL;
    }
}
