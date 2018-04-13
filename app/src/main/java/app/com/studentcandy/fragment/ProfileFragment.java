package app.com.studentcandy.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.studentcandy.EditProActivity;
import app.com.studentcandy.R;
import app.com.studentcandy.bean;


public class ProfileFragment extends Fragment
{

TextView tv_pro_edit;

TextView name , username;

    public ProfileFragment()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        tv_pro_edit=(TextView)view.findViewById(R.id.pro_edit_tv);


        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);


        final bean b = (bean) getContext().getApplicationContext();

        name.setText(b.name);
        username.setText(b.username);

        tv_pro_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getActivity(), EditProActivity.class));

            }
        });
        return view;
    }


}
