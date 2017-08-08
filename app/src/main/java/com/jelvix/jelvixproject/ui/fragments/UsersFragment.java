package com.jelvix.jelvixproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jelvix.jelvixproject.R;
import com.jelvix.jelvixproject.adapters.UserAdapter;
import com.jelvix.jelvixproject.mvp.models.User;
import com.jelvix.jelvixproject.mvp.presenters.UsersPresenter;
import com.jelvix.jelvixproject.mvp.views.UsersView;
import com.jelvix.jelvixproject.ui.listeners.NavigateListener;
import com.jelvix.jelvixproject.ui.listeners.OnUserSelectedListener;
import com.jelvix.jelvixproject.ui.listeners.ProgressListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersFragment extends MvpAppCompatFragment implements UsersView, OnUserSelectedListener {

    @InjectPresenter
    UsersPresenter usersPresenter;

    private List<User> users;
    private UserAdapter adapter;
    private ProgressListener progressListener;
    private NavigateListener navigateListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            progressListener = (ProgressListener) getActivity();
            navigateListener = (NavigateListener) getActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersPresenter.getUsers();
        users = new ArrayList<>();
        adapter = new UserAdapter(getContext(), users, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.users_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.users_back_btn)
    void backToLogin() {
        navigateListener.navigateToLogin();
    }

    @Override
    public void onStartRequest() {
        progressListener.showProgress();
    }

    @Override
    public void onFinishRequest() {
        progressListener.hideProgress();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUsers(List<User> users) {
        this.users.addAll(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUser(User user) {
        navigateListener.navigateToProfile(user);
    }

    @Override
    public void onUserSelected(long id) {
        usersPresenter.getUser(id);
    }
}
