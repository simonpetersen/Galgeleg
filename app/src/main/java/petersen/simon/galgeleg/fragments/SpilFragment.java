package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import petersen.simon.galgeleg.R;
import petersen.simon.galgeleg.galgeleg.Galgelogik;

/**
 * Created by Simon on 16/11/15.
 */
public class SpilFragment extends Fragment implements View.OnClickListener {

    private static ImageView iv, tabt;
    private EditText input;
    private Button check;
    private TextView ordView;
    private static TextView brugteBogstaver, forkerte;
    public static Galgelogik logik;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.spil_frag, container, false);
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


        brugteBogstaver = (TextView) view.findViewById(R.id.brugte);
        forkerte = (TextView) view.findViewById(R.id.forkerte);
        iv = (ImageView) view.findViewById(R.id.imageView);
        tabt = (ImageView) view.findViewById(R.id.tabt);
        check = (Button) view.findViewById(R.id.button);
        check.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        tjek();
    }

    public void tjek(){
        if (input.getText().length()>1) {
            input.setHint("Kun et bogstav!");
            input.setText("");
        } else if (input.getText().length()==0) {
            input.setHint("Skriv et bogstav!");
        } else {
            String b = input.getText().toString();
            logik.gætBogstav(b);
            ordView.setText(logik.getSynligtOrd());
            input.setHint("Gæt et bogstav");
            input.setText("");
            opdaterSkærm();
        }
        if (logik.erSpilletVundet()) {
            tabt.setImageResource(R.mipmap.vundet);
            check.setClickable(false);
            logik.nulstil();
        }
        if (logik.erSpilletTabt()) {
            tabt.setImageResource(R.mipmap.tabt);
            check.setClickable(false);
            logik.nulstil();
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
        brugteBogstaver.setText(str);
        forkerte.setText(" " + logik.getAntalForkerteBogstaver());
        if (logik.getAntalForkerteBogstaver()>0) {
            int id = R.mipmap.forkert1 + logik.getAntalForkerteBogstaver()-1;
            iv.setImageResource(id);
        }
        else
            iv.setImageResource(R.mipmap.galge);
    }


    /* Skal indsættes ved genstart ved ryst
    public void genstartBesked(){
        Toast.makeText(getActivity(), "Spillet blev genstartet.", Toast.LENGTH_SHORT).show();
    }
    */
}
