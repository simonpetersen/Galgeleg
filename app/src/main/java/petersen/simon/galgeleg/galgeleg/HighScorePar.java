package petersen.simon.galgeleg.galgeleg;

import java.util.ArrayList;

/**
 * Created by Joakim on 12-01-2016.
 */
public class HighScorePar {

    public String name;
    public String time;

    public HighScorePar(String time, String name){
        this.name = name;
        this.time = time;
    }

    public HighScorePar(){

    }

    public String getName(){
        return name;
    }

    public String getTime(){
        return time;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String toString(){
        return this.name + ", " + this.time;
    }
}
