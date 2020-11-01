package org.leon.finch.common.result;

import org.leon.finch.common.util.StringUtil;

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
        try {
            return SortDirection.valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            throw new IllegalArgumentException(StringUtil.format(
                    "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
        }

    }

}
