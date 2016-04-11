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

        TextView tinggiv = (TextView)findViewById(R.id.height);
        tinggiv.setText(tinggiv.getText() + tinggi);

        TextView genderv = (TextView) findViewById(R.id.gender);
        genderv.setText(genderv.getText() + gender);

        TextView beratnowv = (TextView) findViewById(R.id.height);
        beratnowv.setText(beratnowv.getText() + beratnow);

        TextView beratthenv = (TextView) findViewById(R.id.target);
        beratthenv.setText(beratthenv.getText() + beratThen);




        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
