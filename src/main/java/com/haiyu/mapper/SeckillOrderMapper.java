package com.haiyu.mapper;

import com.haiyu.entity.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @Title: SeckillOrderMapper
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:08
 */
@Mapper
@Repository
public interface SeckillOrderMapper {
    /**
     * 插入购买订单明细
     *
     * insert ignore表示，如果中已经存在相同的记录，则忽略当前新数据；
     *
     * @param seckillId 秒杀到的商品ID
     * @param money     秒杀的金额
     * @param userPhone 秒杀的用户
     * @return 返回该SQL更新的记录数，如果>=1则更新成功
     *
     */
    @Insert("INSERT ignore INTO seckill_order(seckill_id, money, user_phone)\n" +
            "        VALUES (#{seckillId}, #{money}, #{userPhone})")
    int insertOrder(@Param("seckillId") long seckillId, @Param("money") BigDecimal money, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品ID查询订单明细数据并得到对应秒杀商品的数据，因为我们再SeckillOrder中已经定义了一个Seckill的属性
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    @Select("SELECT\n" +
            "          so.seckill_id,\n" +
            "          so.user_phone,\n" +
            "          so.money,\n" +
            "          so.create_time,\n" +
            "          so.state,\n" +
            "          s.seckill_id \"seckill.seckill_id\",\n" +
            "          s.title \"seckill.title\",\n" +
            "          s.cost_price \"seckill.cost_price\",\n" +
            "          s.create_time \"seckill.create_time\",\n" +
            "          s.start_time \"seckill.start_time\",\n" +
            "          s.end_time \"seckill.end_time\",\n" +
            "          s.stock_count \"seckill.stock_count\"\n" +
            "        FROM seckill_order so\n" +
            "        INNER JOIN seckill s ON so.seckill_id = s.seckill_id\n" +
            "        WHERE so.seckill_id = #{seckillId} AND so.user_phone = #{userPhone}")
    SeckillOrder findById(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
