package top.ykong.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ykong.entity.Goods;
import top.ykong.entity.GoodsHeatEnum;
import top.ykong.entity.GoodsStateEnum;
import top.ykong.util.PageEx;

import java.util.Map;

public interface GoodsService {

    PageEx<Goods> fuzzyQuery(int page, int limit, String gname);

    PageEx<Goods> showPage(int page, int limit);

    Page<Goods> showAll(int page, int limit);

    Page<Goods> showOnLine(int page, int limit, GoodsStateEnum state, GoodsHeatEnum heat);

    Map<String, Object> insertGood(Integer gid, String gname, GoodsStateEnum state, String type);

    Map<String, Object> deleteGood(Integer gid, String gname);

    Map<String, Object> updateGoodName(Integer gid, String gname, String new_gname);

    Map<String, Object> updateGoodHeat(Integer gid, String gname, GoodsHeatEnum heat);

    Map<String, Object> updateGoodState(Integer gid, String gname, GoodsStateEnum state);
}
