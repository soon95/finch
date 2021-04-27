package org.leon.finch.domain.base;


/**
 * Repository模式通用接口
 *
 * @author Leon Song
 * @date 2021-03-14
 */
public interface Repository<T extends Aggregate<ID>, ID extends Identifier> {

    /**
     * 将一个aggregate附属到一个repository，让它变得可追踪
     *
     * @param aggregate 聚合根
     */
    void attach(T aggregate);

    /**
     * 解除一个aggregate的追踪
     *
     * @param aggregate 聚合根
     */
    void detach(T aggregate);

    /**
     * 通过ID找到aggregate
     *
     * @param id ID
     * @return 聚合根
     */
    T find(ID id);

    /**
     * 将一个aggregate从repository中移除
     * 操作后aggregate对象将自动取消追踪
     *
     * @param aggregate 聚合根
     */
    void remove(T aggregate);

    /**
     * 保存一个aggregate
     *
     * @param aggregate 聚合根
     */
    void save(T aggregate);


    // 具体repository根据业务特征添加其他方法
}
