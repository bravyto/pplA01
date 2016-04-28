package ppla01.foodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String foodName = extras.getString(FoodActivity.EXTRA_MESSAGE1);
        String calories = "Food Calories: " + extras.getString(FoodActivity.EXTRA_MESSAGE2);
        String water = "Water: " + extras.getString(FoodActivity.EXTRA_MESSAGE3);
        String protein = "Protein: " + extras.getString(FoodActivity.EXTRA_MESSAGE4);
        String carbo = "Carbohydrat: " + extras.getString(FoodActivity.EXTRA_MESSAGE5);
        String sugar = "Sugar: " + extras.getString(FoodActivity.EXTRA_MESSAGE6);
        String calcium = "Calcium: " + extras.getString(FoodActivity.EXTRA_MESSAGE7);
        String chole = "Cholestrol: " + extras.getString(FoodActivity.EXTRA_MESSAGE8);

        TextView textV1 = (TextView) findViewById(R.id.nameOfFood);
        TextView textV2 = (TextView) findViewById(R.id.theCalories);
        TextView textV3 = (TextView) findViewById(R.id.theWater);
        TextView textV4 = (TextView) findViewById(R.id.theProtein);
        TextView textV5 = (TextView) findViewById(R.id.theCarbo);
        TextView textV6 = (TextView) findViewById(R.id.theSugar);
        TextView textV7 = (TextView) findViewById(R.id.theCalcium);
        TextView textV8 = (TextView) findViewById(R.id.theChole);
        textV1.setText(foodName);
        textV1.setTextSize(2);
        textV2.setText(calories);
        textV3.setText(water);
        textV4.setText(protein);
        textV5.setText(carbo);
        textV6.setText(sugar);
        textV7.setText(calcium);
        textV8.setText(chole);



    }
}
