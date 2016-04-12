package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TOSHIBA on 10/04/2016.
 */
public class MenuActivity extends Activity {
    protected Button edit_profile;
//<<<<<<< HEAD
    String user_name="";
    String user_birthdate="";
    String user_weight="";
    String user_target="";
    String user_height="";
    String user_gender="";
    TextView update_name;
    TextView update_birthdate;
    TextView update_weight;
    TextView update_target;
    TextView update_height;
    TextView update_gender;
//=======
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    String  nama, tinggi, umur, beratnow, beratThen,  gender,morning,noon,evening;
//>>>>>>> refs/remotes/origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();


//        Intent intent = getIntent();
//        user_name = intent.getStringExtra("user_name");
//        user_birthdate = intent.getStringExtra("user_birthdate");
//        user_weight = intent.getStringExtra("user_weight");
//        user_target = intent.getStringExtra("user_target");
//        user_height = intent.getStringExtra("user_height");
//        user_gender = intent.getStringExtra("user_gender");
//        update_name = (TextView) findViewById(R.id.name);
//        update_name.setText(user_name);
//        update_birthdate = (TextView) findViewById(R.id.birthdate);
//        update_birthdate.setText(user_birthdate);
//        update_weight = (TextView) findViewById(R.id.weight);
//        update_weight.setText(user_weight);
//        update_weight.append(" kg");
//        update_target = (TextView) findViewById(R.id.target);
//        update_target.setText(user_target);
//        update_target.append(" kg");
//        update_height = (TextView) findViewById(R.id.height);
//        update_height.setText(user_height);
//        update_height.append(" cm");
//        update_gender = (TextView) findViewById(R.id.gender);
//        update_gender.setText(user_gender);
        edit_profile = (Button) findViewById(R.id.editProfile);
        tinggi = spref.getString("tinggi", "");
        nama = spref.getString("nama", "");
        umur = spref.getString("umur", "");
        beratnow = spref.getString("beratnow", "");
        beratThen = spref.getString("beratThen", "");
        gender = spref.getString("gender", "");
        morning=spref.getString("pagi","");
        noon=spref.getString("siang","");
        evening=spref.getString("malam","");

        TextView tinggiv = (TextView)findViewById(R.id.height);
        tinggiv.setText(tinggiv.getText() + tinggi);

        TextView namav = (TextView) findViewById(R.id.name);
        namav.setText(namav.getText() + nama);

        TextView umurv = (TextView) findViewById(R.id.birthdate);
        umurv.setText(umurv.getText() + umur);

        TextView beratnowv = (TextView) findViewById(R.id.weight);
        beratnowv.setText(beratnowv.getText() + beratnow);

        TextView beratThenv = (TextView) findViewById(R.id.target);
        beratThenv.setText(beratThenv.getText() + beratThen);

        TextView genderv = (TextView) findViewById(R.id.gender);
        genderv.setText(genderv.getText() + gender);

        TextView morningv= (TextView) findViewById(R.id.morning);
        morningv.setText( morning);

        TextView noonv= (TextView) findViewById(R.id.noon);
        noonv.setText( noon);

        TextView eveningv= (TextView) findViewById(R.id.dinner);
        eveningv.setText(evening);



        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = spref.edit();
                editor.putString("log", "");
                editor.commit();
                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
