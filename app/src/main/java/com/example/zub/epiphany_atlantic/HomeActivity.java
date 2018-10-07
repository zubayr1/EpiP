package com.example.zub.epiphany_atlantic;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    TextView title;

    ImageButton home, homeMax, message, messageMax, notification, notificationMax, settings, settingsMax, post_icon;

    ImageView search,new_message, camera_open, mute_notification, logout;

    RelativeLayout homeR, messageR, notificationR, settingsR;

    ImageView profile_button;

    String fragmentId;
    String extra;

    String check = "false";
    @Override
    public void onBackPressed() {

            Intent startMain = new Intent(HomeActivity.this, HomeActivity.class);

            startActivity(startMain);
            finish();
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ViewPager pager = (ViewPager) findViewById(R.id.home_viewpager);
        pager.setAdapter(new HomeActivity.MyPagerAdapter(getSupportFragmentManager()));

        title = (TextView) findViewById(R.id.homeText);

        Typeface typeface1 = Typeface.createFromAsset(this.getAssets(), "fonts/Courgette.ttf");

        title.setTypeface(typeface1);

        fragmentId =getIntent().getStringExtra("extra");
        check=getIntent().getStringExtra("extra1");

       // Toast.makeText(HomeActivity.this, fragmentId, Toast.LENGTH_SHORT).show();

        search = (ImageView) findViewById(R.id.search);
        new_message = (ImageView) findViewById(R.id.new_message);
        camera_open = (ImageView) findViewById(R.id.camera_open);
        mute_notification = (ImageView) findViewById(R.id.mute_notification);
        logout = (ImageView) findViewById(R.id.logout);


        home = (ImageButton) findViewById(R.id.bnb_home_icon);
        homeMax = (ImageButton) findViewById(R.id.bnb_home_max);
        message = (ImageButton) findViewById(R.id.bnb_message_icon);
        messageMax = (ImageButton) findViewById(R.id.bnb_message_max);
        notification = (ImageButton) findViewById(R.id.bnb_notification_icon);
        notificationMax = (ImageButton) findViewById(R.id.bnb_notification_max);
        settings = (ImageButton) findViewById(R.id.bnb_settings_icon);
        settingsMax = (ImageButton) findViewById(R.id.bnb_settings_max);

        post_icon = (ImageButton) findViewById(R.id.bnb_post_icon);

        profile_button = (ImageView) findViewById(R.id.profile_button);


        homeR= (RelativeLayout) findViewById(R.id.homeR);
        messageR= (RelativeLayout) findViewById(R.id.messageR);
        notificationR= (RelativeLayout) findViewById(R.id.notificationR);
        settingsR= (RelativeLayout) findViewById(R.id.settingsR);






        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {

                    homeSet();

                    homeMax.setVisibility(View.VISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("EpiPhany");

                }
                else if(position==1)
                {

                    messageSet();
                    messageMax.setVisibility(View.VISIBLE);
                    message.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("Chat");

                }
                else if(position==2)
                {

                    postSet();
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    bounceAnim();

                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);

                    title.setText("Add Post");

                }

                else if(position==3)
                {
                    notificationSet();
                    notificationMax.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("Notification");

                }
                else if(position==4)
                {
                    setttingsSet();
                    settingsMax.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);

                    title.setText("Settings");

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        try {

            if (check.equals("true")) {
                if (fragmentId.equals("0")) {

                    pager.setCurrentItem(0);
                    homeSet();
                    homeMax.setVisibility(View.VISIBLE);
                    home.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("EpiPhany");
                    check = "false";

                } else if (fragmentId.equals("1")) {
                    pager.setCurrentItem(1);
                    messageSet();
                    messageMax.setVisibility(View.VISIBLE);
                    message.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("Chat");
                    check = "false";

                } else if (fragmentId.equals("2")) {

                    pager.setCurrentItem(2);
                    postSet();
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    bounceAnim();

                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);

                    title.setText("Add Post");
                    check = "false";


                } else if (fragmentId.equals("3")) {
                    pager.setCurrentItem(3);
                    notificationSet();
                    notificationMax.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    settingsMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);

                    title.setText("Notification");
                    check = "false";

                } else if (fragmentId.equals("4")) {
                    pager.setCurrentItem(4);
                    setttingsSet();
                    settingsMax.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.INVISIBLE);
                    homeMax.setVisibility(View.INVISIBLE);
                    messageMax.setVisibility(View.INVISIBLE);
                    notificationMax.setVisibility(View.INVISIBLE);
                    home.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    notification.setVisibility(View.VISIBLE);

                    title.setText("Settings");
                    check = "false";

                }


            }
        }catch (Exception e)
        {

        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeSet();
                homeMax.setVisibility(View.VISIBLE);
                home.setVisibility(View.INVISIBLE);
                messageMax.setVisibility(View.INVISIBLE);
                notificationMax.setVisibility(View.INVISIBLE);
                settingsMax.setVisibility(View.INVISIBLE);
                message.setVisibility(View.VISIBLE);
                notification.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);

                pager.setCurrentItem(0);

            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageSet();
                messageMax.setVisibility(View.VISIBLE);
                message.setVisibility(View.INVISIBLE);
                homeMax.setVisibility(View.INVISIBLE);
                notificationMax.setVisibility(View.INVISIBLE);
                settingsMax.setVisibility(View.INVISIBLE);
                home.setVisibility(View.VISIBLE);
                notification.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);

                pager.setCurrentItem(1);


            }
        });

        post_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postSet();
                home.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                notification.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);

                bounceAnim();

                homeMax.setVisibility(View.INVISIBLE);
                messageMax.setVisibility(View.INVISIBLE);
                notificationMax.setVisibility(View.INVISIBLE);
                settingsMax.setVisibility(View.INVISIBLE);

                pager.setCurrentItem(2);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationSet();
                notificationMax.setVisibility(View.VISIBLE);
                notification.setVisibility(View.INVISIBLE);
                homeMax.setVisibility(View.INVISIBLE);
                messageMax.setVisibility(View.INVISIBLE);
                settingsMax.setVisibility(View.INVISIBLE);
                home.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);

                pager.setCurrentItem(3);

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setttingsSet();
                settingsMax.setVisibility(View.VISIBLE);
                settings.setVisibility(View.INVISIBLE);
                homeMax.setVisibility(View.INVISIBLE);
                messageMax.setVisibility(View.INVISIBLE);
                notificationMax.setVisibility(View.INVISIBLE);
                home.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                notification.setVisibility(View.VISIBLE);

                pager.setCurrentItem(4);

            }
        });


        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra = String.valueOf(pager.getCurrentItem());

                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                i.putExtra("extra", extra);
                startActivity(i);

            }
        });


    }

    private void setttingsSet() {

        search.setVisibility(View.INVISIBLE);
        new_message.setVisibility(View.INVISIBLE);
        camera_open.setVisibility(View.INVISIBLE);
        mute_notification.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.VISIBLE);

        search.setClickable(false);
        new_message.setClickable(false);
        camera_open.setClickable(false);
        mute_notification.setClickable(false);
        logout.setClickable(true);

    }

    private void notificationSet() {

        search.setVisibility(View.INVISIBLE);
        new_message.setVisibility(View.INVISIBLE);
        camera_open.setVisibility(View.INVISIBLE);
        mute_notification.setVisibility(View.VISIBLE);
        logout.setVisibility(View.INVISIBLE);

        search.setClickable(false);
        new_message.setClickable(false);
        camera_open.setClickable(false);
        mute_notification.setClickable(true);
        logout.setClickable(false);

    }

    private void postSet() {

        search.setVisibility(View.INVISIBLE);
        new_message.setVisibility(View.INVISIBLE);
        camera_open.setVisibility(View.VISIBLE);
        mute_notification.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.INVISIBLE);

        search.setClickable(false);
        new_message.setClickable(false);
        camera_open.setClickable(true);
        mute_notification.setClickable(false);
        logout.setClickable(false);
    }

    private void messageSet() {

        new_message.setVisibility(View.VISIBLE);
        search.setVisibility(View.INVISIBLE);
        camera_open.setVisibility(View.INVISIBLE);
        mute_notification.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.INVISIBLE);

        search.setClickable(false);
        new_message.setClickable(true);
        camera_open.setClickable(false);
        mute_notification.setClickable(false);
        logout.setClickable(false);

    }

    private void homeSet() {

        search.setVisibility(View.VISIBLE);
        new_message.setVisibility(View.INVISIBLE);
        camera_open.setVisibility(View.INVISIBLE);
        mute_notification.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.INVISIBLE);

        search.setClickable(true);
        new_message.setClickable(false);
        camera_open.setClickable(false);
        mute_notification.setClickable(false);
        logout.setClickable(false);
    }

    private void bounceAnim() {


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        post_icon.startAnimation(myAnim);


    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int pos) {
            switch(pos) {



                case 0:

                    return HomeFragment.newInstance("FirstFragment, Instance 1");
                case 1:

                    return MessageFragment.newInstance("SecondFragment, Instance 1");
                case 2:

                    return PostFragment.newInstance("ThirdFragment, Instance 1");
                case 3:

                    return NotificationFragment.newInstance("ForthFragment, Instance 1");
                case 4:

                    return SettingsFragment.newInstance("FifthFragment, Instance 1");

                default:

                    return HomeFragment.newInstance("FirstFragment, Instance 1");
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }



        @Override
        public int getCount() {
            return 5;
        }



    }

}
