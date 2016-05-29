package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                    AddFoodActivity addFoodActivity = new AddFoodActivity();
                    Intent intent = new Intent(v.getContext(), Main2Activity.class);

                    if (jenis.equals("breakfast")) {
                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> pagi = spref.getStringSet("SetPagi",null);
                            if(pagi == null){
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayBreakfast(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                listPagi=addFoodActivity.getListBreakfast();
                                setPagi.addAll(listPagi);
                                editor.putStringSet("SetPagi",setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp = new ArrayList<String>(pagi);
                                for (int i = 0; i < temp.size(); i++) {
                                    addFoodActivity.addArrayBreakfast(temp.get(i));
                                }
                                addFoodActivity.addArrayBreakfast(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                listPagi=addFoodActivity.getListBreakfast();
                                setPagi.addAll(listPagi);
                                editor.putStringSet("SetPagi",setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
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
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayBreakfast(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                listPagi=addFoodActivity.getListBreakfast();
                                setPagi.addAll(listPagi);
                                editor.putStringSet("SetPagi",setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp = new ArrayList<String>(pagi);
                                for (int i = 0; i < temp.size(); i++) {
                                    addFoodActivity.addArrayBreakfast(temp.get(i));
                                    Toast.makeText(v.getContext(), " indeks ke-i "+temp.get(i),Toast.LENGTH_SHORT).show();
                                }
                                addFoodActivity.addArrayBreakfast(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriPagi(caloriUpdate);
                                listPagi=addFoodActivity.getListBreakfast();
                                Toast.makeText(v.getContext(), " listPagi "+listPagi,Toast.LENGTH_SHORT).show();
                                setPagi.addAll(listPagi);
                                editor.putStringSet("SetPagi",setPagi);
                                editor.putFloat("kaloriPagi", (float) caloriUpdate + spref.getFloat("kaloriPagi", 0));
                                editor.commit();
                            }

                        }

                    }  else if (jenis.equals("lunch")) {
                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> siang = spref.getStringSet("SetSiang",null);
                            if(siang == null){
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayLunch(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                listSiang=addFoodActivity.getListLunch();
                                setSiang.addAll(listSiang);
                                editor.putStringSet("SetSiang",setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp2 = new ArrayList<String>(siang);
                                for (int i = 0; i < temp2.size(); i++) {
                                    addFoodActivity.addArrayLunch(temp2.get(i));
                                }
                                addFoodActivity.addArrayLunch(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                listSiang=addFoodActivity.getListLunch();
                                setSiang.addAll(listSiang);
                                editor.putStringSet("SetSiang",setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                editor.commit();
                            }

                        }else{
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            addFoodActivity.setNull();
                            editor.putFloat("kaloriSiang", 0);
                            editor.commit();

                            Set<String> siang = spref.getStringSet("SetPagi",null);
                            if(siang == null){
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayLunch(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                listSiang=addFoodActivity.getListLunch();
                                setSiang.addAll(listSiang);
                                editor.putStringSet("SetSiang",setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp2 = new ArrayList<String>(siang);
                                for (int i = 0; i < temp2.size(); i++) {
                                    addFoodActivity.addArrayLunch(temp2.get(i));
                                }
                                addFoodActivity.addArrayLunch(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriSiang(caloriUpdate);
                                listSiang=addFoodActivity.getListLunch();
                                setSiang.addAll(listSiang);
                                editor.putStringSet("SetSiang",setSiang);
                                editor.putFloat("kaloriSiang", (float) caloriUpdate + spref.getFloat("kaloriSiang", 0));
                                editor.commit();
                            }
                        }
                    } else {
                        if(curent == date){
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            Set<String> malam = spref.getStringSet("SetMalam",null);
                            if(malam == null){
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayDinner(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                listMalam=addFoodActivity.getListDinner();
                                setMalam.addAll(listMalam);
                                editor.putStringSet("SetMalam",setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp3 = new ArrayList<String>(malam);
                                for (int i = 0; i < temp3.size(); i++) {
                                    addFoodActivity.addArrayDinner(temp3.get(i));
                                }
                                addFoodActivity.addArrayDinner(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                listMalam=addFoodActivity.getListDinner();
                                setMalam.addAll(listMalam);
                                editor.putStringSet("SetMalam",setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                editor.commit();
                            }

                        }else{
                            spref = getApplicationContext().getSharedPreferences("my_data", 0);
                            editor = spref.edit();
                            addFoodActivity.setNull();
                            editor.putFloat("kaloriMalam", 0);
                            editor.commit();

                            Set<String> malam = spref.getStringSet("SetMalam",null);
                            if(malam == null){
//                                Toast.makeText(v.getContext(), " masuk if ",Toast.LENGTH_SHORT).show();
                                addFoodActivity.addArrayDinner(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                listMalam=addFoodActivity.getListDinner();
                                setMalam.addAll(listMalam);
                                editor.putStringSet("SetMalam",setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
                                editor.commit();
                            }else{
                                ArrayList <String> temp3 = new ArrayList<String>(malam);
                                for (int i = 0; i < temp3.size(); i++) {
                                    addFoodActivity.addArrayDinner(temp3.get(i));
                                }
                                addFoodActivity.addArrayDinner(newItem + " (" + caloriUpdate + " kcal)");
                                addFoodActivity.AddKaloriMalam(caloriUpdate);
                                listMalam=addFoodActivity.getListDinner();
                                setMalam.addAll(listMalam);
                                editor.putStringSet("SetMalam",setMalam);
                                editor.putFloat("kaloriMalam", (float) caloriUpdate + spref.getFloat("kaloriMalam", 0));
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
}