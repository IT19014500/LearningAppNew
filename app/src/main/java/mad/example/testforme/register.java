package mad.example.testforme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database1.DBHandlerClass;

public class register extends AppCompatActivity {

    Button reg;
    EditText uname,pwd,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg = findViewById(R.id.button2);
        uname = findViewById(R.id.editTextTextPersonName);
        pwd = findViewById(R.id.editTextDate);
        dob = findViewById(R.id.editTextTextPassword2);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerClass dbHelper = new DBHandlerClass(getApplicationContext());
                Long uId = dbHelper.addData(uname.getText().toString(),pwd.getText().toString(),dob.getText().toString());
                Toast.makeText(register.this, "User "+uId+" Added!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                uname.setText(null);
                pwd.setText(null);
                dob.setText(null);
            }
        });
    }
}