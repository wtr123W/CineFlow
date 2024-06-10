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

public class HotFragment extends Fragment {

    private RecyclerView rvHot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot, container, false);
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
        tvTitle.setText("去看电影");
        TextView tvBack = getView().findViewById(R.id.tv_back);
        tvBack.setVisibility(View.GONE);
    }

    private void initView() {
        rvHot = getView().findViewById(R.id.rv_hot);
    }

    private void initAdapter() {
        SingleItemAdapter adapter = new SingleItemAdapter();
        rvHot.setAdapter(adapter);
        rvHot.setLayoutManager(new LinearLayoutManager(getContext()));
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
        singleItem1.setType(1);
        singleItem1.setTitle("涉过愤怒的海");
        singleItem1.setImageId(R.mipmap.ic_hot_1);
        singleItem1.setScore("评分：7.7");
        singleItem1.setContent1("编剧: 曹保平 / 武皮皮 / 焦华静");
        singleItem1.setContent2("主演: 黄渤 / 周迅 / 祖峰 / 张宥浩 / 周依然");
        singleItem1.setPrice("￥30起");

        SingleItem singleItem2 = new SingleItem();
        singleItem2.setType(1);
        singleItem2.setTitle("我本是高山");
        singleItem2.setImageId(R.mipmap.ic_hot_2);
        singleItem2.setScore("暂无评分");
        singleItem2.setContent1("编剧: 袁媛 / 陈麒凌");
        singleItem2.setContent2("主演: 海清 / 陈永胜 / 柴烨 / 王玥婷 / 万国鹏");
        singleItem2.setPrice("￥35起");

        SingleItem singleItem3 = new SingleItem();
        singleItem3.setType(1);
        singleItem3.setTitle("刀尖");
        singleItem3.setImageId(R.mipmap.ic_hot_3);
        singleItem3.setScore("评分：6.2");
        singleItem3.setContent1("编剧: 乌玛 / 某小丫");
        singleItem3.setContent2("主演: 张译 / 黄志忠 / 郎月婷 / 成泰燊 / 沙溢");
        singleItem3.setPrice("￥25起");

        SingleItem singleItem4 = new SingleItem();
        singleItem4.setType(1);
        singleItem4.setTitle("蜡笔小新：新次元！");
        singleItem4.setImageId(R.mipmap.ic_hot_4);
        singleItem4.setScore("评分：8.1");
        singleItem4.setContent1("编剧: 大根仁");
        singleItem4.setContent2("主演: 小林由美子 / 楢桥美纪 / 森川智之 / 兴梠里美 / 真柴摩利");
        singleItem4.setPrice("￥31起");

        SingleItem singleItem5 = new SingleItem();
        singleItem5.setType(1);
        singleItem5.setTitle("星愿 Wish");
        singleItem5.setImageId(R.mipmap.ic_hot_5);
        singleItem5.setScore("评分：6.7");
        singleItem5.setContent1("编剧: 珍妮弗·李 / 艾莉森·摩尔 / 克里斯·巴克 / 弗恩·维拉桑托恩 / 卡洛斯·洛佩斯·埃斯特拉达 / 安德鲁·罗斯柴尔德");
        singleItem5.setContent2("主演: 阿丽亚娜·德博斯 / 克里斯·派恩 / 艾伦·图代克 / 安吉丽卡·布拉尔 / 维克多·加博");
        singleItem5.setPrice("￥27起");

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
