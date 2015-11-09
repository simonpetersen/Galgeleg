package petersen.simon.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    Button spil, regler, vælgOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        spil = (Button) findViewById(R.id.spilButton);
        regler = (Button) findViewById(R.id.reglerButton);
        vælgOrd = (Button) findViewById(R.id.listOrdButton);
        spil.setOnClickListener(this);
        regler.setOnClickListener(this);
        vælgOrd.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
        if (v == spil) {
            Intent i = new Intent(this, SpilActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (v == regler) {
            Intent i = new Intent(this, ReglerActivity.class);
            startActivity(i);
        } else {

        }
    }
}
