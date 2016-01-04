package petersen.simon.galgeleg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import petersen.simon.galgeleg.R;

/**
 * Created by Simon on 16/11/15.
 */
public class VaelgOrdFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView lView;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View view = i.inflate(R.layout.vaelgord_frag, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item, SpilFragment.logik.getArrayAfMuligeOrd());
        lView  = (ListView) view.findViewById(R.id.ordList);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SpilFragment.logik.setOrdet((String) parent.getItemAtPosition(position));
    }
}
