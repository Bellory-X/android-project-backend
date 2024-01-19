package com.person.user.api.response;

import com.person.platform.TablePage;

import java.util.List;

public class UserGuidePage extends TablePage<UserGuideRow> {

    public UserGuidePage(
            List<UserGuideRow> rows,
            Integer pageNumber,
            Integer pageSize,
            Integer pageCount
    ) {
        super(rows, pageNumber, pageSize, pageCount);
    }
}
