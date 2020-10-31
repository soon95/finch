package org.leon.finch.common.result;

import java.util.Locale;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
public enum SortDirection {
    /**
     * 升序
     */
    ASC,
    /**
     * 降序
     */
    DESC;

    public boolean isAscending() {
        return this.equals(ASC);
    }

    public boolean isDescending() {
        return this.equals(DESC);
    }

    public static SortDirection getDirection(String value) {
        return SortDirection.valueOf(value.toUpperCase(Locale.US));
    }

}
