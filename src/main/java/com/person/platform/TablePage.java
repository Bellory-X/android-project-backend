package com.person.platform;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.Objects;

@Schema(name = "Страница справочника")
public class TablePage<T> {

    public final List<T> rows;
    public final @Schema(description = "Номер страницы") @PositiveOrZero Integer pageNumber;
    public final @Schema(description = "Кол-во записей на странице") @Positive Integer pageSize;
    public final @Schema(description = "Кол-во страниц") @PositiveOrZero Integer pageCount;

    public TablePage(List<T> rows, Integer pageNumber, Integer pageSize, Integer pageCount) {
        this.rows = rows;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TablePage<?> tablePage = (TablePage<?>) o;
        return rows.equals(tablePage.rows) && pageNumber.equals(tablePage.pageNumber) && pageSize.equals(tablePage.pageSize) && pageCount.equals(tablePage.pageCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, pageNumber, pageSize, pageCount);
    }

    public List<T> rows() {
        return rows;
    }

    public Integer pageNumber() {
        return pageNumber;
    }

    public Integer pageSize() {
        return pageSize;
    }

    public Integer pageCount() {
        return pageCount;
    }
}
