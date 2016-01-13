package petersen.simon.galgeleg.galgeleg;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;
/**
 * Created by Joakim on 12-01-2016.
 */
public class HighScoreLogik {

    public static ArrayList<HighScorePar> parArray = new ArrayList<HighScorePar>();


    public int timeToInt(String Time) {
        return parseInt(Time.substring(0, 2)) * 60 + parseInt(Time.substring(3, 5));
    }

    public boolean isHigh(String time) {
        boolean check = false;
        for (int i = 0; i < parArray.size(); i++) {
            if (timeToInt(parArray.get(i).getTime()) == 0 || timeToInt(time) < timeToInt(parArray.get(i).getTime()) ) {
                check = true;
                break;
            }
        }
        System.out.println(check);
        return check;
    }


    public void InsertHighscore(String Time, String Name) {
        for (int i = 0; i < parArray.size(); i++) {
            if (timeToInt(parArray.get(i).getTime()) == 0 || timeToInt(Time) < timeToInt(parArray.get(i).getTime())) {
                parArray.add(i, new HighScorePar(Time, Name));
                break;
            }
        }
        parArray.remove(parArray.size()-1);
    }

    public HighScoreLogik(){
        for ( int i = 0; i < 10; i++) {
            parArray.add(new HighScorePar("00:00", "    Tom plads    "));
        }
    }
}
