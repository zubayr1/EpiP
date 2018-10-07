package com.example.zub.epiphany_atlantic;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    TextView signIn;

    public EditText first;
    public  EditText last;
    public  EditText email;
    public  EditText pass1;
    public  EditText pass2;

    String firststr, laststr, emailstr, pass1str, pass2str;

    Button signUp;

    ImageButton eye_open1, eye_closed1,eye_open2, eye_closed2 ;

    private FirebaseAuth firebaseAuth;
    public DatabaseReference databaseReference;
    public DatabaseReference mdb;

    public void onBackPressed(){
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);


    }


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        signIn= (TextView) view.findViewById(R.id.signIn);

        signUp= (Button) view.findViewById(R.id.signUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);

            }
        });


        first = (EditText) view.findViewById(R.id.firstname);
        last = (EditText) view.findViewById(R.id.lastname);
        email = (EditText) view.findViewById(R.id.username);
        pass1 = (EditText) view.findViewById(R.id.password);
        pass2 = (EditText) view.findViewById(R.id.password2);


        eye_open1 = (ImageButton) view.findViewById(R.id.pass1non_hidden);
        eye_closed1 = (ImageButton)view.findViewById(R.id.pass1hidden);

        eye_open2 = (ImageButton) view.findViewById(R.id.pass2non_hidden);
        eye_closed2 = (ImageButton)view.findViewById(R.id.pass2hidden);



        eye_open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass1.setTransformationMethod(null);
                eye_closed1.setVisibility(View.VISIBLE);
                eye_open1.setVisibility(View.INVISIBLE);
                eye_open1.setClickable(false);
                eye_closed1.setClickable(true);

            }
        });


        eye_closed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass1.setTransformationMethod(new PasswordTransformationMethod());
                eye_open1.setVisibility(View.VISIBLE);
                eye_closed1.setVisibility(View.INVISIBLE);
                eye_open1.setClickable(true);
                eye_closed1.setClickable(false );

            }
        });



        eye_open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass2.setTransformationMethod(null);
                eye_closed2.setVisibility(View.VISIBLE);
                eye_open2.setVisibility(View.INVISIBLE);
                eye_open2.setClickable(false);
                eye_closed2.setClickable(true);

            }
        });


        eye_closed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass2.setTransformationMethod(new PasswordTransformationMethod());
                eye_open2.setVisibility(View.VISIBLE);
                eye_closed2.setVisibility(View.INVISIBLE);
                eye_open2.setClickable(true);
                eye_closed2.setClickable(false );

            }
        });


        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User_Info");
        mdb = FirebaseDatabase.getInstance().getReference("User_profilePic");

        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    if (!s.toString().equals(pass1.getText().toString())) {



                        pass2.setTextColor(Color.parseColor("#e53238"));


                    }
                    else if(s.toString().equals(pass1.getText().toString()))
                    {
                        pass2.setTextColor(Color.parseColor("#B4BAD8"));
                    }

                }catch(Exception e)
                {

                }


            }
        });





        pass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!pass2.getText().toString().isEmpty()) {

                    // Toast.makeText(RegistrationActivity.this, "Verification of email is incomplete.", Toast.LENGTH_SHORT).show();

                    try {
                        if (!s.toString().equals(pass2.getText().toString())) {


                            pass2.setTextColor(Color.parseColor("#e53238"));


                        } else if (s.toString().equals(pass2.getText().toString())) {
                            pass2.setTextColor(Color.parseColor("#B4BAD8"));
                        }

                    } catch (Exception e) {

                    }


                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                 firststr = first.getText().toString();
                 laststr = last.getText().toString();
                 emailstr = email.getText().toString();
                 pass1str = pass1.getText().toString();
                 pass2str = pass2.getText().toString();

                if (firststr.isEmpty()) {

                    first.setError("Enter first name");


                }
                else if(laststr.isEmpty()){
                    last.setError("Enter last name");
                }
                else if(emailstr.equals(null)){
                    email.setError("Enter email address");
                }
                else if(!emailstr.matches(emailPattern))
                {
                    email.setError("Enter a valid email address");
                }
                else if(pass1str.isEmpty()){
                    pass1.setError("Enter password");
                }
                else if(pass1str.length()<4)
                {
                    pass1.setError("Password is short. Password must be of at least 8 characters.");
                }

                else if(pass2str.isEmpty()){
                    pass2.setError("Passwords do not match");
                }
                else if(!pass1str.equals(pass2str))
                {
                    pass2.setError("passwords do not match");
                }

                else {

                    firebaseAuth.createUserWithEmailAndPassword(emailstr,pass1str)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    final FirebaseUser user = firebaseAuth.getCurrentUser();

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();

                                        user.sendEmailVerification();


                                        String id = databaseReference.push().getKey();

                                        FirebaseUser userM = FirebaseAuth.getInstance().getCurrentUser();
                                        String userId = userM.getUid();

                                        String name = first.getText().toString() + " " + last.getText().toString();

                                        name = name.toLowerCase();

                                        PassInfos passInfos = new PassInfos(id, firststr,laststr,emailstr,pass1str, name,userId);
                                        databaseReference.child(userId).setValue(passInfos);

                                        PassProfilePic ppc =new PassProfilePic("https://firebasestorage.googleapis.com/v0/b/eauthor-98c9d.appspot.com/o/profile_pics%2F4QOLzg0xcuZ021fuqBJss3FKqG23.jpg?alt=media&token=17ca4735-4b6b-4e2d-bdb6-005593be7e68");
                                        mdb.child(userId).setValue(ppc);



                                        //firebase Realtime Database to store data .....

                                        Intent i = new Intent(getActivity(), LoginActivity.class);

                                        i.putExtra("extra", email.getText().toString());
                                        i.putExtra("extra2",pass1.getText().toString());

                                        startActivity(i);



                                    }

                                    else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {


                                        Toast.makeText(getActivity(), "User Exists. Try logging in.", Toast.LENGTH_SHORT).show();
                                    }

                                    else
                                    {



                                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }



                                }
                            });


                }


            }
        });





    }



}
