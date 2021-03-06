package petersen.simon.galgeleg;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import petersen.simon.galgeleg.fragments.MenuFragment;
import petersen.simon.galgeleg.fragments.SpilFragment;
import petersen.simon.galgeleg.fragments.ToSpillereSpil_frag;
import petersen.simon.galgeleg.fragments.ToSpillereVundet_frag;
import petersen.simon.galgeleg.fragments.VaelgOrd_frag;
import petersen.simon.galgeleg.galgeleg.Galgelogik;
import petersen.simon.galgeleg.galgeleg.HighScoreLogik;
import petersen.simon.galgeleg.galgeleg.Sensorlistener;

public class HovedAktivitet extends AppCompatActivity {
    public static SensorManager sensorManager;
    public static Sensorlistener lytter = new Sensorlistener();
    public static HighScoreLogik Hlogik = new HighScoreLogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoved_aktivitet);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);




        if (SpilFragment.logik == null) SpilFragment.logik = new Galgelogik();

        if (savedInstanceState == null) {
            Fragment fragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold, fragment)
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    SpilFragment.logik.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
                return null;
            }
        }.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // brugeren vil navigere op i hierakiet
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //if (getFragmentManager().getBackStackEntryCount()!=1)
          //  getSupportFragmentManager().popBackStack();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragmentindhold);
        if(f instanceof ToSpillereSpil_frag || f instanceof VaelgOrd_frag || f instanceof ToSpillereVundet_frag){   }
        else super.onBackPressed();

    }

}
