package ppla01.foodo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gema Raditya on 5/18/2016.
 */
public class RecReader extends ArrayAdapter<FoodDetail> {
    Context ctx;
    String message;
    double dBMR;
    public RecReader(Context context,int textViewResourceId, String message, double dBMR) {
        super(context,textViewResourceId);
        this.ctx = context;
        this.message=message.toLowerCase();
        this.dBMR=dBMR;

        loadArrayFromFile2();

    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        TextView mView = (TextView)convertView;

        if(null == mView){
            mView = new TextView(parent.getContext());
            mView.setTextSize(18);
            mView.setPadding(20, 20, 20, 20);
            mView.setTextColor(Color.BLACK);
        }

        //Set the state name as the text.
        mView.setText(getItem(pos).getName());



        return mView;
    }

    private void loadArrayFromFile2(){
        try {
            int counter =0;

            InputStream is = ctx.getAssets().open("DataBaruMakanan.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {


                String[] RowData = line.split(",");
                int a = RowData.length;
                String b = "" + a;

                FoodDetail cur = new FoodDetail();
                cur.setName(RowData[1]);
                cur.setCalories(RowData[2]);
                cur.setVitc(RowData[5]);
                cur.setProtein(RowData[3]);
                cur.setCarbo(RowData[4]);
                cur.setWater(RowData[6]);
                cur.setCalcium(RowData[7]);
                cur.setPorsi(RowData[9]);
                cur.setBeratMakanan(RowData[10]);
                cur.setTipe(RowData[11]);

                double calcul= Double.parseDouble(cur.getCalories());

                if (cur.getTipe().equals(message) && calcul <= dBMR) {
                    counter++;
                    this.add(cur);
                }


            }
            Log.d("itung","" + counter);
            if (counter ==0) {
                FoodDetail cur2 = new FoodDetail();
                cur2.setName("No matches found");
                this.add(cur2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
