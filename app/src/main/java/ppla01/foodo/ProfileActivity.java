package ppla01.foodo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Bravyto on 29/04/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    protected EditText user_name;
    String  nama, tinggi, beratnow, gender, birthdate;
    boolean valid = true ;
    protected EditText user_birthdate;
    protected EditText user_weight;
    protected EditText user_height;
    protected Button submit_profile;
    RadioButton pria, wanita;
    Spinner Aktivitas;
    double indeksMassa;
    double massa = 0;
    String [] separate;

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
        new DatePickerDialog(ProfileActivity.this, date, myCalendar
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

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Edit Profile Info");
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (EditText) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);
        Aktivitas = (Spinner)findViewById(R.id.spin);
        Aktivitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Low Activity")) {
                    indeksMassa = 1.2;
//                    Toast.makeText(MainActivity.this,"massa adalah "+ parent.getSelectedItem().toString() +" dan "+ indeksMassa,Toast.LENGTH_SHORT).show();

                }
                if (Aktivitas.getSelectedItem().toString().equals("Light Activity")) {
                    indeksMassa = 1.375;
                }
                if (Aktivitas.getSelectedItem().toString().equals("Moderate Activity")) {
                    indeksMassa = 1.55;
                }
                if (Aktivitas.getSelectedItem().toString().equals("Active Activity")) {
                    indeksMassa = 1.725;
                }
                if (Aktivitas.getSelectedItem().toString().equals("Extreme Activity")) {
                    indeksMassa = 1.9;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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

        massa = spref.getFloat("Aktivity",0);
        if(massa == 1.2){
            Aktivitas.setSelection(0);
        } else if(massa == 1.375){
            Aktivitas.setSelection(1);
        }else if(massa == 1.55){
            Aktivitas.setSelection(2);
        }else if(massa == 1.725){
            Aktivitas.setSelection(3);
        }else{
            Aktivitas.setSelection(4);
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
                editor.putFloat("Aktivity",(float)indeksMassa);

                double berat = Double.parseDouble(spref.getString("beratnow", ""));
                double tinggi = Double.parseDouble(spref.getString("tinggi", ""));
                int curent = Calendar.getInstance().get(Calendar.YEAR);
                separate = spref.getString("umur","").split("/");
                double BMR=0;
                String gen = spref.getString("gender", "");
                if(gen.equals("Pria")){
                    BMR = 66.473 + (13.7516 * berat) + (5 * tinggi) - (6.755 * (curent-Double.parseDouble(separate[2])) ) *  massa;
                }
                else{
                    BMR = 655.095 + (9.5634 * berat) + (1.8496 * tinggi )- (4.6756 *(curent-Double.parseDouble(separate[2])) * massa) ;
                }
                editor.putFloat("BMR", (float) BMR);
                editor.commit();

                if (valid) {
                    double BMRr = spref.getFloat("BMR",0);
                    Toast.makeText(v.getContext(), "Calori adalah " + BMRr, Toast.LENGTH_SHORT).show();
                    Toast.makeText(v.getContext(), "Tahun Lahir  " +separate[2], Toast.LENGTH_SHORT).show();
                    Toast.makeText(v.getContext(), "indeksmassa adalah "+ spref.getFloat("Aktivity",0),Toast.LENGTH_SHORT).show();

                    if (!spref.getString("log","").equals("1")) {
                        Intent intent = new Intent(v.getContext(), JadwalMakanActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
