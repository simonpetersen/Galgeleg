package petersen.simon.galgeleg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Simon on 09/11/15.
 */
public class VælgOrdActivity extends Activity {

    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaelgord);

        String[] ord = {"programmering", "bus", "universitet"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_vaelgord, R.id.list_item, ord);
        lView = new ListView(this);
        lView.setAdapter(adapter);
        setContentView(lView);
    }
}
