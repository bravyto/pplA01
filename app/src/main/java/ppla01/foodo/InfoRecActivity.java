package ppla01.foodo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InfoRecActivity extends AppCompatActivity {

    Bundle extras;
    Intent intent;
    EditText edit1;
    String foodName, kalori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rec);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Info Food Recommendation");

        intent = getIntent();
        extras = intent.getExtras();

        foodName = extras.getString(RecommendationActivity.EXTRA_MESSAGE1);
        kalori  =              "Food Calories  : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE2) + " Kal";
        String vitc =           "Vit C                   : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE3) + " mg";
        String protein =        "Protein              : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE4) + " gram";
        String carbo =          "Carbohydrat     : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE5) + " gram";
        String water =          "Water                 : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE6) + " gram";
        String calcium =        "Calcium            : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE7) + " mg";
        String porsiAndBerat =  "Portion              : " + extras.getString(RecommendationActivity.EXTRA_MESSAGE8) + ", Weight: " + extras.getString(FoodActivity.EXTRA_MESSAGE9) + "(g)";

        //kalori = extras.getString(FoodActivity.EXTRA_MESSAGE2);;

        TextView textV1 = (TextView) findViewById(R.id.nameOfFoodRec);
        TextView textV2 = (TextView) findViewById(R.id.theCaloriesRec);
        TextView textV3 = (TextView) findViewById(R.id.theWaterRec);
        TextView textV4 = (TextView) findViewById(R.id.theProteinRec);
        TextView textV5 = (TextView) findViewById(R.id.theCarboRec);
        TextView textV6 = (TextView) findViewById(R.id.theSugarRec);
        TextView textV7 = (TextView) findViewById(R.id.theCalciumRec);
        TextView textV8 = (TextView) findViewById(R.id.theCholeRec);

        textV1.setText(foodName);
        textV1.setTextSize(26);
        textV2.setText(kalori);
        textV3.setText(vitc);
        textV4.setText(protein);
        textV5.setText(carbo);
        textV6.setText(water);
        textV7.setText(calcium);
        textV8.setText(porsiAndBerat);


    }

}
