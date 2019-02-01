package com.example.think.sqlite;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Registration extends AppCompatActivity {

    EditText edtname, edtpassword, edtmobile, edtemail, edtid;
    ImageView img;
    TextView txtdate;
    TextView txtnext;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        edtname = findViewById(R.id.edtname);
        edtpassword = findViewById(R.id.edtpassword);
        edtmobile = findViewById(R.id.edtmobile);
        edtemail = findViewById(R.id.edtemail);
        txtdate = findViewById(R.id.txtdate);
        //edtid =(EditText) findViewById(R.id.edtid);
        txtnext = findViewById(R.id.txtnext);

        img = findViewById(R.id.imgdate);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getSupportFragmentManager(), "Date Picker");

            }


        });


        final DataBaseHelper dataBaseHelper = new DataBaseHelper(this);


        btnsubmit = findViewById(R.id.btnsubmit);

        txtnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String name = edtname.getText().toString();
                final String password = edtpassword.getText().toString();
                final String date = txtdate.getText().toString();
                final String mobile = edtmobile.getText().toString();
                final String email = edtemail.getText().toString();


                if (edtname.length() <= 0 || edtpassword.length() <= 0 || txtdate.length() <= 0 ||
                        edtmobile.length() <= 0 || edtemail.length() <= 0 ||
                        name.isEmpty() || password.isEmpty() ||
                        date.isEmpty() || mobile.isEmpty()
                        || email.isEmpty()) {

                    if (name.isEmpty()) {
                        edtname.setError("Enter  Valid User Name");
                    }
                    if (password.isEmpty()) {
                        edtpassword.setError("Enter Valid Password ");

                    }

                    if (mobile.isEmpty()) {
                        edtmobile.setError("Enter Valid Mobile.No ");

                    }
                    if (email.isEmpty()) {
                        edtemail.setError("Enter Valid Email Address");
                    }
                }
                if (name.length() <= 0 || password.length() <= 0 || mobile.length() <= 0 || email.length() <= 0 || date.length() <= 0) {
                    Toast.makeText(Registration.this, "Please Enter All Filled", Toast.LENGTH_SHORT).show();
                } else { boolean result = dataBaseHelper.OnInsert(name, password, date, mobile, email );
                    if (result = true) {
                        Toast.makeText(Registration.this, "Data is Inserted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registration.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //public Dialog onCreateDialog(Bundle savedInstanceState){
            Calendar calendar = Calendar.getInstance();
            //  final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);

            /*
                add(int field, int value)
                    Adds the given amount to a Calendar field.
             */
            // Add  days to Calendar
            //calendar.add(Calendar.DATE ,1);
            calendar = Calendar.getInstance();


            /*
                getTimeInMillis()
                    Returns the time represented by this Calendar,
                    recomputing the time from its fields if necessary.

                getDatePicker()
                Gets the DatePicker contained in this dialog.

                setMinDate(long minDate)
                    Sets the minimal date supported by this NumberPicker
                    in milliseconds since January 1, 1970 00:00:00 in getDefault() time zone.

                setMaxDate(long maxDate)
                    Sets the maximal date supported by this DatePicker in milliseconds
                    since January 1, 1970 00:00:00 in getDefault() time zone.
             */

            // Set the Calendar new date as maximum date of date picker


            //this is validation as properly fixed date
            //dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());

            // Subtract 6 days from Calendar updated date
            //calendar.add(Calendar.DATE, -8);


            // Set the Calendar new date as minimum date of date picker
            //  dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());

            // So, now date picker selectable date range is 7 days only

            // Return the DatePickerDialog
            return dpd;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            TextView predate = getActivity().findViewById(R.id.txtdate);

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style and locale
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
            String formattedDate = df.format(chosenDate);

            // Display the chosen date to app interface
            predate.setText(formattedDate);
        }

    }
}


    /* final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Registration");
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(Registration.this);
                dialog.setMessage("Please Waiting.....");
                dialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtid.getText().toString()).exists())

                        {

                            dialog.dismiss();

                            Toast.makeText(Registration.this, "User Name Already Registraton!!!! ", Toast.LENGTH_SHORT).show();
                        } else {


                            dialog.dismiss();

                            Registration_User user = new Registration_User(edtname.getText().toString(),
                                    edtpassword.getText().toString(), edtmobile.getText().toString(),
                                    edtemail.getText().toString(), txtdate.getText().toString(), edtid.getText().toString());

                            table_user.child(edtid.getText().toString()).setValue(user);
                            Toast.makeText(Registration.this, "Registration Suceessfully !!!! ", Toast.LENGTH_SHORT).show();

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }*/

