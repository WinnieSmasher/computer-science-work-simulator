package io.github.winniesmasher.novamart.common;

import java.util.List;

public record PageResult<T>(
        List<T> items,
        int page,
        int size,
        long total,
        boolean hasMore,
        boolean isEnd
) {
}

