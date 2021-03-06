package petersen.simon.galgeleg.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import petersen.simon.galgeleg.R;

public class ToSpillere_frag extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    EditText spiller1, spiller2;
    Button startknap;
    SeekBar point;
    TextView pointTekst;
    public static String Spiller1Navn, Spiller2Navn;
    public static boolean spillerTur = true;
    public static int Spiller1Score, Spiller2Score, antalPointForVind;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.to_spillere_frag, container, false);

        spiller1 = (EditText) view.findViewById(R.id.Spiller1Navn);
        spiller2 = (EditText) view.findViewById(R.id.Spiller2Navn);
        startknap = (Button) view.findViewById(R.id.StartButton);
        startknap.setOnClickListener(this);
        point = (SeekBar) view.findViewById(R.id.AntalPoint);
        point.setOnSeekBarChangeListener(this);
        pointTekst = (TextView) view.findViewById(R.id.pointTekst);

        Spiller1Score = 0;
        Spiller2Score = 0;

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == startknap){
            if(spiller1.getText().toString().equals("") || spiller2.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Begge spillernavne skal udfyldes for at komme videre.", Toast.LENGTH_SHORT).show();
            }
            else if(point.getProgress() == 0)
                Toast.makeText(getActivity(), "Der skal vælges et antal point større end 0, der skal spilles til", Toast.LENGTH_LONG).show();
            else{
                antalPointForVind = point.getProgress();
                Spiller1Navn = spiller1.getText().toString();
                Spiller2Navn = spiller2.getText().toString();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new VaelgOrd_frag())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        pointTekst.setText("Vælg antal point der spiller til: " + point.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
