package com.grassroots.cineflow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.activity.LoginActivity;
import com.grassroots.cineflow.activity.MovieActivity;
import com.grassroots.cineflow.activity.SnackActivity;
import com.grassroots.cineflow.utils.CurrentUserUtils;

public class UserFragment extends Fragment {

    private TextView tvUsername;

    private ImageView ivService1, ivService2, ivService3, ivService4;

    private LinearLayout llMovie, llSnack;

    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        tvUsername = getView().findViewById(R.id.tv_username);
        tvUsername.setText(String.format("用户昵称：%s", CurrentUserUtils.getCurrentUser().getUsername()));
        ivService1 = getView().findViewById(R.id.iv_service1);
        ivService2 = getView().findViewById(R.id.iv_service2);
        ivService3 = getView().findViewById(R.id.iv_service3);
        ivService4 = getView().findViewById(R.id.iv_service4);
        llMovie = getView().findViewById(R.id.ll_movie);
        llSnack = getView().findViewById(R.id.ll_snack);
        btnLogout = getView().findViewById(R.id.btn_logout);
    }

    private void initListener() {
        llMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                startActivity(intent);
            }
        });
        llSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SnackActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        ivService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        ivService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        ivService3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        ivService4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
