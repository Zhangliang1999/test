package com.jy.common.jc;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.redis.RedisPoll;
import redis.clients.jedis.Jedis;

import java.util.Date;

import static org.nutz.dao.util.Pojos.log;

/**
 * Created by susen-pc on 2016/10/26.
 */
public class JcConst {
//	public static int  PAINTED_LuckyNum  = 10000000;
	public static String PAINTED_AX="JCPNO";
	public static String INIT_PASSWORD ="123456";

    public static String getMathTypeName(int type) {
        String[] matchType = {"足球", "篮球", "网球", "棒球", "斯洛克"};
        if (type >= 1 && type <= 5) {
            return matchType[type - 1];
        }
        return type + "";
    }

    /**
     * 账户流水来源
     * 1:充值,2:提现,3:竞猜支出,4:竞猜收入,5:赛前单收入,6:赛前单支出,7:滚球单收入,8:滚球单支出,
     * 9:每日彩蛋支出,10:新用户注册收入,11:分享收入,12:被分享收入,13:兑换支出,14:系统指定收入,15:系统指定支出
     *
     * @param type
     * @return
     */
    public static String getSourceName(int type) {
        // 来源（1充值，2赢球，3分析费，4购买分析费，5提现）
        String[] source = {"", "充值", "提现",
                "竞猜支出", "竞猜收入",
                "赛前单收入", "赛前单支出",
                "滚球单收入", "滚球单支出",
                "每日彩蛋支出",
                "新用户注册收入", "分享收入", "被分享收入",
                "兑换支出",
                "系统指定收入", "系统指定支出"};
       /* for(int i=0;i<source.length;i++) {
            System.out.print(i + ":" + source[i]+",");
        }*/
        if (type >= 1 && type <= source.length) {
            return source[type];
        }
        return type + "";
    }

    /** 幸运号 */
    public final static String LUCKYNUM_KEY = "LUCKYNUM_";
    /**
     * 幸运号开始号码
     */
    public static int LUCKYNUM_START = 10000000;
    /**
     * 获取幸运号
     *
     * @param key
     * @return
     */
    public static Integer[] getLuckynum(String key, Integer number) throws Exception {
        Jedis jedis = RedisPoll.getInstance().getResource();
        try {
            jedis.setnx(key, JcConst.LUCKYNUM_START + ""); //如果不存在就设置初始值
            Integer luckynumend = Integer.valueOf(jedis.incrBy(key, number)+"");
            Integer luckynumstart = luckynumend - number + 1;
            System.out.println(luckynumstart + ", " + luckynumend);
            return new Integer[]{luckynumstart, luckynumend};
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            if (jedis != null) {
                //jedis.unwatch();
                jedis.close();
            }
        }
    }


    /**
     * 构建排序ID
     * @param date
     * @return
     */
    public static String buildOrderId(Date date) {
        int r = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
        String orderId = DateUtils.formatDate(date, "yyyyMMddHHmmss") + r;
        return orderId;
    }

    public static void main(String[] args) throws Exception {
        String date = DateUtils.getDate(DateUtils.parsePatterns[0]);
        getLuckynum(LUCKYNUM_KEY+date, 2);
    }
}
