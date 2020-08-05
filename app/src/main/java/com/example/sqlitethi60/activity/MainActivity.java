package com.example.sqlitethi60.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitethi60.R;
import com.example.sqlitethi60.model.Students;
import com.example.sqlitethi60.ultil.databaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvList;
    Button btnAdd;
    databaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvList = findViewById(R.id.lvList);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
        loadListStudent();
    }

    public void openAddActivity(){
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent,1);
    }
    public void openUpdateActivity(int id,String name,String number,String email){
        Intent intent = new Intent(this,UpdateActivity.class);
        String idd = Integer.toString(id);

        intent.putExtra("idSV",idd);
        intent.putExtra("nameSV",name);
        intent.putExtra("numberSV",number);
        intent.putExtra("emailSV",email);

        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        if(requestCode ==2 && resultCode == RESULT_OK){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
    public  void loadListStudent(){
        dh = new databaseHelper(getBaseContext());
        final List<Students> list = dh.getAllStudent();

        ArrayAdapter<Students> adapter = new ArrayAdapter<Students>(this,android.R.layout.simple_list_item_1,list);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openUpdateActivity(list.get(position).getId(),list.get(position).getName(),list.get(position).getNumber(),
                        list.get(position).getEmail());
            }
        });

        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
        registerForContextMenu(lvList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.lvList){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.menuDelete){
            dh = new databaseHelper(getBaseContext());
            Students students = (Students) lvList.getAdapter().getItem(info.position);

            int del = dh.deleteStudent(students);
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onContextItemSelected(item);
    }
}