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

public class SnackActivity extends AppCompatActivity {

    private RecyclerView rvSnack;

    private Button btnPay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        initTitle();
        initView();
        initAdapter();
    }

    private void initTitle() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("零食");
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        rvSnack = findViewById(R.id.rv_snack);
        btnPay = findViewById(R.id.btn_pay);
    }

    private void initAdapter() {
        BusinessResult<List<SingleItem>> result = SingleItemService.getByUserIdAndType(CurrentUserUtils.getCurrentUser().getId(), 2);

        SingleItemRemoveAdapter adapter = new SingleItemRemoveAdapter();
        rvSnack.setAdapter(adapter);
        rvSnack.setLayoutManager(new LinearLayoutManager(this));
        adapter.setList(result.getData());
        adapter.setOnItemRemoveClickListener(new SingleItemRemoveAdapter.OnItemRemoveClickListener() {
            @Override
            public void onRemove(SingleItem item) {
                BusinessResult<SingleItem> delete = SingleItemService.deleteById(item.getId());
                Toast.makeText(SnackActivity.this, delete.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SnackActivity.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
