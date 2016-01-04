package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private ImageView iv;
    private EditText input;
    private Button check;
    private TextView ordView, brugteBogstaver, forkerte;
    public static Galgelogik logik;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.spil_frag, container, false);
        if (logik == null) logik = new Galgelogik();

        input = (EditText) view.findViewById(R.id.inputEdit);
        ordView = (TextView) view.findViewById(R.id.ordView);
        ordView.setText(logik.getSynligtOrd());

        brugteBogstaver = (TextView) view.findViewById(R.id.brugtBogstaver);
        forkerte = (TextView) view.findViewById(R.id.forkert);
        iv = (ImageView) view.findViewById(R.id.imageView);
        check = (Button) view.findViewById(R.id.button);
        check.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
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
            check.setText("Du har vundet!");
            check.setClickable(false);
            logik.nulstil();
        }
        if (logik.erSpilletTabt()) {
            check.setText("Du har tabt!");
            check.setClickable(false);
            logik.nulstil();
        }
    }

    private void opdaterSkærm() {
        ArrayList<String> bogstaver = logik.getBrugteBogstaver();
        String str = "Brugte: ";
        for (int i = 0; i<bogstaver.size(); i++) {
            if (i == bogstaver.size()-1)
                str += bogstaver.get(i)+".";
            else
                str += bogstaver.get(i)+", ";
        }
        brugteBogstaver.setText(str);
        forkerte.setText("Forkerte: " + logik.getAntalForkerteBogstaver());
        if (logik.getAntalForkerteBogstaver()>0) {
            int id = R.mipmap.forkert1 + logik.getAntalForkerteBogstaver()-1;
            iv.setImageResource(id);
        }
    }
}
