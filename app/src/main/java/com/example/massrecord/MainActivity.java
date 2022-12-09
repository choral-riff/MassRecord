package com.example.massrecord;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.massrecord.data.MyDbHandler;
import com.example.massrecord.model.Weight;

public class MainActivity extends AppCompatActivity {

    MyDbHandler db;
    EditText editDate, editWeight, editId;
    Button buttonAdd;
    Button buttonView;
    Button buttonUpdate;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDbHandler(MainActivity.this);

        editDate = findViewById(R.id.editTextDate);
        editWeight = findViewById(R.id.editTextWeight);
        buttonAdd = findViewById(R.id.button_add);
        buttonView = findViewById(R.id.button_view);
        buttonUpdate = findViewById(R.id.button_update);
        editId = findViewById(R.id.editText_id);
        buttonDelete = findViewById(R.id.button_delete);
        addData();
        viewAll();
        DeleteData();
    }

    public void DeleteData(){
        buttonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int deletedRows = db.deleteData(editId.getText().toString());
                        if (deletedRows > 0){
                            Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "data not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void addData(){
        buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Weight weight = new Weight();
                        weight.setWeight(editWeight.getText().toString());
                        weight.setDate(editDate.getText().toString());
                        db.addWeight(weight);
                        Toast.makeText(MainActivity.this, "Data added!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void viewAll(){
        buttonView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = db.getAllData();
                        if (res.getCount() == 0){
                            //show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id: " + res.getString(0) + "\n");
                            buffer.append("Date: " + res.getString(1)+ "\n");
                            buffer.append("Weight: " + res.getString(2)+  "\n\n");
                        }
                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void updateData(){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Weight weight = new Weight();
                        weight.setWeight(editWeight.getText().toString());
                        weight.setDate(editDate.getText().toString());
                        int updated = db.updateWeight(weight);
                    }
                }
        );
    }

    public void  showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}