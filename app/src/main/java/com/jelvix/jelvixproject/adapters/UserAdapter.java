package com.jelvix.jelvixproject.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jelvix.jelvixproject.R;
import com.jelvix.jelvixproject.mvp.models.User;
import com.jelvix.jelvixproject.ui.listeners.OnUserSelectedListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final Context context;
    private final List<User> users;
    private final OnUserSelectedListener listener;

    public UserAdapter(Context context, List<User> users, OnUserSelectedListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new UserViewHolder(
                        LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Glide.with(context)
                .load(users.get(position).getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatar);
        holder.firstName.setText(users.get(position).getFirstName());
        holder.lastName.setText(users.get(position).getLastName());
        holder.id = users.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private long id;
        private final CardView cardView;
        private final ImageView avatar;
        private final TextView firstName;
        private final TextView lastName;

        UserViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.user_card_view);
            cardView.setOnLongClickListener(this);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            lastName = (TextView) itemView.findViewById(R.id.last_name);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onUserSelected(id);
            return true;
        }
    }
}
