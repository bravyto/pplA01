package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences spref;
    SharedPreferences.Editor editor;
    protected EditText user_name, user_height, user_birthdate,user_weight;
    String  nama, tinggi, umur, beratnow;
    boolean valid=true ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();
        user_name = (EditText) findViewById(R.id.name);
        user_height = (EditText) findViewById(R.id.height);
        user_birthdate= (EditText) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);

        user_name.setText(spref.getString("nama", ""), null);
        user_height.setText(spref.getString("tinggi", ""), null);
        user_weight.setText(spref.getString("beratnow", ""), null);
        user_birthdate.setText(spref.getString("umur", ""), null);


    }

    public void confirm(View view){
        spref = getSharedPreferences("my_data", 0);
        editor = spref.edit();

        nama = user_name.getText().toString();
        tinggi =user_height.getText().toString();
        umur = user_birthdate.getText().toString();
        beratnow = user_weight.getText().toString();

        editor.putString("nama", nama);
        editor.putString("tinggi", tinggi);
        editor.putString("umur", umur);
        editor.putString("beratnow", beratnow);

        if(nama.equals("")){
            user_name.setError("Type your name");
            valid = false;
        }
        if(tinggi.equals("")){
            user_height.setError("Type your name");
            valid = false;
        }
        if(beratnow.equals("")){
            user_height.setError("Type your name");
            valid = false;
        }
        if(umur.equals("")){
            user_birthdate.setError("Type your name");
            valid = false;
        }

        editor.commit();
        if(valid){
             Intent intent = new Intent(MainActivity.this, MenuActivity.class);
             startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "From contains error" ,Toast.LENGTH_SHORT).show();
        }
    }
    protected void onStart(){
        super.onStart();
        if (spref.getString("log","").equals("1")){
            //Intent intent = new Intent(MainActivity.this, Home.class);
            //startActivity(intent);
        }
    }
}
