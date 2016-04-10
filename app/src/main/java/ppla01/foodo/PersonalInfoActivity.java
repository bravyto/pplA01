package ppla01.foodo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

/**
 * Created by Rezky Pangestu G on 10/04/2016.
 */
public class PersonalInfoActivity extends Activity {
    MainActivity m = new MainActivity();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
    }
}
