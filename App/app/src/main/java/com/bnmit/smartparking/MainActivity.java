package com.bnmit.smartparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    int fastagval[] = {1,2,3,4};
    Button btn;
    EditText editText;
    DatabaseReference databaseReference;
    List<Integer> accessId_list;
//    TextView dataTextView;ArrayList<String> arrFastag = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.submit);
        editText = (EditText) findViewById(R.id.edText_fastag);
//        dataTextView = (TextView) findViewById(R.id.textView);
        ArrayList<String> arrFastag = new ArrayList<>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("accessid");
//        final DatabaseReference myRef = database.getReference().child("accessid");
        reference.child("ids").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot dataSnapshot1: datasnapshot.getChildren()) {

                    arrFastag.add(String.valueOf(dataSnapshot1.getValue()));



                }
                Log.i("OUR VALUE", String.valueOf(arrFastag));
//                dataTextView.setText(String.valueOf(arrFastag));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                dataTextView.setText("Error " + error.toString());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fastId = editText.getText().toString();
                Log.i(String.valueOf(MainActivity.this), "onDataChange: " + fastId );
                Log.i(String.valueOf(MainActivity.this), "onDataChange: " + arrFastag );
//                dataTextView.setText(fastId);

//
        for (int i =0; i<4; i++) {
            Log.i(String.valueOf(MainActivity.this), "onDataChange: " + arrFastag.get(i) );

            if (fastId.equals(arrFastag.get(i))) {
//
                Intent intent = new Intent(getApplicationContext(), SlotActivity.class);
                startActivity(intent);
                break;
            }
//            else
//            {
//                Toast.makeText(getApplicationContext(), "Wrong fasTag ID", Toast.LENGTH_SHORT).show();
//                break;
//            }


        }
    }
//            }
//            Toast.makeText(this, "Wrong fasTag ID", Toast.LENGTH_SHORT).show();






//                long fastId = Long.parseLong(editText.getText().toString());

//                myRef.setValue(fastId);
//                final FirebaseDatabase database = FirebaseDatabase.getInstance();
//                final DatabaseReference myRef = database.getReference();

//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String value = dataSnapshot.getValue(String.class);
//                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                            String id = snapshot.child("id1").getValue().toString();
//                            dataTextView.setText(id);
//                            Log.d(String.valueOf(MainActivity.this), "onDataChange: " + id);
//                        }
//                        Log.d(String.valueOf(MainActivity.this), "onDataChange: " + value);
//                        dataTextView.setText(value);
//                    }

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                        dataTextView.setText("Error " + error.toString());
//                    }
//                });

            });


    }

//    public void onSubmit(View view) {
//
//        int fastId = Integer.parseInt(editText.getText().toString());
//
//        for (int i =0; i<fastagval.length; i++) {
//            if (fastId == fastagval[i]) {
//                Intent intent = new Intent(this, SlotActivity.class);
//                startActivity(intent);
//                break;
//            }
//            else
//            {
//                Toast.makeText(this, "Wrong fasTag ID", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
        }
//            Toast.makeText(this, "Wrong fasTag ID", Toast.LENGTH_SHORT).show();

