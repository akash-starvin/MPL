package com.example.mpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class CreateAccount extends AppCompatActivity  {

    EditText etName, etPhone, etEmail, etPass, etConPass;
    String sName, sPhone, sEmail, sPass, sConPass, fbPhone, fbEmail;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.TBL_USER_DATA);
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etName = findViewById(R.id.etCaName);
        etPhone = findViewById(R.id.etCaPhone);
        etEmail = findViewById(R.id.etCaEmail);
        etPass = findViewById(R.id.etCaPass);
        etConPass = findViewById(R.id.etCaConpass);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnCaRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });
    }

    private void insertData() {
        MBCreateAccount mb = new MBCreateAccount(sName,sPhone,sEmail);
        myRef.child(sPhone).setValue(mb);
    }

    private void signIn() {
        Log.e("-*-*-*-*-*-*-*", sEmail + sPass);
        mAuth.createUserWithEmailAndPassword(sEmail,sPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // FirebaseUser user = mAuth.getCurrentUser();
                            insertData();
                            new ShowToast(CreateAccount.this,"Signed In");
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            new ShowToast(getApplicationContext(), "Authentication failed.");
                        }
                    }
                });
    }

    private void getdata() {
        boolean flag = true;

        if (etName.getText().toString().isEmpty()) {
            etName.setError("Enter Name");
            flag=false;}

        if (etPhone.getText().toString().isEmpty() || etPhone.getText().length() != 10) {
            etPhone.setError("Enter valid 10 digit Phone Number");
            flag=false;}

        if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Enter Email");
            flag=false;}

        if (etPass.getText().toString().isEmpty() || etPass.getText().length()<6) {
            etPass.setError("Enter valid Password");
            flag=false;}

        if (etConPass.getText().toString().isEmpty()) {
            etConPass.setError("Enter Confirm Password");
            flag=false;}

        if (flag==true)
        {
            String p,c;
            p = etPass.getText().toString();
            c = etConPass.getText().toString();
            if(!p.equals(c))
                etConPass.setError("Password does not match");
            else{
            sConPass = etConPass.getText().toString();
            sPass = etPass.getText().toString();
            sName = etName.getText().toString();
            sPhone = etPhone.getText().toString();
            sEmail = etEmail.getText().toString().toLowerCase();
            Log.e("-*-*-*-*-*-*-*gd", sEmail + sPass);
            final Query checkuserquery = FirebaseDatabase.getInstance().getReference( Constants.TBL_USER_DATA ).orderByChild( "phone" ).equalTo( sPhone );
            checkuserquery.addValueEventListener( chkUserVLE );

            }}

    }
    ValueEventListener chkUserVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    fbPhone = "" + postSnapshot.child( "phone" ).getValue();
                    fbEmail = "" + postSnapshot.child( "email" ).getValue();
                }
                if (sPhone.equals(fbPhone)){
                    etPhone.setError("Phone already Registered");
                }
                else if (sEmail.equals(fbEmail)){
                    etEmail.setError("Email already Registered");
                }
                else {
                    signIn();
                }
            } catch (Exception e) {
                Log.e("-*-**-*-Catch",e.toString());
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("-*-**-*-Canceled",databaseError.toString());
        }
    };

}
