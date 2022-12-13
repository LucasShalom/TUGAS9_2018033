package com.example.pertemuan4_actionbar;

import static com.example.pertemuan4_actionbar.DBmain.TABLENAME;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan4_actionbar.databinding.ActivityDisplayDataBinding;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    com.example.pertemuan4_actionbar.DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    com.example.pertemuan4_actionbar.MyAdapter myAdapter;
    private ActivityDisplayDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findId();
        dBmain = new com.example.pertemuan4_actionbar.DBmain(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DisplayData.this,
                        MainActivity.class);
                startActivity(a);
            }
        });
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME,null);
        ArrayList < Model > models = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] avatar = cursor.getBlob(4);
            String star = cursor.getString(2);
            String price = cursor.getString(3);
            models.add(new Model(id, avatar, name, star, price));
        }
        cursor.close();
        myAdapter = new com.example.pertemuan4_actionbar.MyAdapter(this, R.layout.singledata, models, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.rv);
    }
}