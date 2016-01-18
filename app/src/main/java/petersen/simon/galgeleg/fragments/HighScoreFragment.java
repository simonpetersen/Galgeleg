package petersen.simon.galgeleg.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import petersen.simon.galgeleg.HovedAktivitet;
import petersen.simon.galgeleg.R;
import petersen.simon.galgeleg.galgeleg.HighScoreLogik;

/**
 * Created by Joakim on 12-01-2016.
 */
public class HighScoreFragment extends Fragment {
    ListView lv;
    View v;

    @Override
    public View onCreateView(final LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        v = i.inflate(R.layout.high_score_frag, container, false);
        lv = (ListView)v.findViewById(R.id.LV);
String [] numre = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.high_score_liste_element, R.id.nr, numre) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                TextView navn = (TextView) view.findViewById(R.id.navn);
                navn.setText(HighScoreLogik.parArray.get(position).getName());


                TextView tid = (TextView) view.findViewById(R.id.tid);
                tid.setText(HighScoreLogik.parArray.get(position).getTime());

                return view;
            }
        };

        lv.setAdapter(adapter);


        return v;
    }


}
