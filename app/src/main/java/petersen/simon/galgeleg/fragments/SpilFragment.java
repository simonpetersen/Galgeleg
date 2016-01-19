package petersen.simon.galgeleg.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import petersen.simon.galgeleg.HovedAktivitet;
import petersen.simon.galgeleg.R;
import petersen.simon.galgeleg.diverse.App;
import petersen.simon.galgeleg.galgeleg.Galgelogik;
import petersen.simon.galgeleg.galgeleg.HighScoreLogik;
import petersen.simon.galgeleg.galgeleg.HighScorePar;
import petersen.simon.galgeleg.galgeleg.Sensorlistener;

/**
 * Created by Simon on 16/11/15.
 */
public class SpilFragment extends Fragment implements View.OnClickListener {

    private static ImageView iv, tabt, tabt1;
    private EditText input;
    private Button check;
    private static TextView ordView;
    private static TextView brugteBogstaver, forkerte;
    public static Galgelogik logik;
    private Chronometer timer;
    private MediaPlayer mpForkert, mpRigtig, mpPisk;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.spil_frag, container, false);
        if (logik == null) logik = new Galgelogik();

        Sensor accel = HovedAktivitet.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        HovedAktivitet.sensorManager.registerListener(HovedAktivitet.lytter, accel, SensorManager.SENSOR_DELAY_NORMAL);

        input = (EditText) view.findViewById(R.id.inputEdit);
        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int tastekode, KeyEvent handling) {
                if (logik.erSpilletTabt()){
                    tabt.setImageResource(R.mipmap.tabt);
                    input.setClickable(false);
                }
                else if ((handling.getAction() == KeyEvent.ACTION_DOWN) &&
                        (tastekode == KeyEvent.KEYCODE_ENTER && !logik.erSpilletTabt())) {
                    tjek();
                    return true;
                }
                return false;
            }
        });
        ordView = (TextView) view.findViewById(R.id.ordView);
        ordView.setText(logik.getSynligtOrd());


        brugteBogstaver = (TextView) view.findViewById(R.id.brugte);
        timer = (Chronometer) view.findViewById(R.id.timer);
        timer.start();
        forkerte = (TextView) view.findViewById(R.id.forkerte);
        iv = (ImageView) view.findViewById(R.id.imageView);
        tabt = (ImageView) view.findViewById(R.id.tabt);
        tabt1 = (ImageView) view.findViewById(R.id.tabt1);
        check = (Button) view.findViewById(R.id.button);
        check.setOnClickListener(this);
        logik.opdaterSynligtOrd();
        opdaterSkærm();

        mpForkert = MediaPlayer.create(getActivity(), R.raw.forkert_bogstav);
        mpRigtig = MediaPlayer.create(getActivity(), R.raw.rigtigt_bogstav);
        mpPisk = MediaPlayer.create(getActivity(), R.raw.genstarts_pisk);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == check){
            if(check.getText().equals("Prøv igen")){
                logik.nulstil();
                opdaterSkærm();
                tabt.setImageResource(0);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                tabt1.setImageResource(0);
                check.setText("CHECK");
            }
            else
                tjek();
        }
        else
            tjek();
    }

    public void tjek(){
        if (input.getText().length()>1) {
            input.setHint("Kun et bogstav!");
            input.setText("");
        } else if (input.getText().length()==0) {
            input.setHint("Skriv et bogstav!");
        } else {
            String b = input.getText().toString().toLowerCase();
            logik.gætBogstav(b);
            ordView.setText(logik.getSynligtOrd());
            input.setHint("Gæt et bogstav");
            input.setText("");
            opdaterSkærm();
            if(Galgelogik.forkertBogstav){
                mpForkert.start();
                try {
                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                mpRigtig.start();

        }
        if (logik.erSpilletVundet()) {
            timer.stop();
            if(HovedAktivitet.Hlogik.isHigh(timer.getText().toString())){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Tillykke du er kommet på HighScore listen!");
                final EditText highNavn = new EditText(getActivity());
                highNavn.setHint("Indtast navn");
                builder.setView(highNavn);
                builder.setPositiveButton("Enter", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        HovedAktivitet.Hlogik.InsertHighscore(timer.getText().toString(), highNavn.getText().toString());
                        App.highScoreFirebaseRef.child("scorelist").setValue(HighScoreLogik.parArray);
//                        getFragmentManager().popBackStack();
                    }
                });
                builder.show();
            }
            tabt.setImageResource(R.mipmap.vundet);
            tabt1.setImageResource(R.mipmap.vundet);
            check.setText("Prøv igen");

        }
        if (logik.erSpilletTabt()) {
            tabt.setImageResource(R.mipmap.tabt);
            tabt1.setImageResource(R.mipmap.tabt);
            check.setText("Prøv igen");
        }
    }

    public static void opdaterSkærm() {
        ArrayList<String> bogstaver = logik.getBrugteBogstaver();
        String str = " ";
        for (int i = 0; i<bogstaver.size(); i++) {
            if (i == bogstaver.size()-1)
                str += bogstaver.get(i)+".";
            else
                str += bogstaver.get(i)+", ";
        }

        ordView.setText(logik.getSynligtOrd());
        brugteBogstaver.setText(str);
        forkerte.setText(" " + logik.getAntalForkerteBogstaver());

        if (logik.getAntalForkerteBogstaver()>0) {
            int id = R.mipmap.forkert1 + logik.getAntalForkerteBogstaver()-1;
            iv.setImageResource(id);
        }
        else
            iv.setImageResource(R.mipmap.galge);
    }


    //Skal indsættes ved genstart ved ryst
    public void genstartVedRyst(){
        Toast.makeText(getActivity(), "Spillet blev genstartet.", Toast.LENGTH_SHORT).show();

        try {
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        logik.nulstil();
    }
}
