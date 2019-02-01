package com.example.think.sqlite;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    Button getall, viewall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getall = (Button) findViewById(R.id.getstarted);
        viewall = (Button) findViewById(R.id.viewall);

        final DataBaseHelper dataBaseHelper=new DataBaseHelper(this);

        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = dataBaseHelper.getAllData();
                if (res.getCount() == 0) {
                    showall("Error", "nothing");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name" + res.getString(1) + "\n");
                    buffer.append("Password" + res.getString(2) + "\n");
                }

                showall("data", buffer.toString());


            }
        });

    }

    public void showall(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
}
}