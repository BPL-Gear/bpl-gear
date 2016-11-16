package org.bpl.gear.te.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.bpl.gear.te.data.bean.Gear;


import java.util.List;

/**
 * @description
 * @create 2016-11-15 下午3:42
 * @email gxz04220427@163.com
 */
@Mapper
public interface GearOperate {
    @Select("select * from gear")
    List<Gear> selectAllGears();

    @Select("select * from gear where id = #{id}")
    Gear selectGearbyId(int id);

    @Select("select * from gear where name = #{name}")
    Gear selectGearbyName(String name);
}
