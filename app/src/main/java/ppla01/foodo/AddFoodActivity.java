package ppla01.foodo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AddFoodActivity extends AppCompatActivity {
    protected LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);


        lin = (LinearLayout) findViewById(R.id.linearLayout2);
        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrName);

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AddFoodActivity.this, FoodActivity.class);
                startActivity(i);
            }
        });


    }
}
