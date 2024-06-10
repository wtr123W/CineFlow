package com.grassroots.cineflow.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.adapter.SingleItemRemoveAdapter;
import com.grassroots.cineflow.service.BusinessResult;
import com.grassroots.cineflow.service.SingleItemService;
import com.grassroots.cineflow.sqlite.SingleItem;
import com.grassroots.cineflow.utils.CurrentUserUtils;

import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private RecyclerView rvMovie;

    private Button btnPay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initTitle();
        initView();
        initAdapter();
    }

    private void initTitle() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("电影票");
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        rvMovie = findViewById(R.id.rv_movie);
        btnPay = findViewById(R.id.btn_pay);
    }

    private void initAdapter() {
        BusinessResult<List<SingleItem>> result = SingleItemService.getByUserIdAndType(CurrentUserUtils.getCurrentUser().getId(), 1);

        SingleItemRemoveAdapter adapter = new SingleItemRemoveAdapter();
        rvMovie.setAdapter(adapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        adapter.setList(result.getData());
        adapter.setOnItemRemoveClickListener(new SingleItemRemoveAdapter.OnItemRemoveClickListener() {
            @Override
            public void onRemove(SingleItem item) {
                BusinessResult<SingleItem> delete = SingleItemService.deleteById(item.getId());
                Toast.makeText(MovieActivity.this, delete.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MovieActivity.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
