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

public class UpdateActivity extends AppCompatActivity {
    Button btnUpdate,btnCancel;
    databaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btnCancel = findViewById(R.id.btnCancelUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);

        final EditText etId = (EditText) findViewById(R.id.etIdUp);
        final EditText etName = (EditText) findViewById(R.id.etNameUp);
        final EditText etNumber = (EditText) findViewById(R.id.etNumberUp);
        final EditText etEmail = (EditText) findViewById(R.id.etEmailUp);

        etId.setText(getIntent().getStringExtra("idSV"));
        etName.setText(getIntent().getStringExtra("nameSV"));
        etNumber.setText(getIntent().getStringExtra("numberSV"));
        etEmail.setText(getIntent().getStringExtra("emailSV"));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = etId.getText().toString();
                int idd = Integer.parseInt(a);

                Students students = new Students();

                students.setId(idd);
                students.setName(etName.getText().toString());
                students.setNumber(etNumber.getText().toString());
                students.setEmail(etEmail.getText().toString());

                dh = new databaseHelper(getBaseContext());
                int j = dh.updateStudent(students);

                if(j>0){
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK,null);
                finish();
            }
        });
    }
}