package ppla01.foodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends ActionBarActivity {

    SharedPreferences spref;
    SharedPreferences.Editor editor;
    protected EditText user_name;
    String  nama, tinggi, umur, beratnow, gender, birthdate;
    boolean valid = true ;
    protected EditText user_birthdate;
    protected EditText user_weight;
    protected EditText user_height;
    protected Button submit_profile;
    RadioButton pria, wanita;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public void showDatePicker(View v) {
        new DatePickerDialog(MainActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        user_birthdate.setText(sdf.format(myCalendar.getTime()));
        user_birthdate.setError(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Profile Info");
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (EditText) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);

        user_name.setText(spref.getString("nama", ""), null);
        user_birthdate.setText(spref.getString("umur", ""), null);
        user_height.setText(spref.getString("tinggi", ""), null);
        user_weight.setText(spref.getString("beratnow", ""), null);

//        Typeface font = Typeface.createFromAsset(getAssets(), "Yellowtail.ttf");
//        TextView tv=(TextView) findViewById(R.id.textView4);
//        tv.setTypeface(font);

        String gen = spref.getString("gender", "");
        if (gen.equals("Wanita")){
            wanita.setChecked(true);
        } else {
            pria.setChecked(true);
        }

        submit_profile = (Button) findViewById(R.id.submitProfile);
        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spref = getSharedPreferences("my_data", 0);
                editor = spref.edit();
                valid = true;

                nama = user_name.getText().toString();
                tinggi = user_height.getText().toString();
                birthdate = user_birthdate.getText().toString();
                beratnow = user_weight.getText().toString();


                if (nama.equals("")) {
                    user_name.setError("Type your name");
                    valid = false;
                }
                if (birthdate.equals("")) {
                    user_birthdate.setError("Set your birthdate");
                    valid = false;
                }
                if (tinggi.equals("")) {
                    user_height.setError("Type your height in cm");
                    valid = false;
                }
                if (beratnow.equals("")) {
                    user_weight.setError("Type your weight in kg");
                    valid = false;
                }
                if(pria.isChecked()) {
                    gender = "Pria";
                } else {
                    gender = "Wanita";
                }
                editor.putString("nama", nama);
                editor.putString("tinggi", tinggi);
                editor.putString("umur", birthdate);
                editor.putString("beratnow", beratnow);
                editor.putString("gender", gender);

                editor.commit();

                if (valid) {
                    Intent intent = new Intent(MainActivity.this, JadwalMakanActivity.class);
                    startActivity(intent);
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
