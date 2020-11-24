package mad.example.testforme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database1.DBHandlerClass;
import Database1.User;

public class Searching extends AppCompatActivity {

    EditText name;
    Button searchb,edit,delete,update;
    TextView un,pwd,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        name =  findViewById(R.id.editTextTextPersonName3);
        searchb =  findViewById(R.id.button4);
        un =  findViewById(R.id.editTextTextPersonName6);
        dob =  findViewById(R.id.editTextTextPersonName8);
        pwd =  findViewById(R.id.editTextTextPersonName7);
        delete =  findViewById(R.id.button5);
        update = findViewById(R.id.button3);

        searchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerClass dbHelper = new DBHandlerClass(getApplicationContext());
                List USR = dbHelper.readInfo(name.getText().toString());

                if (USR.isEmpty()){
                    Toast.makeText(Searching.this, "No Uesr!", Toast.LENGTH_SHORT).show();
                    un.setText(null);
                    pwd.setText(null);
                    dob.setText(null);
                }
                else {
                    Toast.makeText(Searching.this, "User Found!", Toast.LENGTH_SHORT).show();
                    un.setText(USR.get(0).toString());
                    pwd.setText(USR.get(1).toString());
                    dob.setText(USR.get(2).toString());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerClass dbhandler = new DBHandlerClass(getApplicationContext());
                dbhandler.deleteData(un.getText().toString());
                Toast.makeText(Searching.this, "User Deleted!", Toast.LENGTH_SHORT).show();
                un.setText(null);
                dob.setText(null);
                pwd.setText(null);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHandlerClass dbHelper = new DBHandlerClass(getApplicationContext());
                        Boolean status = dbHelper.updatePart(un.getText().toString(),pwd.getText().toString(),dob.getText().toString());
                        if (status){
                            Toast.makeText(Searching.this, "User Updated!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Searching.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}