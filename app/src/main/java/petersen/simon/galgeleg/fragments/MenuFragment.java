package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import petersen.simon.galgeleg.R;

/**
 * Created by Simon on 16/11/15.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    Button spil, regler, vælgOrd, score;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.menu_frag, container, false);
        spil = (Button) view.findViewById(R.id.spilButton);
        score = (Button) view.findViewById(R.id.buttonscore);
        regler = (Button) view.findViewById(R.id.reglerButton);
        vælgOrd = (Button) view.findViewById(R.id.listOrdButton);
        spil.setOnClickListener(this);
        score.setOnClickListener(this);
        regler.setOnClickListener(this);
        vælgOrd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        if (v == spil) {
            fragment = new SpilFragment();
        } else if (v == vælgOrd) {
            fragment = new VaelgOrdFragment();
        } else if (v == score) {
            fragment = new HighScoreFragment();
        } else {
            fragment = new ReglerFragment();
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold,fragment)
                .addToBackStack(null)
                .commit();
    }
}
