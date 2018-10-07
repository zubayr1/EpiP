package com.example.zub.epiphany_atlantic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity {

    String extra="", extra2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        extra =getIntent().getStringExtra("extra");
        extra2 =getIntent().getStringExtra("extra2");

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return LoginFragment.newInstance(extra,extra2);
                case 1: return ForgotpassFragment.newInstance("SecondFragment, Instance 1");

                default: return LoginFragment.newInstance(extra,extra2);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
