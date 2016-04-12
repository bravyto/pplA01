package ppla01.foodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class MenuActivity extends AppCompatActivity {

    protected Button edit_profile;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        user_birthdate = intent.getStringExtra("user_birthdate");
        user_weight = intent.getStringExtra("user_weight");
        user_target = intent.getStringExtra("user_target");
        user_height = intent.getStringExtra("user_height");
        user_gender = intent.getStringExtra("user_gender");
        update_name = (TextView) findViewById(R.id.name);
        update_name.setText(user_name);
        update_birthdate = (TextView) findViewById(R.id.birthdate);
        update_birthdate.setText(user_birthdate);
        update_weight = (TextView) findViewById(R.id.weight);
        update_weight.setText(user_weight);
        update_weight.append(" kg");
        update_target = (TextView) findViewById(R.id.target);
        update_target.setText(user_target);
        update_target.append(" kg");
        update_height = (TextView) findViewById(R.id.height);
        update_height.setText(user_height);
        update_height.append(" cm");
        update_gender = (TextView) findViewById(R.id.gender);
        update_gender.setText(user_gender);
        edit_profile = (Button) findViewById(R.id.editProfile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
