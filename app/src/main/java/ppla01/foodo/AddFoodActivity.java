package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddFoodActivity extends Activity {
    Button addBreakFast;
    protected  TextView breakfastv, lunchv, dinnerv;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;
    String foodname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String foodName = extras.getString(InfoFoodActivity.EXTRA_MESSAGE1);
//        String caloriFood = extras.getString(InfoFoodActivity.EXTRA_MESSAGE2);
////        int kalori = Integer.parseInt(caloriFood);
//        TextView food = (TextView)findViewById(R.id.makanaPagi1);
//        TextView calori = (TextView)findViewById(R.id.caloriMakanan);
//        food.setText(foodName);
//        calori.setText(caloriFood);
//
//
//        addBreakFast = (Button)findViewById(R.id.addPagi);
//        addBreakFast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), FoodActivity.class);
//                startActivity(intent);
//            }
//        });

//        TextView textView = (TextView) findViewById(R.id.txtitem);
//        Intent intent = getIntent();
//        String foodname = intent.getStringExtra("foodname");
//        textView.setText(foodname);

//        Bundle extras = getIntent().getExtras();
//        foodname = extras.getString("Foodname");
//
//        ListView listView = (ListView) findViewById(R.id.listv);
//        String[] items = {"apel", "banana"};
//        arrayList = new ArrayList<String> (Arrays.asList(items));
//       // arrayList = new ArrayList<String>();
//
//
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem ,arrayList);
//        listView.setAdapter(adapter);
//        arrayList.add(foodname);
//        adapter.notifyDataSetChanged();




        breakfastv = (TextView) findViewById(R.id.breakfastz);
        breakfastv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });

        lunchv = (TextView) findViewById(R.id.lunch);
        lunchv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });

        dinnerv = (TextView) findViewById(R.id.dinner);
        dinnerv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });

    }


}