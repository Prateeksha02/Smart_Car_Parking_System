package com.bnmit.smartparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotActivity extends AppCompatActivity {
    Button btnn;
    Button btnn1;
    DatabaseReference databaseReference;
    String slot1, slot2, slot3, slot4;
    TextView tvSlot1, tvSlot2, tvSlot3, tvSlot4;
//    List<String> places_list,accessId_list;


//    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);
        btnn = (Button) findViewById(R.id.check_slot);
        btnn1 = (Button) findViewById(R.id.paymentpage);
        tvSlot1 = (TextView) findViewById(R.id.tv_slot1);
        tvSlot2 = (TextView) findViewById(R.id.tv_slot2);
        tvSlot3 = (TextView) findViewById(R.id.tv_slot3);
        tvSlot4 = (TextView) findViewById(R.id.tv_slot4);
        ArrayList<String> arrslots = new ArrayList<>();
//        spinner = findViewById(R.id.spin);
        //  databaseReference = FirebaseDatabase.getInstance().getReference("places");
        //  ArrayList<HashMap<String, String>> list = new ArrayList<>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Banashankari");
        reference.child("Slots").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    arrslots.add(String.valueOf(dataSnapshot1.getValue()));


                }
                Log.i("OUR VALUE", String.valueOf(arrslots));

//                tvSlot1.setText(String.valueOf(arrslots.get(0)));
//        tvSlot2.setText(String.valueOf(arrslots.get(1)));
//        tvSlot3.setText(String.valueOf(arrslots.get(2)));
//       tvSlot4.setText(String.valueOf(arrslots.get(3)));
                slot1 = arrslots.get(0);
                slot2 = arrslots.get(1);
                slot3 = arrslots.get(2);
                slot4 = arrslots.get(3);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//               dataTextView.setText("Error " + error.toString());
            }
        });
//        slot1.setText(String.valueOf(arrslots.get(0)));
//        slot2.setText(String.valueOf(arrslots.get(1)));
//        slot3.setText(String.valueOf(arrslots.get(2)));
//        slot4.setText(String.valueOf(arrslots.get(3)));
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.i(String.valueOf(SlotActivity.this), "onDataChange: " + arrslots);

                if (!slot1.equals("0")) {
//                    tvSlot1.setBackgroundColor(Color.parseColor("#FF0000"));
//                    tvSlot1.setBackground(R.drawable.rounded_corner_view);

                    tvSlot1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_corner_view));
                } else {
//                    tvSlot1.setBackgroundColor(Color.parseColor("#00FF00"));
                    tvSlot1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounder_corner_view2));
                }
                    if (!slot2.equals("0"))
                        tvSlot2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_corner_view));
//                        tvSlot2.setBackgroundColor(Color.parseColor("#FF0000"));
                    else {
                        tvSlot2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounder_corner_view2));
//                        tvSlot2.setBackgroundColor(Color.parseColor("#00FF00"));
                    }
                    if (!slot3.equals("0")) {
//                        tvSlot3.setBackgroundColor(Color.parseColor("#FF0000"));
                        tvSlot3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_corner_view));
                    } else {
//                        tvSlot3.setBackgroundColor(Color.parseColor("#00FF00"));
                        tvSlot3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounder_corner_view2));
                    }
                    if (!slot4.equals("0")) {
//                        tvSlot4.setBackgroundColor(Color.parseColor("#FF0000"));
                        tvSlot4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_corner_view));
                    } else {
//                        tvSlot4.setBackgroundColor(Color.parseColor("#00FF00"));
                        tvSlot4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounder_corner_view2));
                    }


            }
        });
        btnn1.setOnClickListener(new View.OnClickListener()
                {
        @Override
        public void onClick(View view){
            Intent intent2 = new Intent(getApplicationContext(),Activity_payment.class);
            startActivity(intent2);

        }} );
    }
}




//










//        final DatabaseReference myRef = database.getReference().child("places");
   //     reference.child("slots").addValueEventListener(new ValueEventListener() {
       //     @Override
    //        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
      //          if (datasnapshot == null) {
//                    return list;


       //         for (DataSnapshot dataSnapshot1 : datasnapshot.getChildren()) {


                    //.add(String.valueOf(dataSnapshot1.getValue()));
//                        list.put(key, value)
//

         //       }
                //     Log.i("OUR VALUE", String.valueOf(arrFastag));
//                dataTextView.setText(String.valueOf(arrFastag));


//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
            //        dataTextView.setText("Error " + error.toString());



   //