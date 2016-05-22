package ppla01.foodo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.w3c.dom.Text;

import java.io.File;

/**
 * Created by Bravyto on 19/05/2016.
 */
public class RewardActivity  extends Activity {

    protected Button submit_profile;
    protected Button share;
    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = 360.0f;// 3.141592654f * 32.0f;
    String  tinggi, beratnow;
    SharedPreferences spref;
    public static double bawahBMI,atasBMI,ideal1,ideal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spref = RewardActivity.this.getSharedPreferences("my_data", 0);
        setContentView(R.layout.activity_reward);
        tinggi = spref.getString("tinggi", "");
        beratnow = spref.getString("beratnow", "");
        double BMI = Double.parseDouble(beratnow)/(Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);

        if (BMI< 18.5){
            atasBMI = 18.5;
            bawahBMI = 18.5;
        }else if (BMI >  18.5 || BMI <= 24.9){
            bawahBMI = 18.5;
            atasBMI = 24.9;
        }
        else if (BMI >= 25 || BMI <= 29.9){
            bawahBMI = 25;
            atasBMI = 29.9;
        }else if (BMI >= 30 || BMI <= 34.9){
            bawahBMI = 30;
            atasBMI = 34.9;
        }
        else if (BMI >= 35 || BMI <= 39.9){
            bawahBMI = 35;
            atasBMI = 39.9;
        }
        else if (BMI >= 40 ){
            bawahBMI = 40;
            atasBMI = 40;
        }

        ideal1 = Math.round(bawahBMI * Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);
        ideal2 = Math.round(atasBMI * Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);


        double oldWeightnya = Double.parseDouble(WeekEvaluationActivity.oldWeight);
        double newWeightnya = Double.parseDouble(WeekEvaluationActivity.newWeight);
        double ideal1nya = ideal1;
        double ideal2nya = ideal2;

        String rewardnya = "";
        ImageView rewardImage = (ImageView) findViewById(R.id.imageView39);
        TextView rewardText = (TextView) findViewById(R.id.rewardText);
        TextView rewardAdvice = (TextView) findViewById(R.id.rewardAdvice);

        if(oldWeightnya < ideal1nya) {
            if (newWeightnya > oldWeightnya) {
                rewardnya = "naik";
                rewardImage.setImageResource(R.drawable.upweight);
                rewardText.setText("Congratulations!");
                rewardAdvice.setText("You are going to your ideal weight by increasing your weight");
            }
        } else if (oldWeightnya > ideal2nya) {
            if (newWeightnya < oldWeightnya) {
                rewardnya = "turun";
                rewardImage.setImageResource(R.drawable.downweight);
                rewardText.setText("Congratulations!");
                rewardAdvice.setText("You are going to your ideal weight by decreasing your weight");
            }
        } else {
            if (newWeightnya <= ideal2nya && newWeightnya >= ideal1nya) {
                rewardnya = "stabil";
                rewardImage.setImageResource(R.drawable.stayweight);
                rewardText.setText("Congratulations!");
                rewardAdvice.setText("You still on your ideal weight. Keep up the good work!");
            }
        }

        ImageView image = (ImageView) findViewById(R.id.imageView37);
        RotateAnimation r = new RotateAnimation(ROTATE_FROM, ROTATE_TO, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((long) 2 * 1500);
        r.setRepeatCount(Animation.INFINITE);
        image.startAnimation(r);
        submit_profile = (Button) findViewById(R.id.button);
        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                finish();
                startActivity(intent);
            }
        });
        share = (Button) findViewById(R.id.button5);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri text = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.drawable.downweight) + '/' + getResources().getResourceTypeName(R.drawable.downweight) + '/' + getResources().getResourceEntryName(R.drawable.downweight));
                TweetComposer.Builder builder = new TweetComposer.Builder(RewardActivity.this)
                        .text("just setting up my Fabric.")
                        .image(text);
                builder.show();
            }
        });
    }
}
