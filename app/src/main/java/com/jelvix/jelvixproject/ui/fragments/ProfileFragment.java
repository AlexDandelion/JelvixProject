package com.jelvix.jelvixproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jelvix.jelvixproject.R;
import com.jelvix.jelvixproject.mvp.models.User;
import com.jelvix.jelvixproject.ui.listeners.NavigateListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jelvix.jelvixproject.utils.Constants.USER_BUNDLE_KEY;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_avatar)
    ImageView avatar;

    @BindView(R.id.profile_first_name)
    TextView firstName;

    @BindView(R.id.profile_last_name)
    TextView lastName;

    private NavigateListener navigateListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            navigateListener = (NavigateListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            User user = ((User) bundle.getParcelable(USER_BUNDLE_KEY));
            if (user != null) {
                Glide.with(this)
                        .load(user.getAvatar())
                        .apply(RequestOptions.circleCropTransform())
                        .into(avatar);
                firstName.setText(user.getFirstName());
                lastName.setText(user.getLastName());
            }
        } else {
            Toast.makeText(getContext(),
                    getResources().getString(R.string.err_display_user), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @OnClick(R.id.profile_back_btn)
    void backToUsers() {
        navigateListener.navigateToUsers();
    }
}
