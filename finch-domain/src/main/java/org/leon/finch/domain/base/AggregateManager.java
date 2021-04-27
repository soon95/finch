package org.leon.finch.domain.base;


/**
 * @author Leon Song
 * @date 2021-03-14
 */
public interface AggregateManager<T extends Aggregate<ID>, ID extends Identifier> {

    /**
     * 将一个aggregate附属到一个repository，让它变得可追踪
     *
     * @param aggregate 聚合根
     */
    void attach(T aggregate);

    void attach(T aggregate, ID id);

    /**
     * 解除一个aggregate的追踪
     *
     * @param aggregate 聚合根
     */
    void detach(T aggregate);

    T find(ID id);


}
