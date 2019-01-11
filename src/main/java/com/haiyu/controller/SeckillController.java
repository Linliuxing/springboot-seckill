package com.haiyu.controller;

import com.haiyu.dto.Exposer;
import com.haiyu.dto.SeckillResponse;
import com.haiyu.dto.SeckillResult;
import com.haiyu.entity.Seckill;
import com.haiyu.enums.SeckillStatEnum;
import com.haiyu.execution.RepeatKillException;
import com.haiyu.execution.SeckillCloseException;
import com.haiyu.execution.SeckillException;
import com.haiyu.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Title: SeckillController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:19
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String findSeckillList(Model model) {
        List<Seckill> list = seckillService.findAll();
        model.addAttribute("list", list);
        return "page/seckill";
    }

    @ResponseBody
    @GetMapping("/findById")
    public Seckill findById(@RequestParam("id") Long id) {
        return seckillService.findById(id);
    }


    @GetMapping("/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "page/seckill";
        }
        Seckill seckill = seckillService.findById(seckillId);
        model.addAttribute("seckill", seckill);
        if (seckill == null) {
            return "page/seckill";
        }
        return "page/seckill_detail";
    }


    @ResponseBody
    @PostMapping("/{seckillId}/exposer")
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }


    @PostMapping("/{seckillId}/{md5}/execution")
    @ResponseBody
    public SeckillResult<SeckillResponse> execute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @RequestParam("money") BigDecimal money,
                                                  @CookieValue(value = "killPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillResult<SeckillResponse>(false, "未注册");
        }
        try {
            SeckillResponse execution = seckillService.executeSeckill(seckillId, money, userPhone, md5);
            return new SeckillResult<SeckillResponse>(true, execution);
        } catch (RepeatKillException e) {
            SeckillResponse seckillExecution = new SeckillResponse(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillResponse>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillResponse seckillExecution = new SeckillResponse(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillResponse>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillResponse seckillExecution = new SeckillResponse(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillResponse>(true, seckillExecution);
        }
    }

    @ResponseBody
    @GetMapping("/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
