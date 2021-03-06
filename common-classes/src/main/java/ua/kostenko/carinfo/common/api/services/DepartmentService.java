package ua.kostenko.carinfo.common.api.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kostenko.carinfo.common.api.ParamsHolderBuilder;
import ua.kostenko.carinfo.common.api.records.Department;
import ua.kostenko.carinfo.common.database.repositories.DBRepository;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
class DepartmentService extends CommonDbService<Department, Long> {

    @Autowired
    protected DepartmentService(@NonNull @Nonnull DBRepository<Department, Long> repository) {
        super(repository);
    }

    @Override
    public boolean isValid(@NonNull @Nonnull Department entity) {
        return Objects.nonNull(entity.getDepartmentCode());
    }

    @Override
    public Optional<Department> get(@NonNull @Nonnull Department entity) {
        ParamsHolderBuilder builder = new ParamsHolderBuilder();
        builder.param(Department.DEPARTMENT_CODE, entity.getDepartmentCode());
        Department foundEntity = this.repository.findOne(builder.build());
        return Optional.ofNullable(foundEntity);
    }
}
