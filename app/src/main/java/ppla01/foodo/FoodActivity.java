package ppla01.foodo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE1= "passingMessageGan";
    public final static String EXTRA_MESSAGE2= "passingMessageGan2";
    public final static String EXTRA_MESSAGE3= "passingMessageGan3";
    public final static String EXTRA_MESSAGE4= "passingMessageGan4";
    public final static String EXTRA_MESSAGE5= "passingMessageGan5";
    public final static String EXTRA_MESSAGE6= "passingMessageGan6";
    public final static String EXTRA_MESSAGE7= "passingMessageGan7";
    public final static String EXTRA_MESSAGE8= "passingMessageGan8";
    public final static String EXTRA_MESSAGE9= "passingMessageGan9";

    protected CSVReader cReader;
    protected ListView mList;
    protected EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle("Search Food");

        cReader = new CSVReader(FoodActivity.this,-1, "abc");
        mList = (ListView)findViewById(R.id.mList);

        input = (EditText) findViewById(R.id.input_food);

        String message = input.getText().toString();

        cReader = new CSVReader(FoodActivity.this,-1, message);
        mList = (ListView)findViewById(R.id.mList);
        mList.setAdapter(cReader);

        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String message = input.getText().toString();

                cReader = new CSVReader(FoodActivity.this,-1, message);
                mList = (ListView)findViewById(R.id.mList);
                mList.setAdapter(cReader);
            }
        });

//        dispFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                input = (EditText) findViewById(R.id.input_food);
//                String message = input.getText().toString();
//
//                cReader = new CSVReader(FoodActivity.this,-1, message);
//                mList = (ListView)findViewById(R.id.mList);
//                mList.setAdapter(cReader);
//
//
//            }
//
//
//        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                if (cReader.getItem(pos).getCalories() != null) {
                    //Toast.makeText(v.getContext(), "Calories = " + cReader.getItem(pos).getCalories(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FoodActivity.this, InfoFoodActivity.class);
                String cal = cReader.getItem(pos).getCalories();
                String fname = cReader.getItem(pos).getName();
                String vitc = cReader.getItem(pos).getVitc();
                String protein = cReader.getItem(pos).getProtein();
                String carbo = cReader.getItem(pos).getCarbo();
                String water = cReader.getItem(pos).getWater();
                String calcium = cReader.getItem(pos).getCalcium();
                //String chole=cReader.getItem(pos).getCholestrol();
                String porsi = cReader.getItem(pos).getPorsi();
                String beratMakanan = cReader.getItem(pos).getBeratMakanan();
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