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
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    String  nama, tinggi, umur, beratnow, beratThen,  gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();


        edit_profile = (Button) findViewById(R.id.editProfile);
        tinggi = spref.getString("tinggi", "");
        nama = spref.getString("nama", "");
        umur = spref.getString("umur", "");
        beratnow = spref.getString("beratnow", "");
        beratThen = spref.getString("beratThen", "");
        gender = spref.getString("gender", "");


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
