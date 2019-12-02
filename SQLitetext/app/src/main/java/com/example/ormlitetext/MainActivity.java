package com.example.ormlitetext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sqlitetext.R;

public class MainActivity extends AppCompatActivity {

    Button insertButton, deleteButton;
    private final SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
    private final String[] usersName = new String[]{
      "Steve",
      "Max",
      "Ann",
      "Patrick"
    };

    private UserListAdapter adapter;
    private RecyclerView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertButton = findViewById(R.id.insert_button);
        deleteButton = findViewById(R.id.delete_button);

        View.OnClickListener insertButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertTask().execute();
            }
        };
        View.OnClickListener deleteButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteTask().execute();
            }
        };
        insertButton.setOnClickListener(insertButtonClick);
        deleteButton.setOnClickListener(deleteButtonClick);

        userList = findViewById(R.id.view_user_list);
        adapter = new UserListAdapter(this);
        userList.setAdapter(adapter);
        userList.setLayoutManager(new LinearLayoutManager(this));

        new SelectTask().execute();
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db.delete(ContractUser.TABLE_NAME, null, null);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class InsertTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (String userName : usersName) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ContractUser.COLUMN_NAME_FIRSTNAME, userName);
                db.insert(ContractUser.TABLE_NAME, null, contentValues);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class SelectTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor cursor = db.query(ContractUser.TABLE_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            adapter.setCursor(cursor);
        }
    }
}
