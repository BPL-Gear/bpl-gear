package org.bpl.gear.te.data.mapper;

import org.apache.ibatis.annotations.*;
import org.bpl.gear.te.data.bean.Te;

import java.util.List;

/**
 * The interface Te operate.
 *
 * @description
 * @create 2016 -11-15 下午3:50
 * @email gxz04220427 @163.com
 */
@Mapper
public interface TeOperate {
    /**
     * Select all te list.
     *
     * @return the list
     */
    @Select("select * from te")
    List<Te> selectAllTe();

    /**
     * Select te by id te.
     *
     * @param id the id
     *
     * @return the te
     */
    @Select("select * from te where id = #{id}")
    Te selectTebyid(int id);

    /**
     * Insert te.
     *
     * @param te the te
     */
    @Insert("insert into te (file_l, file_r, fi_l, fia_l, fp_l, fpt_l, eccentric_l, fi_r, fia_r, fp_r, fpt_r, eccentric_r, num, drivingid, drivedid, loada, rpm) values (#{file_l},#{file_r},#{fi_l},#{fia_l},#{fp_l},#{fpt_l},#{eccentric_l},#{fi_r},#{fia_r},#{fp_r},#{fpt_r},#{eccentric_r},#{num},#{drivingid},#{drivedid},#{loada},#{rpm})")
    void insertTe(Te te);

    @Update("update te set drivingid=#{drivingid},drivedid=#{drivedid},rpm=#{rpm} where num=#{num}")
    void updateTeInfo(@Param("num") String num, @Param("drivingid") int drivingid, @Param("drivedid") int drivedid, @Param("rpm") double rpm);
}
