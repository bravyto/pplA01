package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddFoodActivity extends Activity {
    Button addBreakFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String foodName = extras.getString(InfoFoodActivity.EXTRA_MESSAGE1);
        String caloriFood = extras.getString(InfoFoodActivity.EXTRA_MESSAGE2);
//        int kalori = Integer.parseInt(caloriFood);
        TextView food = (TextView)findViewById(R.id.makanaPagi1);
        TextView calori = (TextView)findViewById(R.id.caloriMakanan);
        food.setText(foodName);
        calori.setText(caloriFood);


        addBreakFast = (Button)findViewById(R.id.addPagi);
        addBreakFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodActivity.class);
                startActivity(intent);
            }
        });
    }
}