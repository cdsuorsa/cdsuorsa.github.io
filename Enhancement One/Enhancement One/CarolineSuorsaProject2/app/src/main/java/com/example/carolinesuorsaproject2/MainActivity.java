package com.example.carolinesuorsaproject2;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private EventDatabase db;
    private boolean smsEnabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        smsEnabled = getIntent().getBooleanExtra("smsEnabled", false);
        db = new EventDatabase(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new RecyclerViewAdapter(db.getAllEvents(), db, smsEnabled);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.buttonAdd).setOnClickListener(v ->
                startActivity(new Intent(this, AddEventActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateData(db.getAllEvents());
    }
}