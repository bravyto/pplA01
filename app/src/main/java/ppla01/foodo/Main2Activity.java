package ppla01.foodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private int activeTabNo;
    private TabLayout tabLayout;
    private Menu mOptionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Home");

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.ic_actionbaricon);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_stat);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_eaten);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_reminder);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_profile);
        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.activeTab);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.notActiveTab);
        for(int i = 1; i < 4; i++) {
            tabLayout.getTabAt(i).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.activeTab);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        MenuInflater inflater = getMenuInflater();
                        int tabNo = tabLayout.getSelectedTabPosition();
                        mOptionsMenu.clear();
                        fab.setVisibility(View.INVISIBLE);
                        if (tabNo == 3) {
                            inflater.inflate(R.menu.profile_menu, mOptionsMenu);
                            setTitle("Profile");
                        }
                        else if (tabNo == 2) {
                            inflater.inflate(R.menu.reminder_menu, mOptionsMenu);
                            setTitle("Reminder");
                        }
                        else if (tabNo == 1) {
                            fab.setVisibility(View.VISIBLE);
                            inflater.inflate(R.menu.menu_main2, mOptionsMenu);
                            setTitle("Today's Food");
                        }
                        else {
                            inflater.inflate(R.menu.menu_main2, mOptionsMenu);
                            setTitle("Home");
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.notActiveTab);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) tab.setCustomView(R.layout.view_home_tab);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mOptionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return true;
    }

    protected void onStart(){
        super.onStart();

        SharedPreferences spref = getApplicationContext().getSharedPreferences("my_data", 0);
        if (!spref.getString("log","").equals("1")){
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_edit_reminder) {
            Intent intent = new Intent(Main2Activity.this, JadwalMakanActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return null;
                case 1:
                    return null;
                case 2:
                    return null;
                case 3:
                    return null;
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        SharedPreferences spref;
        SharedPreferences.Editor editor;
        String  nama, tinggi, umur, beratnow, gender, aktivitasnya;
        protected  TextView breakfastv, lunchv, dinnerv;
        protected Button Edit;
        private ArrayList<String> arrayListBreakfast  =new ArrayList<String>();
        private ArrayList<String> arrayListLunch  =new ArrayList<String>();
        private ArrayList<String> arrayListDinner  =new ArrayList<String>();
        private ArrayAdapter<String> adapterBreakfast;
        private ArrayAdapter<String> adapterLunch;
        private ArrayAdapter<String> adapterDinner;
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
                rootView = inflater.inflate(R.layout.fragment_profile, container, false);
                spref = getContext().getSharedPreferences("my_data", 0);

                tinggi = spref.getString("tinggi", "");
                nama = spref.getString("nama", "");
                umur = spref.getString("umur", "");
                beratnow = spref.getString("beratnow", "");
                gender = spref.getString("gender", "");
                aktivitasnya=spref.getString("Activity","");

                TextView tinggiv = (TextView) rootView.findViewById(R.id.height);
                tinggiv.setText(tinggi + " cm");

                TextView namav = (TextView) rootView.findViewById(R.id.name);
                namav.setText(nama);

                TextView umurv = (TextView) rootView.findViewById(R.id.birthdate);
                umurv.setText(umur);

                TextView beratnowv = (TextView) rootView.findViewById(R.id.weight);
                beratnowv.setText(beratnow + " kg");

                TextView genderv = (TextView) rootView.findViewById(R.id.gender);
                genderv.setText(gender);

                TextView aktivitas= (TextView) rootView.findViewById(R.id.activity);
                aktivitas.setText(aktivitasnya);
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_reminder, container, false);

                spref = getContext().getSharedPreferences("my_data", 0);

                TextView breakfast= (TextView) rootView.findViewById(R.id.breakfast);
                breakfast.setText(spref.getString("pagi", ""));
                TextView lunch= (TextView) rootView.findViewById(R.id.lunch);
                lunch.setText(spref.getString("siang", ""));
                TextView dinner= (TextView) rootView.findViewById(R.id.dinner);
                dinner.setText(spref.getString("malam", ""));
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_food, container, false);

                spref = getContext().getSharedPreferences("my_data", 0);

                LinearLayout item = (LinearLayout) rootView.findViewById(R.id.item);
                Set<String> setPagi = spref.getStringSet("SetPagi", null);
                if(setPagi == null) {
                    TextView textView = new TextView(getContext());
                    textView.setText("No food eaten");
                    textView.setPadding(10, 0, 10, 0);
                    item.addView(textView);
                } else {
                    arrayListBreakfast = new ArrayList<>(setPagi);

                    for (int i = 0; i < arrayListBreakfast.size(); i++) {
                        TextView textView = new TextView(getContext());
                        textView.setText(arrayListBreakfast.get(i));
                        textView.setPadding(10, 0, 10, 0);
                        item.addView(textView);
                        if (i != arrayListBreakfast.size() - 1) {
                            ImageView divider = new ImageView(getContext());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                            lp.setMargins(0, 10, 0, 5);
                            divider.setLayoutParams(lp);
                            divider.setBackgroundColor(Color.BLACK);
                            item.addView(divider);
                        }
                    }
                }

                LinearLayout item2 = (LinearLayout) rootView.findViewById(R.id.item2);
                Set<String> setSiang = spref.getStringSet("SetSiang", null);
                if(setSiang == null) {
                    TextView textView = new TextView(getContext());
                    textView.setText("No food eaten");
                    textView.setPadding(10, 0, 10, 0);
                    item2.addView(textView);
                } else {

                    arrayListLunch = new ArrayList<>(setSiang);
                    for (int i = 0; i < arrayListLunch.size(); i++) {
                        TextView textView = new TextView(getContext());
                        textView.setText(arrayListLunch.get(i));
                        textView.setPadding(10, 0, 10, 0);
                        item2.addView(textView);

                        if (i != arrayListLunch.size() - 1) {
                            ImageView divider = new ImageView(getContext());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                            lp.setMargins(0, 10, 0, 5);
                            divider.setLayoutParams(lp);
                            divider.setBackgroundColor(Color.BLACK);
                            item2.addView(divider);
                        }
                    }
                }

                LinearLayout item3 = (LinearLayout) rootView.findViewById(R.id.item3);
                Set<String> setMalam = spref.getStringSet("SetMalam", null);
                if(setMalam == null) {
                    TextView textView = new TextView(getContext());
                    textView.setText("No food eaten");
                    textView.setPadding(10, 0, 10, 0);
                    item3.addView(textView);
                } else {
                    arrayListDinner = new ArrayList<>(setMalam);
                    for (int i = 0; i < arrayListDinner.size(); i++) {
                        TextView textView = new TextView(getContext());
                        textView.setText(arrayListDinner.get(i));
                        textView.setPadding(10, 0, 10, 0);
                        item3.addView(textView);

                        if (i != arrayListDinner.size() - 1) {
                            ImageView divider = new ImageView(getContext());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                            lp.setMargins(0, 10, 0, 5);
                            divider.setLayoutParams(lp);
                            divider.setBackgroundColor(Color.BLACK);
                            item3.addView(divider);
                        }
                    }
                }
            }
            else {
                rootView = inflater.inflate(R.layout.fragment_main2, container, false);
                spref = getContext().getSharedPreferences("my_data", 0);
                GraphView line_graph = (GraphView) rootView.findViewById(R.id.graph);
                line_graph.getViewport().setScrollable(true);
                line_graph.getViewport().setScalable(true);
                // set manual X bounds
                line_graph.getViewport().setXAxisBoundsManual(true);
                line_graph.getViewport().setMinX(0);
                line_graph.getViewport().setMaxX(5);

                // set manual Y bounds
                line_graph.getViewport().setYAxisBoundsManual(true);
                line_graph.getViewport().setMinY(0);
                line_graph.getViewport().setMaxY(99);
                int size = 99;

                //Ini tingaal tambah array of tanggal sama array of konsumsi kalori user
                //Sumber graph bisa liat di
                // https://www.numetriclabz.com/android-line-graph-using-graphview-library-tutorial/
                // http://www.android-graphview.org/documentation
                DataPoint [] values = new DataPoint[size];
                for (int i=0; i<size; i++) {
                    DataPoint v = new DataPoint(i, i);
                    values[i] = v;
                }

                LineGraphSeries<DataPoint> line_series = new LineGraphSeries<DataPoint>(values);
                line_graph.addSeries(line_series);
                line_series.setDrawDataPoints(true);
                line_series.setDataPointsRadius(10);
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(line_graph);
                //staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "Feb", "March","April","June","July"});
                line_graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                line_series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        //Toast.makeText(Main2Activity.this, "Series: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
                    }
                });

//                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }

            return rootView;
        }
    }
}
