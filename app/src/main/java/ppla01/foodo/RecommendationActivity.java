package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Bravyto on 29/04/2016.
 */
public class RecommendationActivity extends AppCompatActivity {
    protected ImageView recPagi;
    protected ImageView recSiang;
    protected ImageView recMalam;
    protected RecReader rReader;
    protected ListView theList;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    double theBMR;
    public final static String EXTRA_MESSAGE1= "passingMessageGan";
    public final static String EXTRA_MESSAGE2= "passingMessageGan2";
    public final static String EXTRA_MESSAGE3= "passingMessageGan3";
    public final static String EXTRA_MESSAGE4= "passingMessageGan4";
    public final static String EXTRA_MESSAGE5= "passingMessageGan5";
    public final static String EXTRA_MESSAGE6= "passingMessageGan6";
    public final static String EXTRA_MESSAGE7= "passingMessageGan7";
    public final static String EXTRA_MESSAGE8= "passingMessageGan8";
    public final static String EXTRA_MESSAGE9= "passingMessageGan9";


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
        theBMR = spref.getFloat("BMR",0);


        recPagi = (ImageView) findViewById(R.id.oneImgView);
        recSiang = (ImageView) findViewById(R.id.twoImgView);
        recMalam = (ImageView) findViewById(R.id.threeImgView);

        rReader = new RecReader(RecommendationActivity.this,-1, "abc");
        theList = (ListView)findViewById(R.id.thelist);

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

        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                if (rReader.getItem(pos).getCalories() != null) {
                    //Toast.makeText(v.getContext(), "Calories = " + cReader.getItem(pos).getCalories(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecommendationActivity.this, InfoRecActivity.class);
                    String cal = rReader.getItem(pos).getCalories();
                    String fname = rReader.getItem(pos).getName();
                    String vitc = rReader.getItem(pos).getVitc();
                    String protein = rReader.getItem(pos).getProtein();
                    String carbo = rReader.getItem(pos).getCarbo();
                    String water = rReader.getItem(pos).getWater();
                    String calcium = rReader.getItem(pos).getCalcium();
                    //String chole=cReader.getItem(pos).getCholestrol();
                    String porsi = rReader.getItem(pos).getPorsi();
                    String beratMakanan = rReader.getItem(pos).getBeratMakanan();
                    Bundle extras = new Bundle();
                    extras.putString(EXTRA_MESSAGE1, fname);
                    extras.putString(EXTRA_MESSAGE2, cal);
                    extras.putString(EXTRA_MESSAGE3, vitc);
                    extras.putString(EXTRA_MESSAGE4, protein);
                    extras.putString(EXTRA_MESSAGE5, carbo);
                    extras.putString(EXTRA_MESSAGE6, water);
                    extras.putString(EXTRA_MESSAGE7, calcium);
                    extras.putString(EXTRA_MESSAGE8, porsi);
                    extras.putString(EXTRA_MESSAGE9, beratMakanan);
                    intent.putExtras(extras);

                    startActivity(intent);
                }

            }
        });


    }

}