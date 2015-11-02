package petersen.simon.galgeleg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import petersen.simon.galgeleg.galgeleg.Galgelogik;

/**
 * Created by Simon on 02/11/15.
 */
public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    EditText input;
    Button check;
    TextView ordView, brugteBogstaver;
    Galgelogik logik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        logik = new Galgelogik();
        logik.nulstil();

        input = (EditText) findViewById(R.id.inputEdit);
        ordView = (TextView) findViewById(R.id.ordView);
        ordView.setText(logik.getSynligtOrd());

        brugteBogstaver = (TextView) findViewById(R.id.brugtBogstaver);
        check = (Button) findViewById(R.id.checkButton);
        check.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
