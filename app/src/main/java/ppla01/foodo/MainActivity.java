package ppla01.foodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText user_name;
    protected DatePicker user_birthdate;
    protected EditText user_weight;
    protected EditText user_height;
    protected Button submit_profile;

    protected Spinner user_gender;
    ArrayAdapter<CharSequence> genderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (DatePicker) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);
        user_gender = (Spinner) findViewById(R.id.gender);
        genderAdapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_gender.setAdapter(genderAdapter);
        user_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit_profile = (Button) findViewById(R.id.submitProfile);

        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, JadwalMakanActivity.class);
                startActivity(i);
            }
        });
    }
}
