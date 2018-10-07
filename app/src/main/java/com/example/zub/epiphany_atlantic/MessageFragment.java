package com.example.zub.epiphany_atlantic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }


    public static MessageFragment newInstance(String text) {

        MessageFragment f = new MessageFragment();
        Bundle b = new Bundle();
        b.putString("extra1", text);


        f.setArguments(b);

        return f;
    }
}
