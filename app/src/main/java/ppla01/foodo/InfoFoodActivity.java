package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class InfoFoodActivity extends AppCompatActivity {
    Button addFood ;
    Bundle extras;
    Intent intent;
    EditText edit1;
    String foodName,calories, kalori;
    SharedPreferences spref;
    double portion =1;
    SharedPreferences.Editor editor;
    ArrayList<String> listPagi = new ArrayList<>();
    ArrayList<String> listSiang = new ArrayList<>();
    ArrayList<String> listMalam = new ArrayList<>();
    Set<String> setPagi = new HashSet<>();
    Set <String> setSiang = new HashSet<>();
    Set <String> setMalam = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Food Information");

        addFood = (Button)findViewById(R.id.addFood);
        intent = getIntent();
        extras = intent.getExtras();

        foodName = extras.getString(FoodActivity.EXTRA_MESSAGE1);
        calories =              extras.getString(FoodActivity.EXTRA_MESSAGE2) + " kcal";
        String vitc =           extras.getString(FoodActivity.EXTRA_MESSAGE3) + " mg";
        String protein =        extras.getString(FoodActivity.EXTRA_MESSAGE4) + " gram";
        String carbo =          extras.getString(FoodActivity.EXTRA_MESSAGE5) + " gram";
        String water =          extras.getString(FoodActivity.EXTRA_MESSAGE6) + " gram";
        String calcium =        extras.getString(FoodActivity.EXTRA_MESSAGE7) + " mg";
        String porsiAndBerat =  extras.getString(FoodActivity.EXTRA_MESSAGE8) + ", Weight: " + extras.getString(FoodActivity.EXTRA_MESSAGE9) + "(g)";

        kalori = extras.getString(FoodActivity.EXTRA_MESSAGE2);;
        TextView textV1 = (TextView) findViewById(R.id.nameOfFood);
        TextView textV2 = (TextView) findViewById(R.id.theCalories);
        TextView textV3 = (TextView) findViewById(R.id.theWater);
        TextView textV4 = (TextView) findViewById(R.id.theProtein);
        TextView textV5 = (TextView) findViewById(R.id.theCarbo);
        TextView textV6 = (TextView) findViewById(R.id.theSugar);
        TextView textV7 = (TextView) findViewById(R.id.theCalcium);
        TextView textV8 = (TextView) findViewById(R.id.theChole);
        edit1 = (EditText) findViewById(R.id.inputPortion);

        textV1.setText(foodName);
        textV1.setTextSize(26);
        textV2.setText(calories);
        textV3.setText(vitc);
        textV4.setText(protein);
        textV5.setText(carbo);
        textV6.setText(water);
        textV7.setText(calcium);
        textV8.setText(porsiAndBerat);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = foodName;
                spref = getApplicationContext().getSharedPreferences("my_data", 0);
                editor = spref.edit();
                String jenis = spref.getString("jenis", "");
                int date = spref.getInt("tanggal", 0);
                int curent = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                String thePortion = edit1.getText().toString();

                if(thePortion.equals("")) {
                    edit1.setError("Type your eat portion");
                } else {
                    portion = Double.parseDouble(thePortion);
                    double caloriUpdate = Double.parseDouble(kalori) * portion;
                    String item = newItem + " (" + caloriUpdate + " kcal)";
                    AddFoodActivity addFoodActivity = new AddFoodActivity();
                    Intent intent = new Intent(v.getContext(), Main2Activity.class);

                    if (jenis.equals("breakfast")) {

                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> pagi = spref.getStringSet("SetPagi",null);

                            if(pagi == null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setPagi.addAll(temp);
                                editor.putStringSet("SetPagi", setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : pagi) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp,item);
                                setPagi.addAll(temp);
                                editor.putStringSet("SetPagi", setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                editor.commit();

                            }

                        }else{
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            addFoodActivity.setNull();
                            editor.putFloat("kaloriPagi", 0);
                            editor.commit();

                            Set<String> pagi = spref.getStringSet("SetPagi",null);
                            if(pagi == null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setPagi.addAll(temp);
                                editor.putStringSet("SetPagi", setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : pagi) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp,item);
                                setPagi.addAll(temp);
                                editor.putStringSet("SetPagi", setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                editor.commit();

                            }

                        }

                    }  else if (jenis.equals("lunch")) {
                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> siang = spref.getStringSet("SetSiang",null);
                            if(siang == null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setSiang.addAll(temp);
                                editor.putStringSet("SetSiang", setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : siang) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp, item);
                                setSiang.addAll(temp);
                                editor.putStringSet("SetSiang", setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                editor.commit();

                            }

                        }else{
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            addFoodActivity.setNull();
                            editor.putFloat("kaloriSiang", 0);
                            editor.commit();

                            Set<String> siang = spref.getStringSet("SetSiang",null);
                            if(siang == null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setSiang.addAll(temp);
                                editor.putStringSet("SetSiang", setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : siang) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp, item);
                                setSiang.addAll(temp);
                                editor.putStringSet("SetSiang", setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                editor.commit();

                            }
                        }
                    } else {
                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> malam = spref.getStringSet("SetMalam",null);

                            if(malam== null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setMalam.addAll(temp);
                                editor.putStringSet("SetMalam", setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : malam) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp, item);
                                setMalam.addAll(temp);
                                editor.putStringSet("SetMalam", setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                editor.commit();

                            }

                        }else{
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            addFoodActivity.setNull();
                            editor.putFloat("kaloriMalam", 0);
                            editor.commit();

                            Set<String> malam = spref.getStringSet("SetMalam",null);
                            if(malam== null){
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.add(item);
                                setMalam.addAll(temp);
                                editor.putStringSet("SetMalam", setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                editor.commit();

                            }else{
                                ArrayList <String> temp = new ArrayList<String>();
                                temp.clear();
                                int k =0;
                                for (String food : malam) {
                                    temp.add(k,food);
                                    k++;
                                }

                                addFood(temp, item);
                                setMalam.addAll(temp);
                                editor.putStringSet("SetMalam", setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                editor.commit();

                            }
                        }
                    }

                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    public ArrayList<String> addFood(ArrayList<String> temp,String item){
        boolean same = false;
        for(int i = 0 ; i < temp.size(); i++) {
            if(temp.get(i).substring(0, temp.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = temp.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                temp.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            temp.add(item);
        return temp;
    }

}