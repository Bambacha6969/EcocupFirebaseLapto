package ecocupfirebase.ecocup;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmailSU, editTextPasswordSU;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
         FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();




        editTextEmailSU = (EditText) findViewById(R.id.editTextEmailSU);
        editTextPasswordSU = (EditText) findViewById(R.id.editTextPasswordSU);

        findViewById(R.id.buttonSIGNUP).setOnClickListener(this);
        findViewById(R.id.tvSIGNtoLOG).setOnClickListener(this);



}


    private void registerUser() {
        String email = editTextEmailSU.getText().toString().trim();
        String password = editTextPasswordSU.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmailSU.setError("Email is required");
            editTextEmailSU.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailSU.setError("Please enter a valid email");
            editTextEmailSU.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPasswordSU.setError("Password is required");
            editTextPasswordSU.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPasswordSU.setError("Minimum lenght of password should be 6");
            editTextPasswordSU.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(getApplicationContext(),"EPITELOUS", Toast.LENGTH_SHORT).show();


                        } else {
                                                    // If sign in fails, display a message to the user.

                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }




    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonSIGNUP:
                registerUser();
                break;

            case R.id.tvSIGNtoLOG:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}