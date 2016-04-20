package com.zzh.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.recycler.holder.UserViewHolder;
import com.zzh.vae.R;
import com.zzh.vae.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/4/19.
 */
public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<Users> mUsersList;

    public UserAdapter(){
        mUsersList = new ArrayList<>();
    }

    public void add(Users user){
        mUsersList.add(user);
    }
    public void add2Position(Users user, int postion){
        mUsersList.add(postion, user);
    }

    public void remove2Position(int position)
    {
        mUsersList.remove(position);
    }


    public void addAll(List<Users> list){
        mUsersList.addAll(list);
    }

    public void clear(){
        mUsersList.clear();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anim_recycler, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Users users = mUsersList.get(position);
        holder.userName.setText(users.getUserName());
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }
}
