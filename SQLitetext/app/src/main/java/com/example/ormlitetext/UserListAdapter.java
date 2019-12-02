package com.example.ormlitetext;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private Cursor cursor;

    public UserListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setCursor(Cursor cursor) {
        final Cursor oldCursor = cursor;
        this.cursor = cursor;
        notifyDataSetChanged();
        if (oldCursor != null) {
            oldCursor.close();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.userNameTextView.setText(cursor.getString(cursor.getColumnIndex(ContractUser.COLUMN_NAME_FIRSTNAME)));
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView userNameTextView;

        public ViewHolder (View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
