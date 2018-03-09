package hitro.a5x5assistant;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rohit on 11/13/2016.
 */

public class Profile {

    private double body, squat, bench, row, ohp, dl;
    private double[] weights = new double[6];

    public Profile() { }


    public Profile(double body, double squat, double bench, double row, double ohp, double dl) {
        this.body =  body;
        this.squat = squat;
        this.bench = bench;
        this.row = row;
        this.ohp = ohp;
        this.dl = dl;

        weights[0] = body;
        weights[1] = squat;
        weights[2] = bench;
        weights[3] = row;
        weights[4] = ohp;
        weights[5] = dl;
    }

    public Profile(double squat, double bench, double row) {
        weights[1] = this.squat = squat;
        weights[2] = this.bench = bench;
        weights[3] = this.row = row;
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


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("body", body);
        result.put("squat", squat);
        result.put("bench", bench);
        result.put("row", row);
        result.put("ohp", ohp);
        result.put("dl", dl);

        return result;
    }
}

