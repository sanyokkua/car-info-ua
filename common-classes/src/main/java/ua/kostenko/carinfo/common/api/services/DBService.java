package ua.kostenko.carinfo.common.api.services;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import ua.kostenko.carinfo.common.api.ParamsHolderBuilder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface DBService<T> {
    Optional<T> create(@Nonnull @NonNull final T entity);
    Optional<T> update(@Nonnull @NonNull final T entity);
    boolean delete(final long id);
    boolean exists(@Nonnull @NonNull final T entity);
    boolean isValid(@Nonnull @NonNull final T entity);
    Optional<T> get(final long id);
    Optional<T> get(@Nonnull @NonNull ParamsHolderBuilder builder);
    Optional<T> get(@Nonnull @NonNull T entity);
    List<T> getAll();
    Page<T> getAll(@Nonnull @NonNull ParamsHolderBuilder builder);
    int countAll();
    int countAll(@Nonnull @NonNull ParamsHolderBuilder builder);
}
