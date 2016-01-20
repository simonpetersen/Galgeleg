package petersen.simon.galgeleg.diverse;

import android.app.Application;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import petersen.simon.galgeleg.galgeleg.HighScoreLogik;
import petersen.simon.galgeleg.galgeleg.HighScorePar;


/**
 * Created by Joakim on 15-01-2016.
 */
public class App  extends Application {

    public static Firebase highScoreFirebaseRef;
    public static String nr, navn, tid;
    public static Runnable pmenu;
    public static boolean vedstart;


    public void onCreate(){
        super.onCreate();
        HighScoreLogik.parArray = new ArrayList<>();

        vedstart = true;
        Firebase.setAndroidContext(this);
        highScoreFirebaseRef = new Firebase("https://galgeleg-g41.firebaseio.com/");
        highScoreFirebaseRef.child("scorelist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HighScoreLogik.parArray = new ArrayList<>();
                Iterable<DataSnapshot> i = dataSnapshot.getChildren();
                for (DataSnapshot data : i) {
                    HighScorePar highScorePar = data.getValue(HighScorePar.class);
                    HighScoreLogik.parArray.add(highScorePar);
                }
                pmenu.run();
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Read error = " + firebaseError.getMessage());
                pmenu.run();
            }

        });

    }
}

