package ppla01.foodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {


    protected Button dispFood;
    protected CSVReader cReader;
    protected ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);



        dispFood = (Button) findViewById(R.id.displayFoodButton);
        cReader = new CSVReader(FoodActivity.this,-1, "abc");
        mList = (ListView)findViewById(R.id.mList);

        dispFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input_food);
                String message = input.getText().toString();

                cReader = new CSVReader(FoodActivity.this,-1, message);
                mList = (ListView)findViewById(R.id.mList);
                mList.setAdapter(cReader);


            }


        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Toast.makeText(v.getContext(), "Calories = " + cReader.getItem(pos).getCalories(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
