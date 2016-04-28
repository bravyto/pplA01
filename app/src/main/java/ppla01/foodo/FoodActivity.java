package ppla01.foodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE1= "passingMessageGan";
    public final static String EXTRA_MESSAGE2= "passingMessageGan2";

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
                //Toast.makeText(v.getContext(), "Calories = " + cReader.getItem(pos).getCalories(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FoodActivity.this, InfoFoodActivity.class);
                String cal= cReader.getItem(pos).getCalories();
                String fname=cReader.getItem(pos).getName();
                Bundle extras = new Bundle();
                extras.putString(EXTRA_MESSAGE1,fname);
                extras.putString(EXTRA_MESSAGE2,cal);
                intent.putExtras(extras);

                startActivity(intent);

            }
        });





    }
}
