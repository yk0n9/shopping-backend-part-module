package top.ykong.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import top.ykong.entity.User;
import top.ykong.entity.UserSexEnum;
import top.ykong.provider.UserProvider;

import java.util.Date;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into user(phone_number,password) values(#{param1},#{param2})")
    void registerUser(String username, String password);

    @Update("update user set password=#{param2} where phone_number = #{param1} or mail = #{param1} or account = #{param1}")
    int modifyUser(String account, String password);

    //根据 手机号or账号or邮箱 取出密码
    @Select("select password from user where account = #{arg0} or phone_number = #{arg0} or mail = #{arg0}")
    String selectPasswordAcc(String acc);

    //根据 手机号or账号or邮箱 取出手机号
    @Select("select phone_number from user where phone_number = #{arg0} or account = #{arg0} or mail = #{arg0}")
    String selectPhone(String acc);

    @Update("update user set password = #{arg0} where phone_number = #{arg1}")
    int updatePassword(String new_password, String phone_number);

    @UpdateProvider(type = UserProvider.class, method = "updatePro")
    int add_information(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail);

}
