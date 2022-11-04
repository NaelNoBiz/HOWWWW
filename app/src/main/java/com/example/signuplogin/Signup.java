package com.example.signuplogin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    private EditText etEmail,etPassword,etConfirmPassword;
    private FirebaseAuth Auth;




    private void connectComponents()
    {
        etEmail=getView().findViewById(R.id.email);
        etPassword=getView().findViewById(R.id.pass);
        etConfirmPassword=getView().findViewById(R.id.con);
        Auth=FirebaseAuth.getInstance();
    }

    public void signup()
    {

        String email,password,confirm;
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();
        confirm=etConfirmPassword.getText().toString();
        TextView RES= getView().findViewById(R.id.OUTPUTSIGNUP);


        if(email.trim().isEmpty()||password.trim().isEmpty()||confirm.trim().isEmpty())
        {
            RES.setText("there is an empty space you forgot");
            return;
        }

        if(!CheckEmail(email))
        {
            RES.setText("email incorrect");

            return;
        }
        if(!CheckPass(password))
        {
            RES.setText("Password not compliant");

            return;
        }
        if(!password.equals(confirm))
        {
            RES.setText("Password Confirmation failed");

            return;
        }
        createUser();
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
    public void createUser() {
        try {
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty() && !etConfirmPassword.getText().toString().isEmpty()) {
                if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    Auth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(getContext(), "Account created.", Toast.LENGTH_SHORT).show();
                                if (Auth.getCurrentUser() != null) {
                                    Auth.signOut();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(getContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Missing fields identified.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void signup(View view) {
        signup();
    }
}