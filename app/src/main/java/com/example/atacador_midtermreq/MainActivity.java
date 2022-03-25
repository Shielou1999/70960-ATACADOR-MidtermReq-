package com.example.atacador_midtermreq;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        EditText id, name, addons, price, qty;
        Button create, retrieve, update, delete;
        DatabaseHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                id = findViewById(R.id.id);
                name = findViewById(R.id.name);
                addons = findViewById(R.id.addons);
                price = findViewById(R.id.price);
                qty = findViewById(R.id.qty);

                create = findViewById(R.id.createbtn);
                retrieve = findViewById(R.id.retrievebtn);
                update = findViewById(R.id.updatebtn);
                delete = findViewById(R.id.deletebtn);

                DB = new DatabaseHelper(this);

                create.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String idTXT = id.getText().toString();
                                String nameTXT = name.getText().toString();
                                String addTXT = addons.getText().toString();
                                String priceTXT = price.getText().toString();
                                String qtyTXT = qty.getText().toString();

                                if (idTXT.isEmpty()) {
                                        id.setError("Kindly put the code!");
                                        id.requestFocus();
                                        return;
                                }
                                if (nameTXT.isEmpty()) {
                                        name.setError("Flavor is required!");
                                        name.requestFocus();
                                        return;
                                }
                                if (addTXT.isEmpty()) {
                                        addons.setError("Add-ons required!");
                                        addons.requestFocus();
                                        return;
                                }
                                if (priceTXT.isEmpty()) {
                                        price.setError("Kindly put the price!");
                                        price.requestFocus();
                                        return;
                                }
                                if (qtyTXT.isEmpty()) {
                                        qty.setError("Quantity is required!");
                                        qty.requestFocus();
                                        return;
                                }

                                if (idTXT.isEmpty() || nameTXT.isEmpty() || addTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                                        Toast.makeText(MainActivity.this, "Please fill up required fields!", Toast.LENGTH_SHORT).show();
                                } else {
                                        Boolean checkinsertdata = DB.insertuserdata(idTXT, nameTXT, addTXT, priceTXT, qtyTXT);
                                        if (checkinsertdata == true) {
                                                Toast.makeText(MainActivity.this, "Order Inserted", Toast.LENGTH_SHORT).show();
                                                id.getText().clear();
                                                name.getText().clear();
                                                addons.getText().clear();
                                                price.getText().clear();
                                                qty.getText().clear();
                                        } else
                                                Toast.makeText(MainActivity.this, "No order yet. Product ID Exists!", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String idTXT = id.getText().toString();
                                String nameTXT = name.getText().toString();
                                String addTXT = addons.getText().toString();
                                String priceTXT = price.getText().toString();
                                String qtyTXT = qty.getText().toString();

                                if (idTXT.isEmpty()) {
                                        id.setError("Kindly put the code!");
                                        id.requestFocus();
                                        return;
                                }
                                if (nameTXT.isEmpty()) {
                                        name.setError("Flavor is required!");
                                        name.requestFocus();
                                        return;
                                }
                                if (addTXT.isEmpty()) {
                                        addons.setError("Add-ons required!");
                                        addons.requestFocus();
                                        return;
                                }
                                if (priceTXT.isEmpty()) {
                                        price.setError("Kindly put the price!");
                                        price.requestFocus();
                                        return;
                                }
                                if (qtyTXT.isEmpty()) {
                                        qty.setError("Quantity is required!");
                                        qty.requestFocus();
                                        return;
                                }

                                if (idTXT.isEmpty() || nameTXT.isEmpty() || addTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                                        Toast.makeText(MainActivity.this, "Please fill up required fields!", Toast.LENGTH_SHORT).show();
                                } else {
                                        Boolean checkupdatedata = DB.updateuserdata(idTXT, nameTXT, addTXT, priceTXT, qtyTXT);
                                        if (checkupdatedata == true) {
                                                Toast.makeText(MainActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                                                id.getText().clear();
                                                name.getText().clear();
                                                addons.getText().clear();
                                                price.getText().clear();
                                                qty.getText().clear();
                                        } else
                                                Toast.makeText(MainActivity.this, "Order Not Yet Updated.Sip Code does not Exists!", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String idTXT = id.getText().toString();
                                Boolean checkudeletedata = DB.deletedata(idTXT);

                                if (idTXT.isEmpty()) {
                                        id.setError("Kindly put the code!");
                                        id.requestFocus();
                                        return;
                                }

                                if (checkudeletedata == true) {
                                        Toast.makeText(MainActivity.this, "Order Deleted", Toast.LENGTH_SHORT).show();
                                        id.getText().clear();
                                } else
                                        Toast.makeText(MainActivity.this, "Order not  yet Deleted.Sip code does not Exists!", Toast.LENGTH_SHORT).show();
                        }
                });

                retrieve.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Cursor res = DB.getdata();
                                if (res.getCount() == 0) {
                                        Toast.makeText(MainActivity.this, "No Order/s Exists", Toast.LENGTH_SHORT).show();
                                        return;
                                }
                                StringBuffer buffer = new StringBuffer();
                                while (res.moveToNext()) {
                                        buffer.append("Sip code: " + res.getString(0) + "\n");
                                        buffer.append("Flavor: " + res.getString(1) + "\n");
                                        buffer.append("Addons: " + res.getString(2) + "\n");
                                        buffer.append("Price: " + res.getString(3) + "\n");
                                        buffer.append("Quantity: " + res.getString(4) + "\n\n");
                                }

                                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setCancelable(true);
                                builder.setTitle("Order Details");
                                builder.setMessage(buffer.toString());
                                builder.show();
                        }
                });
        }
}