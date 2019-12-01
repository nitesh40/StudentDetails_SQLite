package com.nitesh.sqlite_learning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    Database myDb;
    TextView idtext;
    EditText name,lastname,marks,id;
    Button add, view, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new Database(this);

        idtext = findViewById(R.id.idText);
        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        marks = findViewById(R.id.marks);
        id = findViewById(R.id.idEditTExt);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewData();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete();

            }
        });

    }

    private void Delete() {

        Integer integer = myDb.deleteData(id.getText().toString());

        if (integer > 0){
            Toast.makeText(MainActivity.this,"Data Deleted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Data Is Not Deleted", LENGTH_LONG).show();
        }

    }

    private void Update() {

        boolean isUpdate = myDb.updateData(id.getText().toString(),name.getText().toString(),lastname.getText().toString(),marks.getText().toString());
            if (isUpdate == true){
                Toast.makeText(MainActivity.this,"Data Updated", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this,"Data Is Not Update", LENGTH_LONG).show();
            }
    }

    private void ViewData() {


        Cursor cursor = myDb.getAllData();

        if (cursor.getCount() == 0){
            //show message
            showMessage("Error", "No Data Found");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append("Id :" + cursor.getString(0 ) + "\n");
                stringBuffer.append("Name :" + cursor.getString(1 ) + "\n");
                stringBuffer.append("LastName :" + cursor.getString(2 ) + "\n");
                stringBuffer.append("Marks :" + cursor.getString(3 ) + "\n\n");
            }
            //show all data
        showMessage("Data",stringBuffer.toString());
    }

    public void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void Add(){

       boolean isInsert = myDb.insertData(name.getText().toString(), lastname.getText().toString(), marks.getText().toString());
        if (isInsert == true) {
            Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Data Is Not Inserted", LENGTH_LONG).show();
        }
    }
}
