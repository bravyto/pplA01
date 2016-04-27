package ppla01.foodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String foodName = extras.getString(FoodActivity.EXTRA_MESSAGE1);
        String calories = extras.getString(FoodActivity.EXTRA_MESSAGE2);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(foodName);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(40);
        textView2.setText(calories);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
        layout.addView(textView2);

    }
}
