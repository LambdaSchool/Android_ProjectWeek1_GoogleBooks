package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabbed_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SearchBooksFragment(), "Search Books");
        viewPagerAdapter.addFragment(new UsersBooksFragment(), "Library");
        viewPagerAdapter.addFragment(new BookshelvesFragment(), "Bookshelves");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.dialog_container);
                if (currentFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
