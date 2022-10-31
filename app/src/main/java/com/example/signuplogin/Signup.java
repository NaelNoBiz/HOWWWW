package com.example.signuplogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
/** ///////////////////////////////////////////////////////////////////////////////////////////////// */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            connectComponents();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    private EditText etEmail,etPassword,etConfirmPassword;



    private void connectComponents()
    {
        etEmail=findViewById(R.id.email);
        etPassword=findViewById(R.id.pass);
        etConfirmPassword=findViewById(R.id.con);
    }

    public void signup(View view)
    {

        String email,password,confirm;
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();
        confirm=etConfirmPassword.getText().toString();


        if(email.trim().isEmpty()||password.trim().isEmpty()||confirm.trim().isEmpty())
        {
            Toast.makeText(this,"there is an empty space you forgot",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!CheckEmail(email))
        {
            Toast.makeText(this,"email incorrect",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!CheckPass(password))
        {
            Toast.makeText(this,"Password not compliant",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirm))
        {
            Toast.makeText(this,"Password Confirmation failed",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    public boolean CheckEmail(String CHECK)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(CHECK);
        return matcher.matches();
    }
    public boolean CheckPass(String CHECK)
    {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(CHECK);

        return matcher.matches();
    }
}