package com.haiyu.mapper;

import com.haiyu.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Title: SeckillMapper
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 13:47
 */
@Mapper
@Repository
public interface SeckillMapper {
    /**
     * 查询所有秒杀商品的记录信息
     *
     * @return
     */
    @Select(" SELECT * FROM seckill")
    List<Seckill> findAll();

    /**
     * 根据主键查询当前秒杀商品的数据
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM seckill WHERE seckill_id = #{id}")
    Seckill findById(long id);

    /**
     * 减库存。
     * 对于Mapper映射接口方法中存在多个参数的要加@Param()注解标识字段名称，不然Mybatis不能识别出来哪个字段相互对应
     *
     * @param seckillId 秒杀商品ID
     * @param killTime  秒杀时间
     * @return 返回此SQL更新的记录数，如果>=1表示更新成功
     */
    @Update("UPDATE seckill\n" +
            "        SET stock_count = stock_count - 1\n" +
            "        WHERE seckill_id = #{seckillId}\n" +
            "        AND start_time <= #{killTime}\n" +
            "        AND end_time >= #{killTime}\n" +
            "        AND stock_count > 0")
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
