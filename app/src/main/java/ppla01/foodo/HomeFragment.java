package ppla01.foodo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Rezky Pangestu G on 25/04/2016.
 */
public class HomeFragment extends Fragment {

    protected Button button1;
    protected Button button2;
    protected Button button3;
    protected Button button4;
    protected TextView kalori;
    SharedPreferences spref;
    SharedPreferences.Editor editor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.activity_home, container, false);

        spref = this.getActivity().getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
//        kalori = (TextView) view.findViewById(R.id.tampil);
//        kalori.setText(spref.getString("Calori", ""), null);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.fragment_container,profileFragment);
                fragmentTransaction.commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SetAlarmFragment setAlarmFragment = new SetAlarmFragment();
                fragmentTransaction.replace(R.id.fragment_container,setAlarmFragment);
                fragmentTransaction.commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                ProfileFragment profileFragment = new ProfileFragment();
//                fragmentTransaction.replace(R.id.fragment_container,profileFragment);
//                fragmentTransaction.commit();

                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
