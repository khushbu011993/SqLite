package com.example.think.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText ledtname, ledtpassword;
    Button btnlogin;
    TextView txtcreate;
    Button all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



      // all=(Button)findViewById(R.id.all);

        ledtname = (EditText) findViewById(R.id.ledtname);
        ledtpassword = (EditText) findViewById(R.id.ledtpassword);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        txtcreate=(TextView)findViewById(R.id.txtcreate);


        final DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        final SQLiteDatabase database=dataBaseHelper.getReadableDatabase();

       /* all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=dataBaseHelper.getAllData();
                if (res.getCount()==0)
                {
                    showall("Error","nothing");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Name"+res.getString(1)+"\n");
                    buffer.append("Password"+res.getString(2)+"\n");
                }

                showall("data",buffer.toString());
            }
        });*/

        txtcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String name = ledtname.getText().toString();
                final String password = ledtpassword.getText().toString();


                if (ledtname.length() <= 0 || ledtpassword.length() <= 0 ||
                        name.isEmpty() || password.isEmpty()) {

                    if (name.isEmpty()) {
                        ledtname.setError("Enter Valid User Name");
                    }
                    if (password.isEmpty()) {
                        ledtpassword.setError("Enter Valid Password ");

                    }
                }
                if (name.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(Login.this, "Please Valid Username and password", Toast.LENGTH_SHORT).show();
                } else {


                    String[] columns = {"Name", "Password"};
                    String[] Cvalues = {ledtname.getText().toString(), ledtpassword.getText().toString()};

                    Cursor cursor = database.query("Registration", columns, "Name = ? AND Password = ?", Cvalues, null, null, null);

                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            Toast.makeText(Login.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Login.this, "Enter Correct Login Username and Password ", Toast.LENGTH_SHORT).show();

                    } else {




                    }

                }
            }


          });

}

/*public void showall(String title,String message)
{
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}*/
}