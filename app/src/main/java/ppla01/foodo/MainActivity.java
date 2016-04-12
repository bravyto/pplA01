package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    SharedPreferences spref;
    SharedPreferences.Editor editor;
    protected EditText user_name, user_height, user_weight,user_weight_target;
    String  nama, tinggi, umur, beratnow, beratThen, gender;
    boolean valid = true ;
    protected DatePicker user_birthdate;
    protected Button submit_profile;
    protected Spinner user_gender;
    //    ArrayAdapter<CharSequence> genderAdapter;
    RadioButton pria, wanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (DatePicker) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_weight_target=(EditText) findViewById(R.id.target);
        user_height = (EditText) findViewById(R.id.height);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);

        user_name.setText(spref.getString("nama", ""), null);
        user_height.setText(spref.getString("tinggi", ""), null);
        user_weight.setText(spref.getString("beratnow", ""), null);
        user_weight_target.setText(spref.getString("beratThen", ""), null);

        String gen = spref.getString("gender", "");
        if (gen.equals("pria")){
            pria.setChecked(true);
        } else {
            wanita.setChecked(true);
        }

//        genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
//        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        user_gender.setAdapter(genderAdapter);
//        user_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        submit_profile = (Button) findViewById(R.id.submitProfile);
        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spref = getSharedPreferences("my_data", 0);
                editor = spref.edit();

                nama = user_name.getText().toString();
                tinggi = user_height.getText().toString();
                beratnow = user_weight.getText().toString();
                beratThen = user_weight_target.getText().toString();
                int hari = user_birthdate.getDayOfMonth();
                int bulan = user_birthdate.getMonth() + 1;
                int tahun = user_birthdate.getYear();
                umur = hari + "/" + bulan + "/" + tahun;


                if (nama.equals("")) {
                    user_name.setError("Type your name");
                    valid = false;
                }
                if (tinggi.equals("")) {
                    user_height.setError("Type your height");
                    valid = false;
                }
                if (beratnow.equals("")) {
                    user_height.setError("Type your weight");
                    valid = false;
                }
                if (beratThen.equals("")) {
                    user_height.setError("Type your weight target");
                    valid = false;
                }
                if (umur.equals("")) {
                    valid = false;
                }
                if (pria.isChecked()) {
                    gender = "pria";

                }
                if (wanita.isChecked()) {
                    gender = "wanita";
                }
                editor.putString("nama", nama);
                editor.putString("tinggi", tinggi);
                editor.putString("umur", umur);
                editor.putString("beratnow", beratnow);
                editor.putString("beratThen", beratThen);
                editor.putString("gender", gender);

                editor.commit();

                if (valid) {
                    Intent intent = new Intent(MainActivity.this, JadwalMakanActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "From contains error" + umur, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onStart(){
        super.onStart();
        if (spref.getString("log","").equals("1")){
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
