package hitro.a5x5assistant;

/**
 * Created by rohit on 11/13/2016.
 */

public class User {

    public String name;
    public int bodyweight, squat, bench, row, ohp, dl;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name) {
        this.name = name;
        this.bodyweight = 150;
        this.squat = 45;
        this.bench = 45;
        this.row = 45;
        this.ohp = 45;
        this.dl = 95;
    }
}
