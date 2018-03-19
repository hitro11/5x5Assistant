package hitro.a5x5assistant;


public class History {
    String date, workout;
    private double ex1, ex2, ex3;
    private boolean doneEx1, doneEx2, doneEx3;

    public History(String workout, String date, double ex1, double ex2, double ex3, boolean doneEx1,
                   boolean doneEx2, boolean doneEx3) {

        this.workout = workout;
        this.date = date;
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
        this.doneEx1 = doneEx1;
        this.doneEx2 = doneEx2;
        this.doneEx3 = doneEx3;
    }

    public String getDate() {
        return date;
    }

    public String getWorkout() {
        return workout;
    }

    public History() { }

    public double getEx1() {
        return ex1;
    }

    public double getEx2() {
        return ex2;
    }

    public double getEx3() {
        return ex3;
    }

    public boolean isDoneEx1() {
        return doneEx1;
    }

    public boolean isDoneEx2() {
        return doneEx2;
    }

    public boolean isDoneEx3() {
        return doneEx3;
    }
}
