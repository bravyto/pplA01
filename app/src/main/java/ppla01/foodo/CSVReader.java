package ppla01.foodo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gema Raditya on 4/26/2016.
 */
public class CSVReader extends ArrayAdapter<FoodDetail> {
    Context ctx;
    String message;
    public CSVReader(Context context,int textViewResourceId, String message) {
        super(context,textViewResourceId);
        this.ctx = context;
        this.message=message.toUpperCase();

        loadArrayFromFile();

    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        TextView mView = (TextView)convertView;

        if(null == mView){
            mView = new TextView(parent.getContext());
            mView.setTextSize(18);
        }

        //Set the state name as the text.
        mView.setText(getItem(pos).getName());



        return mView;
    }

    private void loadArrayFromFile(){
        try {
            CharSequence cs2 = message;
            InputStream is = ctx.getAssets().open("foodData.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {


                String[] RowData = line.split(",");


                FoodDetail cur = new FoodDetail();
                cur.setName(RowData[1]);
                cur.setCalories(RowData[3]);

                if (cur.getName().contains(cs2)) {
                    this.add(cur);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
