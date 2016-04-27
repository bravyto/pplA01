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

        TextView textV1 = (TextView) findViewById(R.id.nameOfFood);
        TextView textV2 = (TextView) findViewById(R.id.theCalories);
        textV1.setText(foodName);
        textV1.setTextSize(20);
        textV2.setText(calories);



    }
}
