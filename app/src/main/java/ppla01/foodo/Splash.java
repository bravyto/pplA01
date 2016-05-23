package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Bravyto on 09/04/2016.
 */
public class Splash extends Activity{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "HB1nBve0OylJTx9d5xqO95wqC";
    private static final String TWITTER_SECRET = "kF7alauDdMq1GcfMqFx6VAzbPqSBstpcc8nQFexVQqoZ1LTYWZ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.splash);

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        final ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        final ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        final ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate1);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {

            }
            @Override
            public void onAnimationEnd (Animation animation) {
                iv.startAnimation(an2);
                iv2.startAnimation(an2);
                iv3.startAnimation(an2);
                iv4.startAnimation(an2);
                iv5.startAnimation(an2);
                finish();
                Intent i = new Intent(Splash.this, Main2Activity.class);
                startActivity(i);
            }
            @Override
            public void onAnimationRepeat (Animation animation) {

            }
        });
    }
}
