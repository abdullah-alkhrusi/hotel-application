package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button re,del,upd,cal,cle,clo,in,room1,room2,room3,room4;
    TextView details;
    EditText name,day,phone,email,number;
    int price=0;
    hoteldatabase DataBase;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName3);
        day = (EditText) findViewById(R.id.editTextTextPersonName2);
        phone = (EditText) findViewById(R.id.editTextTextPersonName4);
        email = (EditText) findViewById(R.id.editTextTextPersonName5);
        number = (EditText) findViewById(R.id.editTextTextPersonName);

        cal = (Button) findViewById(R.id.button2);
        upd = (Button) findViewById(R.id.button);
        del = (Button) findViewById(R.id.button3);
        re = (Button) findViewById(R.id.button4);
        cle = (Button) findViewById(R.id.button5);
        clo = (Button) findViewById(R.id.button6);
        in = (Button) findViewById(R.id.button7);
        room1 = (Button) findViewById(R.id.button8);
        room2 = (Button) findViewById(R.id.button9);
        room3 = (Button) findViewById(R.id.button10);
        room4 = (Button) findViewById(R.id.button11);

        details = (TextView) findViewById(R.id.textView2);
        room1.setOnClickListener(view -> details.setText("Single Room " +
                "price=20 OMR"));
        room2.setOnClickListener(view -> details.setText("Dule Room " +
                "price=30 OMR"));
        room3.setOnClickListener(view -> details.setText("Signle Room with view " +
                "price=40 OMR"));
        room4.setOnClickListener(view -> details.setText("Dule Room with view " +
                "price=50 OMR"));

        DataBase = new hoteldatabase(this);


        calcData();
        updateData();
        deleteData();
        readData();
        clearData();
        closeData();
        insertDate();
    }

    @SuppressLint("SetTextI18n")
    public void calcData() {
        cal.setOnClickListener(view -> {
            if (number.getText().toString().isEmpty() || day.getText().toString().isEmpty())
            {
                details.setText("Enter Your Information Please!!");
            } else
            {
                int num1 = Integer.parseInt(number.getText().toString());
                int num2 = Integer.parseInt(day.getText().toString());
                if (num1==1){
                    price=20;
                }
                else if (num1==2){
                     price=30;
                }
                else if (num1==3){
                    price=40;
                }
                else if (num1==4){
                    price=50;
                }
                int multiply=price*num2;
                details.setText("Room price : "+ multiply);
            }
        });
    }

    public void updateData() {
        upd.setOnClickListener(view -> {
            boolean
                    upd = DataBase.updateData(name.getText().toString(), day.getText().toString(), name.getText().toString()
                    , phone.getText().toString(), email.getText().toString());
            if (upd)
                Toast.makeText(MainActivity.this, "The Data is updated", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "The Data is not updated", Toast.LENGTH_LONG).show();
        });
    }
    public void deleteData() {
        del.setOnClickListener(view -> {
            Integer del=DataBase.deleteData(number.getText().toString());
            if(del>0)
                Toast.makeText(MainActivity.this,"The Data is deleted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"The Dats is not deleted",Toast.LENGTH_LONG).show();
        });
    }

    public void readData() {
        re.setOnClickListener(view -> {
            Cursor r=DataBase.getAllData();
            if(r.getCount()==0)
            {
                showMessage("Error", "Nothing found");
                return;
            }
            StringBuilder b=new StringBuilder();
            while(r.moveToNext())
            {
                b.append("Room Price :").append(r.getString(0)).append("\n");
                b.append("The Days :").append(r.getString(1)).append("\n");
                b.append("Username :").append(r.getString(2)).append("\n");
                b.append("Phone Number :").append(r.getString(3)).append("\n");
                b.append("Email :").append(r.getString(4)).append("\n");
            }
            showMessage("Hotel Details",b.toString());
        });
    }

    public void insertDate()
    {
        in.setOnClickListener(view -> {
            boolean
                    insertData = DataBase.insertData(number.getText().toString(), day.getText().toString(),name.getText().toString(),
                    phone.getText().toString(),email.getText().toString());
            if (insertData)
                Toast.makeText(MainActivity.this, "Information Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Information not Inserted", Toast.LENGTH_LONG).show();
        });
    }

    @SuppressLint("SetTextI18n")
    public void clearData() {
        cle.setOnClickListener(view -> {
                number.setText("");
                day.setText("");
                name.setText("");
                phone.setText("");
                email.setText("");
        });
    }


    public void closeData() {
        clo.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }
    public void showMessage(String title,String mes)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        int id=menuItem.getItemId();
        if(id==R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
