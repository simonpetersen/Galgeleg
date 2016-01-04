package petersen.simon.galgeleg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import petersen.simon.galgeleg.R;

/**
 * Created by Simon on 16/11/15.
 */
public class ReglerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.regler_frag, container, false);
        return v;
    }
}
