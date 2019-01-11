package com.haiyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: BaseController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:16
 */
@Controller
public class BaseController {
    /**
     * 跳转到秒杀商品页
     *
     * @return
     */
    @RequestMapping("/seckill")
    public String seckillGoods() {
        return "redirect:/seckill/list";
    }
}
