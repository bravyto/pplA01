package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TOSHIBA on 26/05/2016.
 */


public class DeleteActivity extends AppCompatActivity {
    protected Button delete;
    String data;
    TextView datatampil, datatampil2;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    ArrayList<String> listPagi = new ArrayList<>();
    ArrayList<String> listSiang = new ArrayList<>();
    ArrayList<String> listMalam = new ArrayList<>();
    Set<String> setPagi = new HashSet<>();
    Set <String> setSiang = new HashSet<>();
    Set <String> setMalam = new HashSet<>();
    double kalori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Delete Food");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        data = spref.getString("makanan", "");
        String nilaiKal = data.substring(data.lastIndexOf("(") + 1, data.length() - 1 - 4);
        kalori = Double.parseDouble(nilaiKal);

//        Toast.makeText(DeleteActivity.this, "kalori 0 " +nilaiKal, Toast.LENGTH_SHORT).show();
        datatampil = (TextView) findViewById(R.id.makanan);
        datatampil2 = (TextView) findViewById(R.id.kalori);
        delete = (Button)findViewById(R.id.delete);
        datatampil.setText(" Nama Makanan : " + data.substring(0, data.lastIndexOf("(") - 1));
        datatampil2.setText(" Kalori Makanan : " + kalori+ " kkal");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = data;
                spref = getApplicationContext().getSharedPreferences("my_data", 0);
                editor = spref.edit();
                String jenis = spref.getString("delete", "");
                AddFoodActivity addFoodActivity = new AddFoodActivity();

                if (jenis.equals("pagi")) {
                    Set<String> pagi = spref.getStringSet("SetPagi", null);
                    listPagi = new ArrayList<String>(pagi);
                    int idx = search(newItem, listPagi);
                    listPagi= rmArray(listPagi, idx);
                    addFoodActivity.minKaloriPagi(kalori);
                    editor.putFloat("kaloriPagi", (spref.getFloat("kaloriPagi", 0) - (float) kalori));

                    setPagi.addAll(listPagi);
                    editor.putStringSet("SetPagi", setPagi);
                    editor.commit();
                    Intent i = new Intent(DeleteActivity.this, Main2Activity.class);
                    startActivity(i);
                } else if (jenis.equals("siang")) {

                    Set<String> siang = spref.getStringSet("SetSiang", null);
                    listSiang = new ArrayList<String>(siang);
                    int idx = search(newItem, listSiang);
                    listSiang= rmArray(listSiang, idx);
                    addFoodActivity.minKaloriSiang(kalori);
                    editor.putFloat("kaloriSiang", (spref.getFloat("kaloriSiang", 0) - (float) kalori));

                    setSiang.addAll(listSiang);
                    editor.putStringSet("SetSiang", setSiang);
                    editor.commit();
                    Intent i = new Intent(DeleteActivity.this, Main2Activity.class);
                    startActivity(i);
                } else if (jenis.equals("malam")) {
//                    listMalam = addFoodActivity.getListDinner();
//                    int idx = addFoodActivity.search(newItem, listMalam);
//                    addFoodActivity.rmArrayMalam(idx);
//                    addFoodActivity.minKaloriMalam(kalori);
//                    editor.putFloat("kaloriMalam", (spref.getFloat("kaloriKalam", 0) - (float) kalori));
//
//                    listMalam = addFoodActivity.getListDinner();
//                    setMalam.addAll(listMalam);
//                    editor.putStringSet("SetMalam", setPagi);
//                    editor.commit();
//                    Intent i = new Intent(DeleteActivity.this, Main2Activity.class);
//                    startActivity(i);
                    Set<String> malam = spref.getStringSet("SetMalam", null);
                    listMalam = new ArrayList<String>(malam);
                    int idx = search(newItem, listMalam);
                    listMalam= rmArray(listMalam, idx);
                    addFoodActivity.minKaloriMalam(kalori);
                    editor.putFloat("kaloriMalam", (spref.getFloat("kaloriMalam", 0) - (float) kalori));

                    setMalam.addAll(listMalam);
                    editor.putStringSet("SetMalam", setMalam);
                    editor.commit();
                    Intent i = new Intent(DeleteActivity.this, Main2Activity.class);
                    startActivity(i);
                }


            }
        });
    }
    public int search (String item,ArrayList<String>arrayList){
        int i = 0;
        for(i=0; i<=arrayList.size();i++)
        {
            if(arrayList.get(i).equals(item)){
                return i;
            }
        }
        return i;

    }
    public ArrayList rmArray (ArrayList<String> arrayList,int idx){
        arrayList.remove(idx);
        return arrayList;
    }
}