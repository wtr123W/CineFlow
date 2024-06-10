package com.grassroots.cineflow.service;

import com.grassroots.cineflow.sqlite.SingleItem;
import com.grassroots.cineflow.sqlite.SingleItemDatabaseHelper;

import java.util.List;

public class SingleItemService {

    /**
     * 添加
     */
    public static BusinessResult<SingleItem> add(SingleItem item) {
        if (item.getUserId() == null) {
            return BusinessResult.fail("用户id不能为空");
        }
        SingleItemDatabaseHelper.insert(item);
        return BusinessResult.success("添加成功，快去我的页面查看吧", item);
    }

    /**
     * 删除
     */
    public static BusinessResult<SingleItem> deleteById(Integer id) {
        if (id == null) {
            return BusinessResult.fail("id不能为空");
        }
        SingleItemDatabaseHelper.deleteById(id);
        return BusinessResult.success(null);
    }

    /**
     * 根据用户id和类型查询
     */
    public static BusinessResult<List<SingleItem>> getByUserIdAndType(Integer userId, Integer type) {
        if (userId == null) {
            return BusinessResult.fail("用户id不能为空");
        }
        if (type == null) {
            return BusinessResult.fail("类型不能为空");
        }
        List<SingleItem> list = SingleItemDatabaseHelper.getSingleItemsByUserId(userId, type);
        return BusinessResult.success(list);
    }
}
