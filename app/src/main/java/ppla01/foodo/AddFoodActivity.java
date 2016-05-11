package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.content.SharedPreferences;
>>>>>>> refs/remotes/origin/master
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.util.Set;

public class AddFoodActivity extends AppCompatActivity {
<<<<<<< HEAD
    Button addBreakFast;
=======

>>>>>>> refs/remotes/origin/master
    protected  TextView breakfastv, lunchv, dinnerv;
    protected Button Edit;
    private static ArrayList<String> arrayListBreakfast  =new ArrayList<String>();
    private static ArrayList<String> arrayListLunch  =new ArrayList<String>();
    private static ArrayList<String> arrayListDinner  =new ArrayList<String>();
    private ArrayAdapter<String> adapterBreakfast;
    private ArrayAdapter<String> adapterLunch;
    private ArrayAdapter<String> adapterDinner;
    SharedPreferences spref;
    private static float kaloriPagi ;
    private static float kaloriSiang ;
    private static float kaloriMalam ;
    SharedPreferences.Editor editor;
    float sisa;

    public AddFoodActivity(){

    }

    public void addArrayBreakfast(String item){
        this.arrayListBreakfast.add(item);
    }

    public void addArrayLunch(String item){
        this.arrayListLunch.add(item);
    }

    public void addArrayDinner(String item){
        this.arrayListDinner.add(item);
    }

    public void AddKaloriPagi(double kaloriFood){
        this.kaloriPagi =  this.kaloriPagi + (float)kaloriFood;
    }

    public void AddKaloriSiang(double kaloriFood){
        this.kaloriSiang =  this.kaloriSiang + (float)kaloriFood;
    }

    public void AddKaloriMalam (double kaloriFood){
        this.kaloriMalam =  this.kaloriMalam + (float)kaloriFood;
    }

    public double getKaloriPagi(){
        return this.kaloriPagi;
    }

    public double getKaloriSiang(){
        return this.kaloriSiang;
    }

    public double getKaloriMalam(){

        return this.kaloriMalam;
    }

    public ArrayList<String> getListBreakfast(){

        return this.arrayListBreakfast;
    }
    public ArrayList<String> getListLunch(){
        return this.arrayListLunch;
    }
    public ArrayList<String> getListDinner(){

        return this.arrayListDinner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

<<<<<<< HEAD
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add Food");


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

        Bundle extras = getIntent().getExtras();

        String foodnamez = "hala";

        ListView listView = (ListView) findViewById(R.id.listv);
        String[] items = {"apel", "banana"};
       // arrayList = new ArrayList<String> ();

        arrayList = new ArrayList<String>(Arrays.asList(items));



        if (extras != null){
            int indek =extras.getInt("indeks");
            String foodname = extras.getString(InfoFoodActivity.AAAA);
            arrayList.add(indek, foodname);

        }
=======
>>>>>>> refs/remotes/origin/master

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add Food");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);

        float bmr= spref.getFloat("BMR", 0);
        sisa = bmr - (spref.getFloat("kaloriPagi",0)+spref.getFloat("kaloriSiang",0)+spref.getFloat("kaloriMalam",0));

        TextView consume = (TextView) findViewById(R.id.judul);
        consume.setText("Consumed : "+ (spref.getFloat("kaloriPagi",0)+spref.getFloat("kaloriSiang",0)+ " kal"));
        TextView kurang = (TextView) findViewById(R.id.tampil);
        if(sisa < 0){
            kurang.setText("Excess :  "+ ((-1) *sisa) + "  from " + bmr);
        } else {
            kurang.setText("Remaining : " + sisa + "  from " + bmr);
        }

        ListView listBreakfast = (ListView) findViewById(R.id.listv);
        Set<String> setPagi = spref.getStringSet("SetPagi", null);
        if(setPagi==null){

        }
        else{
            if(arrayListBreakfast.isEmpty()){
                arrayListBreakfast=new ArrayList<>(setPagi);

            }
            adapterBreakfast = new ArrayAdapter<String>(this, R.layout.list_item, arrayListBreakfast);
            listBreakfast.setAdapter(adapterBreakfast);
        }

        ListView listLunch = (ListView) findViewById(R.id.listvlunch);
        Set<String> setSiang = spref.getStringSet("SetSiang", null);
        if(setSiang==null){

        }
        else{
            if(arrayListLunch.isEmpty()){
                arrayListLunch=new ArrayList<>(setSiang);

            }
            adapterLunch = new ArrayAdapter<String>(this, R.layout.list_item, arrayListLunch);
            listLunch.setAdapter(adapterLunch);
        }

        ListView listDinner = (ListView) findViewById(R.id.listvdinner);
        Set<String> setMalam = spref.getStringSet("SetMalam", null);
        if(setMalam==null){

        }
        else{
            if(arrayListDinner.isEmpty()){
                arrayListDinner=new ArrayList<>(setMalam);

            }
            adapterDinner = new ArrayAdapter<String>(this, R.layout.list_item, arrayListDinner);
            listDinner.setAdapter(adapterDinner);
        }


        breakfastv = (TextView) findViewById(R.id.breakfastz);
        breakfastv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                editor = spref.edit();

                editor.putString("jenis", "breakfast");
                editor.commit();
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
                editor = spref.edit();

                editor.putString("jenis", "lunch");
                editor.commit();
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
                editor = spref.edit();

                editor.putString("jenis", "dinner");
                editor.commit();
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });
    }
}