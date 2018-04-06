package com.example.tranvanmanh.week5excersise;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements dataTaskInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void data(String nameTask, String Date, String priority, String tag) {
        String tagg = "android:switcher:" + R.id.viewpager + ":" + 1;
        DoneFragment doneFragment = (DoneFragment) getSupportFragmentManager().findFragmentByTag(tagg);
        doneFragment.displayListView(nameTask, Date, priority, tag);
    }
}
