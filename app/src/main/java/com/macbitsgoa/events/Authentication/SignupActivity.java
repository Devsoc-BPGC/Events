package com.macbitsgoa.events.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.events.DosmEvent.UserDetailsModal;
import com.macbitsgoa.events.R;


public class SignupActivity extends AppCompatActivity {
    TextView SignupTextView;
    TextView signinTextview;
    EditText username;
    EditText userEmail;
    EditText userPassword;
    Button SignINBtn;
    DatabaseReference databaseRef;
    UserDetailsModal userdeatils =new UserDetailsModal();



    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SignINBtn =findViewById(R.id.signin_button);
        userEmail =findViewById(R.id.user_mail);
        username =findViewById(R.id.useername);
        userPassword=findViewById(R.id.user_password);
        SignupTextView=findViewById(R.id.signup_txt_view);
        signinTextview=findViewById(R.id.signin_txt_view);

        auth=FirebaseAuth.getInstance();
        FirebaseUser currentUser= auth.getCurrentUser();
        if(currentUser!=null)
        {
            Log.e("CuserEmail",currentUser.getEmail());
            Log.e("CuserproviderId",currentUser.getProviderId());


        }

       database =FirebaseDatabase.getInstance();
        databaseRef=database.getReference().child("DosmEvent").child("Users");

        SignupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.useername).setVisibility(View.VISIBLE);
                findViewById(R.id.signup_txt_view).setVisibility(View.GONE);
                findViewById(R.id.signin_txt_view).setVisibility(View.VISIBLE);


                SignINBtn.setText("Sign Up");

            }
        });
        signinTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.useername).setVisibility(View.GONE);
                findViewById(R.id.signup_txt_view).setVisibility(View.VISIBLE);
                findViewById(R.id.signin_txt_view).setVisibility(View.GONE);
                SignINBtn.setText("Sign In");






            }
        });

        SignINBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SignINBtn.getText().equals("Sign Up")){

                   registerUser(userEmail.getText().toString(),userPassword.getText().toString() );

                    //signUP
                    //and SignIN()

                } else {
                    //signIN
                    signInUser(userEmail.getText().toString(),userPassword.getText().toString());

                }




            }
        });


    }

    public void registerUser(String usermail, String password)
    {
        auth.createUserWithEmailAndPassword(usermail,password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            Log.e("userEmail",user.getEmail());
                          //  Log.e("userDisplayName",user.getDisplayName());
                            Log.e("userproviderId",user.getProviderId());
                            userdeatils.name=username.getText().toString();
                            databaseRef.child(user.getUid()).setValue(userdeatils);





                        }

                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    public void signInUser(String userEmail,String userPassword){
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            Log.e("SuserEmail",user.getEmail());
                            Log.e("SuserproviderId",user.getProviderId());


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}
