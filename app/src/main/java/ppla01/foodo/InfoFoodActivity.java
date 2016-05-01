package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class InfoFoodActivity extends AppCompatActivity {
    Button addFood ;
    Bundle extras;
    Intent intent;
<<<<<<< HEAD
    String foodName,calories;
    int indeks;
    int strCalories;
    TextView judul;

    Calories d;
    public final static String EXTRA_MESSAGE1= "passingMessageGan1";
    public final static String EXTRA_MESSAGE2= "passingMessageGan9";
    public final static String AAAA = "Foodname";






=======
    String foodName,calories, kalori;
    SharedPreferences spref;
    SharedPreferences.Editor editor;

>>>>>>> refs/remotes/origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);

<<<<<<< HEAD


=======
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();
>>>>>>> refs/remotes/origin/master

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Food Info");

        addFood = (Button)findViewById(R.id.addFood);
        intent = getIntent();
        extras = intent.getExtras();
        Calories ab = (Calories)intent.getSerializableExtra("sampleObj2");
        d =ab;

        foodName = extras.getString(FoodActivity.EXTRA_MESSAGE1);
        calories = "Food Calories: " + extras.getString(FoodActivity.EXTRA_MESSAGE2);
        String strcl= extras.getString(FoodActivity.EXTRA_MESSAGE2);
        strCalories= Integer.parseInt(strcl);

        String water = "Water: " + extras.getString(FoodActivity.EXTRA_MESSAGE3);
        String protein = "Protein: " + extras.getString(FoodActivity.EXTRA_MESSAGE4);
        String carbo = "Carbohydrat: " + extras.getString(FoodActivity.EXTRA_MESSAGE5);
        String sugar = "Sugar: " + extras.getString(FoodActivity.EXTRA_MESSAGE6);
        String calcium = "Calcium: " + extras.getString(FoodActivity.EXTRA_MESSAGE7);
        String chole = "Cholestrol: " + extras.getString(FoodActivity.EXTRA_MESSAGE8);

        kalori = extras.getString(FoodActivity.EXTRA_MESSAGE2);;
        TextView textV1 = (TextView) findViewById(R.id.nameOfFood);
        TextView textV2 = (TextView) findViewById(R.id.theCalories);
        TextView textV3 = (TextView) findViewById(R.id.theWater);
        TextView textV4 = (TextView) findViewById(R.id.theProtein);
        TextView textV5 = (TextView) findViewById(R.id.theCarbo);
        TextView textV6 = (TextView) findViewById(R.id.theSugar);
        TextView textV7 = (TextView) findViewById(R.id.theCalcium);
        TextView textV8 = (TextView) findViewById(R.id.theChole);
        textV1.setText(foodName);
        textV1.setTextSize(26);
        textV2.setText(calories);
        textV3.setText(water);
        textV4.setText(protein);
        textV5.setText(carbo);
        textV6.setText(sugar);
        textV7.setText(calcium);
        textV8.setText(chole);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                d.setCal(strCalories);
                System.out.println(d.getCal());
                judul = (TextView) findViewById(R.id.judul);
                judul.setText(d.getCal()+"n");

                String newItem = foodName;
                spref = getApplicationContext().getSharedPreferences("my_data", 0);
                editor = spref.edit();
                String jenis = spref.getString("jenis", "");
                double caloriUpdate = Double.parseDouble(kalori);

                AddFoodActivity addFoodActivity = new AddFoodActivity();
                Intent intent = new Intent(v.getContext(), AddFoodActivity.class);

                if(jenis.equals("breakfast")){
                   addFoodActivity.AddKalori(caloriUpdate);
                    addFoodActivity.addArrayBreakfast( newItem + " (" + caloriUpdate + " kal)");
                } else  if (jenis.equals("lunch")){
                    addFoodActivity.AddKalori(caloriUpdate);
                    addFoodActivity.addArrayLunch(newItem + " (" + caloriUpdate + " kal)");
                } else {
                    addFoodActivity.AddKalori(caloriUpdate);
                    addFoodActivity.addArrayDinner(newItem + " (" + caloriUpdate + " kal)");
                }

                startActivity(intent);
                finish();
            }
        });

    }



}