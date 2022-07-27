package com.bnmit.smartparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class Activity_payment extends AppCompatActivity {
    Button btnn2;
    Button btnn3;
    String time1,time2;
    private static final  DecimalFormat df= new DecimalFormat("0.00");
            Double a,b,c,d,amount;
    TextView tvtime1, tvtime2,tvtotalt,tv_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnn3 = (Button) findViewById(R.id.calculate);
        tvtime1 = (TextView) findViewById(R.id.id_time1);
        tvtime2 = (TextView) findViewById(R.id.id_time2);
        tvtotalt = (TextView) findViewById(R.id.id_parkingtime);
        tv_amount = (TextView) findViewById(R.id.id_amount);
        ArrayList<String> timedur= new ArrayList<>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Charges");
        DatabaseReference reference1= reference.child("Timing");
        reference1.child("Slot1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    timedur.add(String.valueOf(dataSnapshot1.getValue()));


                }
                Log.i("OUR VALUE", String.valueOf(timedur));
                time1 = timedur.get(0);
                time2 = timedur.get(1);
                tvtime1.setText(String.valueOf(timedur.get(0)));
                tvtime2.setText(String.valueOf(timedur.get(1)));
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date1 = null;
                try {
                    date1 = format.parse(time1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = format.parse(time2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long differenceInMilliSeconds
                        = Math.abs(date2.getTime() - date1.getTime());

                // Calculating the difference in Hours
                long differenceInHours
                        = (differenceInMilliSeconds / (60 * 60 * 1000))
                        % 24;

                // Calculating the difference in Minutes
                long differenceInMinutes
                        = (differenceInMilliSeconds / (60 * 1000)) % 60;

                // Calculating the difference in Seconds
                long differenceInSeconds
                        = (differenceInMilliSeconds / 1000) % 60;
                tvtotalt.setText(String.valueOf("=" +differenceInHours + " hours "
                        + differenceInMinutes + " minutes "
                        + differenceInSeconds + " seconds. "));

                a= (double)differenceInHours;
                b= (double)differenceInMinutes;
                c= (double)differenceInSeconds;
                d= (double)differenceInMilliSeconds;
                Log.i(String.valueOf(Activity_payment.this), "onDataChange: " + a);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.i(String.valueOf(Activity_payment.this), "onDataChange: " + timedur);

               if(a<1)
               {
                  amount=0.00;
               }
               else
               {
                   amount=(a-1)*20+(b/3);}


               tv_amount.setText("="+"Rs"+" "+df.format(amount));
            }
        });

            }
}