package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class AddFoodActivity extends Activity {

    protected  TextView breakfastv, lunchv, dinnerv;
    protected Button Edit;
    private static ArrayList<String> arrayListBreakfast  =new ArrayList<String>();
    private static ArrayList<String> arrayListLunch  =new ArrayList<String>();
    private static ArrayList<String> arrayListDinner  =new ArrayList<String>();
    private ArrayAdapter<String> adapterBreakfast;
    private ArrayAdapter<String> adapterLunch;
    private ArrayAdapter<String> adapterDinner;
    SharedPreferences spref;
    private static float kalori = 0;
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

    public void AddKalori(double kaloriFood){



        this.kalori =  this.kalori+ (float)kaloriFood;
    }

    public double getKalori(){
        return this.kalori;
    }

    public ArrayList<String> getListBreakfast(){
        return this.arrayListBreakfast;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        float bmr= spref.getFloat("BMR", 0);
        sisa = bmr - spref.getFloat("kalori",0);

        TextView consume = (TextView) findViewById(R.id.judul);
        consume.setText("Consumed : "+ spref.getFloat("kalori",0)+ " kal");
        TextView kurang = (TextView) findViewById(R.id.tampil);
        if(sisa < 0){
            kurang.setText("Excess :  "+ ((-1) *sisa) + "  from " + bmr);
        } else {
            kurang.setText("Remaining : " + sisa + "  from " + bmr);
        }

        ListView listBreakfast = (ListView) findViewById(R.id.listv);
        Set<String> set = spref.getStringSet("SetPagi", null);
        if(set==null){

        }
        else{
            if(arrayListBreakfast.isEmpty()){
                arrayListBreakfast=new ArrayList<>(set);

            }
            adapterBreakfast = new ArrayAdapter<String>(this, R.layout.list_item, arrayListBreakfast);
            listBreakfast.setAdapter(adapterBreakfast);
        }

        ListView listLunch = (ListView) findViewById(R.id.listvlunch);
        adapterLunch = new ArrayAdapter<String>(this, R.layout.list_item, arrayListLunch);
        listLunch.setAdapter(adapterLunch);
        adapterLunch.notifyDataSetChanged();


        ListView listDinner = (ListView) findViewById(R.id.listvdinner);
        adapterDinner = new ArrayAdapter<String>(this, R.layout.list_item, arrayListDinner);
        listDinner.setAdapter(adapterDinner);
        adapterDinner.notifyDataSetChanged();

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