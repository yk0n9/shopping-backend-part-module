package top.ykong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.ykong.entity.Goods;
import top.ykong.entity.GoodsHeatEnum;
import top.ykong.entity.GoodsStateEnum;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Update("update goods set state = '已下架', heat = '非热销' where gid = #{arg0} and gname = #{arg1}")
    void downGoodState(Integer gid, String gname);

    @Insert("insert into goods(gid, gname, state, type, heat) values(#{arg0}, #{arg1}, #{arg2}, #{arg3}, '非热销')")
    void insertGood(Integer gid, String gname, GoodsStateEnum state, String type);

    @Select("select state from goods where gid = #{arg0} and gname = #{arg1}")
    String selectGoodState(Integer gid, String gname);

    @Update("update goods set state = '在售' where gid = #{arg0} and gname = #{arg1}")
    void enableGoodState(Integer gid, String gname);

    @Update("update goods set gname = #{arg2} where gid = #{arg0} and gname = #{arg1}")
    void updateGoodName(Integer gid, String gname, String new_gname);

    @Update("update goods set heat = #{arg2} where gid = #{arg0} and gname = #{arg1}")
    void updateGoodHeat(Integer gid, String gname, GoodsHeatEnum heat);

    @Update("update goods set state = #{arg2} where gid = #{arg0} and gname = #{arg1}")
    void updateGoodState(Integer gid, String gname, GoodsStateEnum state);

    @Select("select state from good_classify where id = #{arg0} and name = #{arg1}")
    String selectGoodClassifyState(Integer gid, String type);

    @Update("update good_classify set state = '使用中' where id = #{arg0} and name = #{arg1}")
    void enableGoodClassifyState(Integer gid, String type);
}
