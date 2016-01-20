package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import petersen.simon.galgeleg.R;

/**
 * Created by Blumen on 20-01-2016.
 */
public class ToSpillereVundet_frag extends Fragment implements View.OnClickListener {

    Button prøvIgen, hovedmenu;
    TextView vundetTekst;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.to_spillere_vundet_frag, container, false);

        prøvIgen = (Button) view.findViewById(R.id.prøvIgen);
        hovedmenu = (Button) view.findViewById(R.id.MenuKnap);
        prøvIgen.setOnClickListener(this);
        hovedmenu.setOnClickListener(this);

        vundetTekst = (TextView) view.findViewById(R.id.Vundet_tekst);
        vundetTekst.setText("Tillykke " + ToSpillereSpil_frag.vinder + " har vundet spillet!!");

        return view;
    }
    @Override
    public void onClick(View v) {
        if(v == prøvIgen){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new ToSpillere_frag())
                    .commit();
        }
        else if(v == hovedmenu){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new MenuFragment())
                    .commit();
        }

    }
}
