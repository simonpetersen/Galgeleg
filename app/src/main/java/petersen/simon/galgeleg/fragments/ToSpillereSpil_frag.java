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
public class ToSpillereSpil_frag extends Fragment implements View.OnClickListener {

    private static ImageView iv, tabt;
    private EditText input;
    private Button check;
    private static TextView ordView, brugteBogstaver, forkerte, Spiller1point, Spiller2point, Spiller1navn, Spiller2navn, spillertur;
    public static Galgelogik logik;
    private static MediaPlayer mpForkert, mpRigtig, mpVundet;
    public static String vinder;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.to_spillere_spil_frag, container, false);
        if (logik == null) logik = new Galgelogik();

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
        Spiller1navn = (TextView) view.findViewById(R.id.Spiller1NavnSpil);
        Spiller2navn = (TextView) view.findViewById(R.id.Spiller2NavnSpil);
        Spiller1point = (TextView) view.findViewById(R.id.Spiller1Score);
        Spiller2point = (TextView) view.findViewById(R.id.Spiller2Score);
        Spiller1navn.setText(ToSpillere_frag.Spiller1Navn);
        Spiller2navn.setText(ToSpillere_frag.Spiller2Navn);
        Spiller1point.setText(String.valueOf(ToSpillere_frag.Spiller1Score));
        Spiller2point.setText(String.valueOf(ToSpillere_frag.Spiller2Score));
        spillertur = (TextView) view.findViewById(R.id.SpillerTurTekst);

        brugteBogstaver = (TextView) view.findViewById(R.id.brugte);
        forkerte = (TextView) view.findViewById(R.id.forkerte);
        iv = (ImageView) view.findViewById(R.id.imageView);
        tabt = (ImageView) view.findViewById(R.id.tabt);
        check = (Button) view.findViewById(R.id.button);
        check.setOnClickListener(this);
        logik.opdaterSynligtOrd();
        opdaterSkærm();

        mpForkert = MediaPlayer.create(getActivity(), R.raw.forkert_bogstav);
        mpRigtig = MediaPlayer.create(getActivity(), R.raw.rigtigt_bogstav);
        mpVundet = MediaPlayer.create(getActivity(), R.raw.vundet);

        if(ToSpillere_frag.spillerTur)
            spillertur.setText("Det er nu: " + ToSpillere_frag.Spiller2Navn + " der skal gætte.");
        else
            spillertur.setText("Det er nu: " + ToSpillere_frag.Spiller1Navn + " der skal gætte.");

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == check){
            if(check.getText().equals("Fortsæt")){
                logik.nulstil();
                ToSpillere_frag.spillerTur = !ToSpillere_frag.spillerTur;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new VaelgOrd_frag())
                        .commit();
            }
            else if(check.getText().equals("Tillykke")){
                logik.nulstil();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new ToSpillereVundet_frag())
                        .commit();
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
            String bogstavGæt = input.getText().toString().toLowerCase();
            logik.gætBogstav(bogstavGæt);
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
            if (Galgelogik.erSidsteBogstavKorrekt()){
                mpRigtig.start();
            }
        }
        if (logik.erSpilletVundet()) {
            mpVundet.start();
            if(ToSpillere_frag.spillerTur) {
                ToSpillere_frag.Spiller2Score++;
                Spiller2point.setText(String.valueOf(ToSpillere_frag.Spiller2Score));
                if(ToSpillere_frag.Spiller2Score >= ToSpillere_frag.antalPointForVind) {
                    vinder = ToSpillere_frag.Spiller2Navn;
                    check.setText("Tillykke");
                }
                else
                    check.setText("Fortsæt");
            }
            else {
                ToSpillere_frag.Spiller1Score++;
                Spiller1point.setText(String.valueOf(ToSpillere_frag.Spiller1Score));
                if(ToSpillere_frag.Spiller1Score >= ToSpillere_frag.antalPointForVind) {
                    vinder = ToSpillere_frag.Spiller1Navn;
                    check.setText("Tillykke");
                }
                else
                    check.setText("Fortsæt");
            }

            tabt.setImageResource(R.mipmap.vundet);
            //check.setText("Fortsæt");

        }
        if (logik.erSpilletTabt()) {
            tabt.setImageResource(R.mipmap.tabt);
            check.setText("Fortsæt");
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        logik.nulstil();
    }

}
