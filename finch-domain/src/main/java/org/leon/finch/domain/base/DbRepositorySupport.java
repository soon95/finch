package org.leon.finch.domain.base;

import lombok.Getter;

/**
 * @author Leon Song
 * @date 2021-03-14
 */
public abstract class DbRepositorySupport<T extends Aggregate<ID>, ID extends Identifier> implements Repository<T, ID> {

    @Getter
    private final Class<T> targetClass;


    protected DbRepositorySupport(Class<T> targetClass) {
        this.targetClass = targetClass;
    }
}
