package com.magbanua.camilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;

    private EditText fullname;
    private EditText age;
    private EditText gender;
    private TextView search;

    ArrayList<String> keylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("person");
        keylist = new ArrayList<>();
        init();
    }

    private void init(){
        fullname = findViewById(R.id.fullnameTxt);
        age = findViewById(R.id.ageTxt);
        gender = findViewById(R.id.genderTxt);
        search = findViewById(R.id.searchOut);
    }

    public void saveClick(View v){
        if(v.getId() == R.id.saveBtn){
            int a;
            String name, gen;
            try{
                name = fullname.getText().toString().trim();
                gen = gender.getText().toString().trim();
                a = Integer.parseInt(age.getText().toString().trim());
                Person person_info = new Person(name,a,gen);
                System.out.println(person_info);
                String key = root.push().getKey();
                root.child(key).setValue(person_info);
                keylist.add(key);
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int lastObj = (int) dataSnapshot.getChildrenCount() -1;
                        System.out.println(lastObj);
                        Person per = dataSnapshot.child(keylist.get(lastObj)).getValue(Person.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Cannot put null values", Toast.LENGTH_LONG).show();
            }


        }
    }
}
