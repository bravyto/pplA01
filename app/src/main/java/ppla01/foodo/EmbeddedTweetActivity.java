package ppla01.foodo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

/**
 * Created by Bravyto on 22/05/2016.
 */
public class EmbeddedTweetActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);

//        // TODO: Use a more specific parent
//        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
//        // TODO: Base this Tweet ID on some data from elsewhere in your app
//        long tweetId = 631879971628183552L;
//        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
//            @Override
//            public void success(Result<Tweet> result) {
//                TweetView tweetView = new TweetView(EmbeddedTweetActivity.this, result.data);
//                parentView.addView(tweetView);
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Load Tweet failure", exception);
//            }
//        });



        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("just setting up my Fabric.");
        builder.show();
    }



}
