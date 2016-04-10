package ppla01.foodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText user_name;
    protected EditText user_birthdate;
    protected EditText user_weight;
    protected EditText user_height;
    protected Button submit_profile;
    public String kirimNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (EditText) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);
        submit_profile = (Button) findViewById(R.id.submitProfile);
        kirimNama = user_name.getText().toString();

        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, JadwalMakanActivity.class);
                startActivity(i);
            }
        });
    }
}
