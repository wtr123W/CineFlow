package com.grassroots.cineflow.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.adapter.SingleItemAdapter;
import com.grassroots.cineflow.service.BusinessResult;
import com.grassroots.cineflow.service.SingleItemService;
import com.grassroots.cineflow.sqlite.SingleItem;
import com.grassroots.cineflow.utils.CurrentUserUtils;

import java.util.ArrayList;
import java.util.List;

public class SnackFragment extends Fragment {

    private RecyclerView rvSnack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_snack, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
        initView();
        initAdapter();
    }

    private void initTitle() {
        TextView tvTitle = getView().findViewById(R.id.tv_title);
        tvTitle.setText("零食");
        TextView tvBack = getView().findViewById(R.id.tv_back);
        tvBack.setVisibility(View.GONE);
    }

    private void initView() {
        rvSnack = getView().findViewById(R.id.rv_snack);
    }

    private void initAdapter() {
        SingleItemAdapter adapter = new SingleItemAdapter();
        rvSnack.setAdapter(adapter);
        rvSnack.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setList(getSingleItemList());
        adapter.setOnItemAddClickListener(new SingleItemAdapter.OnItemAddClickListener() {
            @Override
            public void onAdd(SingleItem item) {
                item.setUserId(CurrentUserUtils.getCurrentUser().getId());
                BusinessResult<SingleItem> add = SingleItemService.add(item);
                Toast.makeText(getContext(), add.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<SingleItem> getSingleItemList() {
        SingleItem singleItem1 = new SingleItem();
        singleItem1.setType(2);
        singleItem1.setTitle("爆米花（大）");
        singleItem1.setImageId(R.mipmap.ic_snack_1);
        singleItem1.setPrice("￥20");

        SingleItem singleItem2 = new SingleItem();
        singleItem2.setType(2);
        singleItem2.setTitle("爆米花（小）");
        singleItem2.setImageId(R.mipmap.ic_snack_2);
        singleItem2.setPrice("￥15");

        SingleItem singleItem3 = new SingleItem();
        singleItem3.setType(2);
        singleItem3.setTitle("可乐（小）");
        singleItem3.setImageId(R.mipmap.ic_snack_3);
        singleItem3.setPrice("￥10");

        SingleItem singleItem4 = new SingleItem();
        singleItem4.setType(2);
        singleItem4.setTitle("可乐（中）");
        singleItem4.setImageId(R.mipmap.ic_snack_3);
        singleItem4.setPrice("￥12");

        SingleItem singleItem5 = new SingleItem();
        singleItem5.setType(2);
        singleItem5.setTitle("可乐（大）");
        singleItem5.setImageId(R.mipmap.ic_snack_3);
        singleItem5.setPrice("￥15");

        List<SingleItem> list = new ArrayList<>();
        list.add(singleItem1);
        list.add(singleItem2);
        list.add(singleItem3);
        list.add(singleItem4);
        list.add(singleItem5);
        list.add(singleItem1);
        list.add(singleItem2);
        list.add(singleItem3);
        list.add(singleItem4);
        list.add(singleItem5);
        return list;
    }
}

