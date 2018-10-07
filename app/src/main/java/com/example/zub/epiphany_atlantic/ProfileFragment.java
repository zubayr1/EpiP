package com.example.zub.epiphany_atlantic;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Uri mainImageURI = null;

    CircleImageView profileImageView;

    Button uploadDP;

    ProgressBar progressBar;

     Dialog dialog;

    ImageView userDp, userdpDemo;

    TextView postCount, postText, followerCount, followerText, followingCount, followingText;

    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;

    String userId;

    StorageReference  image_path;

    public DatabaseReference mdb;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        firebaseAuth = FirebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        mdb = FirebaseDatabase.getInstance().getReference("User_profilePic");

        postCount =(TextView) view.findViewById(R.id.postCount);
        postText =(TextView) view.findViewById(R.id.postText);
        followerCount =(TextView) view.findViewById(R.id.followerCount);
        followerText =(TextView) view.findViewById(R.id.followerText);
        followingCount =(TextView) view.findViewById(R.id.followingCount);
        followingText =(TextView) view.findViewById(R.id.followingText);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Courgette.ttf");

        postText.setTypeface(typeface);
        followerText.setTypeface(typeface);
        followingText.setTypeface(typeface);


        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/tnr.ttf");

        postCount.setTypeface(typeface1);
        followerCount.setTypeface(typeface1);
        followingCount.setTypeface(typeface1);

        profileImageView = (CircleImageView) view.findViewById(R.id.profileImageView);

        ValueEventListener ref = FirebaseDatabase.getInstance().getReference().child("User_profilePic").child(userId).child("profilepic")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String ppc = dataSnapshot.getValue(String.class);
                        // Toast.makeText(getActivity(), check, Toast.LENGTH_SHORT).show();

                        RequestOptions placeholderRequest = new RequestOptions();
                        placeholderRequest.placeholder(R.drawable.defimg);
                        try {

                            Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(ppc).into(profileImageView);

                        }catch(Exception e){}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewDialog();
            }
        });

    }

    private void viewDialog() {

        ViewDialog alert = new ViewDialog();
        alert.showDialog(getActivity(), "");

    }


    public class ViewDialog {



        public void showDialog(Activity activity, String key){
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.user_dp_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            userDp = (ImageView) dialog.findViewById(R.id.userDP);
            userdpDemo = (ImageView) dialog.findViewById(R.id.userDPdemo);

            Button editBtn = (Button) dialog.findViewById(R.id.editDP);

            Button removeDp = (Button) dialog.findViewById(R.id.removeDP);

            uploadDP = (Button) dialog.findViewById(R.id.uploadDP);

            progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userId = user.getUid();



            ValueEventListener ref = FirebaseDatabase.getInstance().getReference().child("User_profilePic").child(userId).child("profilepic")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String ppc = dataSnapshot.getValue(String.class);
                            // Toast.makeText(getActivity(), check, Toast.LENGTH_SHORT).show();

                            RequestOptions placeholderRequest = new RequestOptions();
                            placeholderRequest.placeholder(R.drawable.defimg);
                            try {

                                Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(ppc).into(userDp);

                            }catch(Exception e){}

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });





            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    openaccess();




                }
            });

            uploadDP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    gainaccess();

                }
            });


           removeDp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   progressBar.setVisibility(View.VISIBLE);

                   PassProfilePic ppc =new PassProfilePic("https://firebasestorage.googleapis.com/v0/b/eauthor-98c9d.appspot.com/o/profile_pics%2F4QOLzg0xcuZ021fuqBJss3FKqG23.jpg?alt=media&token=17ca4735-4b6b-4e2d-bdb6-005593be7e68");
                   mdb.child(userId).setValue(ppc).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {

                           progressBar.setVisibility(View.GONE);
                           dialog.dismiss();

                       }
                   });

               }
           });


            dialog.show();

        }


    }



    private void gainaccess() {


        uploadDP.setClickable(false);
        uploadDP.setVisibility(View.INVISIBLE);

        progressBar.setVisibility(View.VISIBLE);

        image_path = storageReference.child("profile_pics").child(userId + ".jpg");

        image_path.putFile(mainImageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful())
                {



                    image_path.getDownloadUrl().addOnSuccessListener(
                            new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    //Toast.makeText(getActivity(), "fuck", Toast.LENGTH_SHORT).show();

                                    Uri downloadurl = uri;

                                    DatabaseReference newdatabaseReference = FirebaseDatabase.getInstance().getReference().child("User_profilePic").child(userId);

                                    PassProfilePic passProfilePic = new PassProfilePic(downloadurl.toString());
                                    newdatabaseReference.setValue(passProfilePic).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {



                                            progressBar.setVisibility(View.GONE);

                                        }
                                    });





                                }
                            }
                    );


                    dialog.dismiss();

                }



            }
        });




    }

    private void openaccess() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {




                CropImage.activity(mainImageURI)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .setAutoZoomEnabled(false)
                        .start(getContext(), this);
                ;


                //  startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);

            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                viewDialog();

                mainImageURI = result.getUri();
                profileImageView.setImageURI(mainImageURI);
                userDp.setImageURI(mainImageURI);
                userDp.setVisibility(View.INVISIBLE);

                userdpDemo.setVisibility(View.VISIBLE);
                userdpDemo.setImageURI(mainImageURI);

                uploadDP.setVisibility(View.VISIBLE);
                uploadDP.setClickable(true);

                final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);


                uploadDP.startAnimation(myAnim);

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        ValueEventListener ref = FirebaseDatabase.getInstance().getReference().child("User_profilePic").child(userId).child("profilepic")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        String ppc = dataSnapshot.getValue(String.class);
                                        // Toast.makeText(getActivity(), check, Toast.LENGTH_SHORT).show();

                                        RequestOptions placeholderRequest = new RequestOptions();
                                        placeholderRequest.placeholder(R.drawable.defimg);
                                        try {

                                            Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(ppc).into(profileImageView);

                                        }catch(Exception e){}

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                    }
                });



            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {


                Exception error = result.getError();
            }
        }
    }


    public static ProfileFragment newInstance(String text) {

        ProfileFragment f = new ProfileFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
