package mad.example.testforme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database1.DBHandlerClass;

public class Login extends AppCompatActivity {

    EditText u_name,pwd;
    TextView sign;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        u_name = findViewById(R.id.editTextTextPersonName2);
        pwd = findViewById(R.id.editTextTextPassword);
        sign = findViewById(R.id.textView2);
        log = findViewById(R.id.button);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),register.class);
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerClass dbHandler = new DBHandlerClass(getApplicationContext());
                if(dbHandler.loginPart(u_name.getText().toString(),pwd.getText().toString())){
                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),home.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Login.this, "Sorry! User Credentials Incorrect.", Toast.LENGTH_SHORT).show();
                    u_name.setText(null);
                    pwd.setText(null);
                }

            }
        });

    }
}