package com.example.zub.epiphany_atlantic;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotpassFragment extends Fragment {


    public FirebaseAuth firebaseAuth;
    public EditText editText;

    TextView textView, title;

    Button button;


    public ForgotpassFragment() {
        // Required empty public constructor
    }

    public void onBackPressed(){

        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgotpass, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        editText = (EditText)view.findViewById(R.id.forgotPassEdit);
        textView = (TextView) view.findViewById(R.id.forgotPassText);
        title = (TextView) view.findViewById(R.id.forgotPassTitle);
        button = (Button) view.findViewById(R.id.forgotPassButton);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/tnr.ttf");

        textView.setTypeface(typeface);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Courgette.ttf");

        title.setTypeface(typeface1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = editText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Failed to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });




    }

    public static ForgotpassFragment newInstance(String text) {

        ForgotpassFragment f = new ForgotpassFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


}
