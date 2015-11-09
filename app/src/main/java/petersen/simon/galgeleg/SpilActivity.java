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

import java.lang.reflect.Array;
import java.util.ArrayList;

import petersen.simon.galgeleg.galgeleg.Galgelogik;

/**
 * Created by Simon on 02/11/15.
 */
public class SpilActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private EditText input;
    private Button check, back;
    private TextView ordView, brugteBogstaver, forkerte;
    public static Galgelogik logik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        if (logik == null) logik = new Galgelogik();

        input = (EditText) findViewById(R.id.inputEdit);
        ordView = (TextView) findViewById(R.id.ordView);
        ordView.setText(logik.getSynligtOrd());
        System.out.println(logik.getOrdet());

        brugteBogstaver = (TextView) findViewById(R.id.brugtBogstaver);
        forkerte = (TextView) findViewById(R.id.forkert);
        iv = (ImageView) findViewById(R.id.imageView);
        check = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.backButton);
        check.setOnClickListener(this);
        back.setOnClickListener(this);
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
        if (input.getText().length()>1) {
            input.setHint("Kun et bogstav!");
            input.setText("");
        } else if (input.getText().length()==0) {
            input.setHint("Skriv et bogstav!");
        } else {
            String b = input.getText().toString();
            logik.gætBogstav(b);
            ordView.setText(logik.getSynligtOrd());
            input.setHint("Gæt et bogstav");
            input.setText("");
            opdaterSkærm();
        }
        if (logik.erSpilletVundet()) {
            check.setText("Du har vundet!");
            logik.nulstil();
        }
        if (logik.erSpilletTabt()) {
            check.setText("Du har tabt!");
            logik.nulstil();
        }
        if (v == back) {
            finish();
        }
    }

    private void opdaterSkærm() {
        ArrayList<String> bogstaver = logik.getBrugteBogstaver();
        String str = "Brugte: ";
        for (int i = 0; i<bogstaver.size(); i++) {
            if (i == bogstaver.size()-1)
                str += bogstaver.get(i)+".";
            else
                str += bogstaver.get(i)+", ";
        }
        brugteBogstaver.setText(str);
        forkerte.setText("Forkerte: " + logik.getAntalForkerteBogstaver());
        if (logik.getAntalForkerteBogstaver()>0) {
            int id = R.mipmap.forkert1 + logik.getAntalForkerteBogstaver()-1;
            iv.setImageResource(id);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

}
