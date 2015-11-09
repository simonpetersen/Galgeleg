package petersen.simon.galgeleg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import petersen.simon.galgeleg.galgeleg.Galgelogik;

/**
 * Created by Simon on 09/11/15.
 */
public class VaelgOrdActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SpilActivity.logik == null) SpilActivity.logik = new Galgelogik();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, SpilActivity.logik.getArrayAfMuligeOrd());
        lView = new ListView(this);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(this);
        setContentView(lView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SpilActivity.logik.setOrdet((String) parent.getItemAtPosition(position));
        finish();
    }
}
