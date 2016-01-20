package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import petersen.simon.galgeleg.R;
import petersen.simon.galgeleg.galgeleg.Galgelogik;

/**
 * Created by Blumen on 19-01-2016.
 */
public class VaelgOrd_frag extends Fragment implements View.OnClickListener{

    Button start2Spillere;
    EditText ordTil2Spillere;
    TextView vaelgOrdTekst;
    String ordTekst;
    static boolean toSpillerSpil;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.vaelg_ord_frag, container, false);

        if(ToSpillere_frag.spillerTur)
            ordTekst = "Indtast et ord som " + ToSpillere_frag.Spiller2Navn + " skal gætte:";
        else
            ordTekst = "Indtast et ord som " + ToSpillere_frag.Spiller1Navn + " skal gætte:";

        start2Spillere = (Button) view.findViewById(R.id.StartToSpillereKnap);
        ordTil2Spillere = (EditText) view.findViewById(R.id.indtastetOrd);
        vaelgOrdTekst = (TextView) view.findViewById(R.id.ordValgTekst);
        start2Spillere.setOnClickListener(this);

        vaelgOrdTekst.setText(ordTekst);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == start2Spillere){
            if(ordTil2Spillere.getText().toString().equals(""))
                Toast.makeText(getActivity(), "Du skal indtaste et ord, før spillet kan startes.", Toast.LENGTH_LONG).show();
            else{
                if (ToSpillereSpil_frag.logik == null) ToSpillereSpil_frag.logik = new Galgelogik();
                ToSpillereSpil_frag.logik.setOrdet(ordTil2Spillere.getText().toString());
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new ToSpillereSpil_frag())
                        .commit();
            }
        }
    }
}
