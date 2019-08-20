package com.n.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private DBManager dbManager;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbManager = new DBManager(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        addRecord();
    }

    @Override
    protected void onDestroy() {
        dbManager.closeDB();
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            queryRecords();
        }
    }

    private void queryRecords() {
        List<MarriageRecord> records = dbManager.query();

        StringBuilder sb = new StringBuilder();
        sb.append(records.size());
        sb.append(" Records");
        if (!records.isEmpty()) {
            sb.append(":\n");
        }
        for (MarriageRecord rec : records) {
            sb.append("{\n[" + rec._id + "]\n" + rec.husbandName + "\n" + rec.husbandProfession + "\n" + rec.temperType + "\n" + rec.husbandRole + "\n}");
            sb.append("\n");
        }

        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }

    private void addRecord() {
        try {
            MarriageRecord record = new MarriageRecord();
            record.husbandName = "Yegon";
            record.husbandProfession = "Surveyor";
            record.temperType = "Ballistic";
            record.husbandRole = "Husband";
            dbManager.add(record);
            Toast.makeText(this, "Added a record", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Report",e.getMessage());
        }
    }
}