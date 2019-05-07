package ua.kostenko.carinfo.rest.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kostenko.carinfo.common.api.ParamsHolderBuilder;
import ua.kostenko.carinfo.common.api.services.DBService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;

@Slf4j
abstract class CommonSearchService<T> implements SearchService<T> {
    private final DBService<T> service;

    CommonSearchService(DBService<T> service) {
        this.service = service;
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        return service.getAll(getBuilder(pageable));
    }

    @Override
    public Page<T> findForField(@Nonnull @NonNull String field, Pageable pageable) {
        String findForFieldParam = getFindForFieldParam();
        ParamsHolderBuilder builder = getBuilder(pageable);
        builder.param(findForFieldParam, field);
        return service.getAll(builder);
    }

    abstract String getFindForFieldParam();

    @Override
    public Page<T> findByParams(@Nonnull @NonNull Map<String, Object> params, Pageable pageable) {
        ParamsHolderBuilder builder = getBuilder(pageable);
        addParamsToBuilder(params, builder);
        return service.getAll(builder);
    }

    void addParamsToBuilder(@NonNull @Nonnull Map<String, Object> params, @NonNull @Nonnull ParamsHolderBuilder builder){
        params.forEach((key, value) -> {
            if (value instanceof String) {
                builder.param(key, (String) value);
            } else if (value instanceof Long) {
                builder.param(key, (Long) value);
            } else if (value instanceof Integer) {
                builder.param(key, (Integer) value);
            } else if (value instanceof Date) {
                builder.param(key, (Date) value);
            } else {
                throw new IllegalArgumentException("Type of this value is not supported. Type = " + value.getClass().getSimpleName());
            }
        });
    }

    @Override
    public int countAll() {
        return service.countAll();
    }

    @Override
    public int countForField(@Nonnull @NonNull String field) {
        String findForFieldParam = getFindForFieldParam();
        ParamsHolderBuilder builder = getBuilder(null);
        builder.param(findForFieldParam, field);
        return service.countAll(builder);
    }

    @Override
    public int countByParams(@Nonnull @NonNull Map<String, Object> params) {
        ParamsHolderBuilder builder = getBuilder(null);
        addParamsToBuilder(params, builder);
        return service.countAll(builder);
    }

    @Nonnull
    private ParamsHolderBuilder getBuilder(@Nullable Pageable pageable) {
        ParamsHolderBuilder builder = new ParamsHolderBuilder();
        if (Objects.nonNull(pageable)) {
            builder.page(pageable.getPageNumber());
        }
        return builder;
    }
}
