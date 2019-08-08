package com.example.travelmantics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText txttitle;
    EditText txtdescription;
    EditText txtprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Travelmantics");
        txttitle = (EditText) findViewById(R.id.txttitle);
        txtdescription = (EditText) findViewById(R.id.txtdescription);
        txtprice = (EditText) findViewById(R.id.txtprice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                SaveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void SaveDeal() {
        String title = txttitle.getText().toString();
        String description = txtdescription.getText().toString();
        String price = txtprice.getText().toString();
        TravelDeal deal = new TravelDeal(title, description, price,"");
        mDatabaseReference.push().setValue(deal);
    }

    private void clean() {
        txttitle.setText("");
        txtdescription.setText("");
        txtprice.setText("");
        txttitle.requestFocus();
    }
}
