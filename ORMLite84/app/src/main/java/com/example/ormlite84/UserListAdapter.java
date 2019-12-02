package com.example.ormlite84;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<Users> mData;

    public UserListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Users> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userNameTextView.setText(mData.get(position).getFirsName());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView userNameTextView;

        public ViewHolder (View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}

