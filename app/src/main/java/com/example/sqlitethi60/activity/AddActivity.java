package com.example.sqlitethi60.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitethi60.R;
import com.example.sqlitethi60.model.Students;
import com.example.sqlitethi60.ultil.databaseHelper;

public class AddActivity extends AppCompatActivity {
    EditText etId,etName,etNumber,etEmaIL;
    Button btnAdd,btnCancel;
    databaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etId = findViewById(R.id.etIdAdd);
        etName = findViewById(R.id.etNameAdd);
        etNumber = findViewById(R.id.etNumberAdd);
        etEmaIL = findViewById(R.id.etEmailAdd);

        btnAdd = findViewById(R.id.btnAddAdd);
        btnCancel = findViewById(R.id.btnCancelAdd);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String name = etName.getText().toString();
             String number = etNumber.getText().toString();
             String email = etEmaIL.getText().toString();

                Students students = new Students(name,number,email);
                dh = new databaseHelper(getBaseContext());
                dh.addStudent(students);
                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK,null);
                finish();
            }
        });
    }
}