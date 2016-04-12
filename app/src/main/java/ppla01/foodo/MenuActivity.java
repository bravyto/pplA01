package ppla01.foodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class MenuActivity extends AppCompatActivity {

    protected Button edit_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
