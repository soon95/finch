package org.leon.finch.domain.base;

/**
 * @author Leon Song
 * @date 2021-03-14
 */
public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T, ID> {

    @Override
    public void attach(T aggregate) {

    }

    @Override
    public void attach(T aggregate, ID id) {

    }

    @Override
    public void detach(T aggregate) {

    }

    @Override
    public T find(ID id) {
        return null;
    }
}
