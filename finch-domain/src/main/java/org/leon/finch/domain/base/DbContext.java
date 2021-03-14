package org.leon.finch.domain.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leon Song
 * @date 2021-03-14
 */
public class DbContext<T extends Aggregate<ID>, ID extends Identifier> {

    @Getter
    private Class<? extends T> aggregateClass;


    private Map<ID, T> aggregateMap = new HashMap<>();

    public DbContext(Class<? extends T> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    public void attach(T aggregate) {

    }

}
