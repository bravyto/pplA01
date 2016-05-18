package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Bravyto on 29/04/2016.
 */
public class RecommendationActivity extends AppCompatActivity {
    protected Button recPagi;
    protected Button recSiang;
    protected Button recMalam;
    protected RecReader rReader;
    protected ListView theList;
    SharedPreferences spref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Recommendation");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        recPagi = (Button) findViewById(R.id.recBreakfast);
        recSiang = (Button) findViewById(R.id.recLunch);
        recMalam = (Button) findViewById(R.id.recDinner);

        rReader = new RecReader(RecommendationActivity.this,-1, "abc");

        recPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rReader = new RecReader(RecommendationActivity.this,-1, "breakfast");
                theList = (ListView)findViewById(R.id.thelist);
                theList.setAdapter(rReader);


            }


        });

        recSiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rReader = new RecReader(RecommendationActivity.this,-1, "lunch");
                theList = (ListView)findViewById(R.id.thelist);
                theList.setAdapter(rReader);


            }


        });

        recMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rReader = new RecReader(RecommendationActivity.this,-1, "dinner");
                theList = (ListView)findViewById(R.id.thelist);
                theList.setAdapter(rReader);


            }


        });


    }

}