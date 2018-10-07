package com.example.zub.epiphany_atlantic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    String extra;

    public ViewPager pager;

    ImageButton home, homeMax, message, messageMax, notification, notificationMax, settings, settingsMax, post_icon;

    ImageButton applogoSVG;

    ImageButton nextPage;

    TextView profileName;

    DatabaseReference databaseReference;

    Button follower, following;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pager = (ViewPager) findViewById(R.id.profile_viewpager);
        pager.setAdapter(new ProfileActivity.MyPagerAdapter(getSupportFragmentManager()));

        extra = getIntent().getStringExtra("extra");


        follower = (Button)findViewById(R.id.followerButton);
        following = (Button)findViewById(R.id.followingButton);

        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                follower.setBackgroundResource(R.drawable.followbuttonclicked);
                following.setBackgroundResource(R.drawable.followbuttonnotclick);

            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                following.setBackgroundResource(R.drawable.followbuttonclicked);
                follower.setBackgroundResource(R.drawable.followbuttonnotclick);
            }
        });


        applogoSVG = (ImageButton) findViewById(R.id.applogoSVG);
        home = (ImageButton) findViewById(R.id.bnb_home_icon);
        homeMax = (ImageButton) findViewById(R.id.bnb_home_max);
        message = (ImageButton) findViewById(R.id.bnb_message_icon);
        messageMax = (ImageButton) findViewById(R.id.bnb_message_max);
        notification = (ImageButton) findViewById(R.id.bnb_notification_icon);
        notificationMax = (ImageButton) findViewById(R.id.bnb_notification_max);
        settings = (ImageButton) findViewById(R.id.bnb_settings_icon);
        settingsMax = (ImageButton) findViewById(R.id.bnb_settings_max);

        post_icon = (ImageButton) findViewById(R.id.bnb_post_icon);




        if(extra.equals("0"))
        {
            homeMax.setVisibility(View.VISIBLE);
            home.setVisibility(View.INVISIBLE);
            messageMax.setVisibility(View.INVISIBLE);
            notificationMax.setVisibility(View.INVISIBLE);
            settingsMax.setVisibility(View.INVISIBLE);
            message.setVisibility(View.VISIBLE);
            notification.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
        }
        else if(extra.equals("1"))
        {
            messageMax.setVisibility(View.VISIBLE);
            message.setVisibility(View.INVISIBLE);
            homeMax.setVisibility(View.INVISIBLE);
            notificationMax.setVisibility(View.INVISIBLE);
            settingsMax.setVisibility(View.INVISIBLE);
            home.setVisibility(View.VISIBLE);
            notification.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
        }
        else if(extra.equals("2"))
        {
            home.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
            notification.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);

            bounceAnim();

            homeMax.setVisibility(View.INVISIBLE);
            messageMax.setVisibility(View.INVISIBLE);
            notificationMax.setVisibility(View.INVISIBLE);
            settingsMax.setVisibility(View.INVISIBLE);
        }
        else if(extra.equals("3"))
        {
            notificationMax.setVisibility(View.VISIBLE);
            notification.setVisibility(View.INVISIBLE);
            homeMax.setVisibility(View.INVISIBLE);
            messageMax.setVisibility(View.INVISIBLE);
            settingsMax.setVisibility(View.INVISIBLE);
            home.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
        }
        else if(extra.equals("4"))
        {
            settingsMax.setVisibility(View.VISIBLE);
            settings.setVisibility(View.INVISIBLE);
            homeMax.setVisibility(View.INVISIBLE);
            messageMax.setVisibility(View.INVISIBLE);
            notificationMax.setVisibility(View.INVISIBLE);
            home.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
            notification.setVisibility(View.VISIBLE);
        }




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                i.putExtra("extra", "0");
                i.putExtra("extra1", "true");
                startActivity(i);

            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                i.putExtra("extra", "1");
                i.putExtra("extra1", "true");
                startActivity(i);


            }
        });

        post_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                i.putExtra("extra", "2");
                i.putExtra("extra1", "true");
                startActivity(i);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                i.putExtra("extra", "3");
                i.putExtra("extra1", "true");
                startActivity(i);

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                i.putExtra("extra", "4");
                i.putExtra("extra1", "true");
                startActivity(i);

            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference().child("User_Info");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();


        nextPage = (ImageButton)findViewById(R.id.nextPageButton);

        profileName = (TextView) findViewById(R.id.Profile_name);

        Typeface typeface1 = Typeface.createFromAsset(this.getAssets(), "fonts/tnr.ttf");
        profileName.setTypeface(typeface1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String first, last;

                first=dataSnapshot.child(userId).child("firstname").getValue().toString();
                last=dataSnapshot.child(userId).child("lastname").getValue().toString();

                profileName.setText(first+ " "+ last);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        applogoSVG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewDialog();
            }
        });






        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               pager.setCurrentItem(1);



            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {
                    follower.setVisibility(View.INVISIBLE);
                    follower.setClickable(false);
                    following.setVisibility(View.INVISIBLE);
                    following.setClickable(false);

                    applogoSVG.setVisibility(View.VISIBLE);
                    applogoSVG.setClickable(true);
                    profileName.setVisibility(View.VISIBLE);
                    profileName.setClickable(true);
                    nextPage.setVisibility(View.VISIBLE);
                    nextPage.setClickable(true);
                }

                else if(position==1)
                {
                    follower.setVisibility(View.VISIBLE);
                    follower.setClickable(true);
                    following.setVisibility(View.VISIBLE);
                    following.setClickable(true);

                    applogoSVG.setVisibility(View.INVISIBLE);
                    applogoSVG.setClickable(false);
                    profileName.setVisibility(View.INVISIBLE);
                    profileName.setClickable(false);
                    nextPage.setVisibility(View.INVISIBLE);
                    nextPage.setClickable(false);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void viewDialog() {


        ViewDialog alert = new ViewDialog();
        alert.showDialog(this,"");

    }



    public class ViewDialog {

       public void showDialog(Activity activity, String key){

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog);
           dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView dialogtext = (TextView) dialog.findViewById(R.id.dialogText);

            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Courgette.ttf");



            dialogtext.setTypeface(typeface);


            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });


            dialog.show();

        }
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

                case 0: return ProfileFragment.newInstance("FirstFragment, Instance 1");
                case 1: return FollowerFragment.newInstance("SecondFragment, Instance 1");

                default: return ProfileFragment.newInstance("FIrstFragment, Instance 1");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
