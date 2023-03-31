package cn.icarus.lottery.domain.support.ids;

/**
 * @author Icarus
 * @description 定义生成ID的策略接口
 * @date 2023/3/31 13:22
 */
public interface IIdGenerator {
    /**
     * 获取ID，有两种实现方式
     * 1.雪花算法，用于生成单号
     * 2.日期算法，用于生成活动标号类，特征时生成数字串比较短，但指定时间内不能生成太多
     * 3.随机算法，用于生成策略ID
     * 每种ID策略获得的ID结果有所不同，这也是为了适合不同类型的ID使用。避免同样的ID造成混乱
     * @return  ID
     */

    long nextId();
}
