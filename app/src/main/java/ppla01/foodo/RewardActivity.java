package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Bravyto on 19/05/2016.
 */
public class RewardActivity  extends Activity {

    protected Button submit_profile;
    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = 360.0f;// 3.141592654f * 32.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reward);


        ImageView image = (ImageView) findViewById(R.id.imageView37);
        RotateAnimation r = new RotateAnimation(ROTATE_FROM, ROTATE_TO, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((long) 2*1500);
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
    }
}
