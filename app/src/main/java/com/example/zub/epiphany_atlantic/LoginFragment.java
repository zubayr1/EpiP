package com.example.zub.epiphany_atlantic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    TextView signUp;

    public EditText email;
    public  EditText pass;

    Button signIn;

    ImageButton eye_open, eye_closed;

    FirebaseAuth mAuth;

    public void onBackPressed(){

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }






    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



        signUp= (TextView) view.findViewById(R.id.signUp);

        email = (EditText) view.findViewById(R.id.usernameLogin);
        pass = (EditText) view.findViewById(R.id.passwordLogin);

        signIn = (Button) view.findViewById(R.id.signIn);

        mAuth = FirebaseAuth.getInstance();

        email.setText(getArguments().getString("extra1"));
        pass.setText(getArguments().getString("extra2"));

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(i);


            }
        });



        eye_open = (ImageButton) view.findViewById(R.id.non_hidden);
        eye_closed = (ImageButton) view.findViewById(R.id.hidden);


        eye_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass.setTransformationMethod(null);
                eye_closed.setVisibility(View.VISIBLE);
                eye_open.setVisibility(View.INVISIBLE);
                eye_open.setClickable(false);
                eye_closed.setClickable(true);

            }
        });


        eye_closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pass.setTransformationMethod(new PasswordTransformationMethod());
                eye_open.setVisibility(View.VISIBLE);
                eye_closed.setVisibility(View.INVISIBLE);
                eye_open.setClickable(true);
                eye_closed.setClickable(false );

            }
        });



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailstr = email.getText().toString();
                String pass1str = pass.getText().toString();

                if(emailstr.isEmpty()){
                    email.setError("Enter email address");
                }
                else if(pass1str.isEmpty()){
                    pass.setError("Enter password");
                }

                else
                {

                    mAuth.signInWithEmailAndPassword(emailstr,pass1str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            if(task.isSuccessful())
                            {

                                if(user.isEmailVerified()) {
                                    signIn.setClickable(false);

                                    Intent i = new Intent(getActivity(), HomeActivity.class);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getActivity(), "Verification of email is incomplete.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else
                            {


                                Toast.makeText(getActivity(), "Email and Password doesn't match. Try again or swipe left to reset password", Toast.LENGTH_SHORT).show();



                            }

                        }
                    });

                }


            }
        });



    }

    public static LoginFragment newInstance(String text1, String text2) {

        LoginFragment f = new LoginFragment();
        Bundle b = new Bundle();
        b.putString("extra1", text1);
        b.putString("extra2", text2);

        f.setArguments(b);

        return f;
    }


}
