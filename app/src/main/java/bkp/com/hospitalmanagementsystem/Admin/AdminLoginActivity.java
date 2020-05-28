package bkp.com.hospitalmanagementsystem.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText emailEdtAdmin, passwordEdtAdmin;
    private Button loginBtnAdmin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();
        emailEdtAdmin = findViewById(R.id.email_admin);
        passwordEdtAdmin = findViewById(R.id.password_admin);
        loginBtnAdmin = findViewById(R.id.login_admin);

        loginBtnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAdmin();


            }
        });

    }

    private void loginAdmin() {

       final String email = emailEdtAdmin.getText().toString();
       final String password = passwordEdtAdmin.getText().toString();

       if(email.equals("") && password.equals("")){
           Toast.makeText(this, "Invalid information.", Toast.LENGTH_SHORT).show();
       }else{
           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){
                       Toast.makeText(AdminLoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(AdminLoginActivity.this,AdminHomeActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);

                   }else{
                       Toast.makeText(AdminLoginActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();

                   }

               }
           });
       }

    }
}
