package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import petersen.simon.galgeleg.R;
import petersen.simon.galgeleg.diverse.App;

/**
 * Created by Simon on 16/11/15.
 */
public class MenuFragment extends Fragment implements View.OnClickListener,Runnable {

    Button spil, regler, vælgOrd, score;
    ProgressBar pbar;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.menu_frag, container, false);
        spil = (Button) view.findViewById(R.id.spilButton);
        score = (Button) view.findViewById(R.id.buttonscore);
        regler = (Button) view.findViewById(R.id.reglerButton);
        vælgOrd = (Button) view.findViewById(R.id.listOrdButton);
        pbar = (ProgressBar) view.findViewById(R.id.progressBar);
        spil.setOnClickListener(this);
        score.setOnClickListener(this);
        regler.setOnClickListener(this);
        vælgOrd.setOnClickListener(this);
        pbar.setVisibility(View.VISIBLE);
        App.pmenu = this;

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

    @Override
    public void run() {
        pbar.setVisibility(View.INVISIBLE);
    }
}
