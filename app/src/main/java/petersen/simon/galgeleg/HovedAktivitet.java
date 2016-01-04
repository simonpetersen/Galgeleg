package petersen.simon.galgeleg;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import petersen.simon.galgeleg.fragments.MenuFragment;
import petersen.simon.galgeleg.fragments.SpilFragment;
import petersen.simon.galgeleg.galgeleg.Galgelogik;

public class HovedAktivitet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoved_aktivitet);

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
        if (getFragmentManager().getBackStackEntryCount()!=1)
            getSupportFragmentManager().popBackStack();
    }
}
