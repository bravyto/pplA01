package ppla01.foodo;

import android.app.Activity;
import android.app.FragmentContainer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TOSHIBA on 10/04/2016.
 */
public class HomeActivity extends Activity {
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
        setContentView(R.layout.activity_home_container);
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_container,homeFragment);
        fragmentTransaction.commit();





//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void onBackPressed(){
        finish();
    }
}
