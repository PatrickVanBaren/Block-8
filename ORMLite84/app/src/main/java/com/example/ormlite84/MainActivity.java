package com.example.ormlite84;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button insertButton, deleteButton;
    private final DatabaseHelper mHelper = DatabaseHelper.getInstance();
    private final String[] mUsersName = new String[]{
            "Steve",
            "Max",
            "Ann",
            "Patrick"
    };

    private UserListAdapter mAdapter;
    private RecyclerView mUserList;

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

        mUserList = findViewById(R.id.view_user_list);
        mAdapter = new UserListAdapter(this);
        mUserList.setAdapter(mAdapter);
        mUserList.setLayoutManager(new LinearLayoutManager(this));

        new SelectTask().execute();
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                final Dao<Users, Long> dao = mHelper.getDao(Users.class);
                dao.deleteBuilder().delete();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            try {
                final Dao<Users, Long> dao = mHelper.getDao(Users.class);
                for (final String userName : mUsersName) {
                    Users user = new Users();
                    user.setFirsName(userName);
                    dao.create(user);
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class SelectTask extends AsyncTask<Void, Void, List<Users>> {

        @Override
        protected List<Users> doInBackground(Void... voids) {
            try {
                final Dao<Users, Long> dao = mHelper.getDao(Users.class);
                return dao.queryForAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            mAdapter.setData(users);
        }
    }
}
