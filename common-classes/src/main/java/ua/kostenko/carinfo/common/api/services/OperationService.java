package ua.kostenko.carinfo.common.api.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kostenko.carinfo.common.api.ParamsHolderBuilder;
import ua.kostenko.carinfo.common.api.records.Operation;
import ua.kostenko.carinfo.common.database.repositories.DBRepository;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
class OperationService extends CommonDbService<Operation, Long> {

    @Autowired
    protected OperationService(@NonNull @Nonnull DBRepository<Operation, Long> repository) {
        super(repository);
    }

    @Override
    public boolean isValid(@NonNull @Nonnull Operation entity) {
        return Objects.nonNull(entity.getOperationCode()) && StringUtils.isNotBlank(entity.getOperationName());
    }

    @Override
    public Optional<Operation> get(@NonNull @Nonnull Operation entity) {
        ParamsHolderBuilder builder = new ParamsHolderBuilder();
        builder.param(Operation.OPERATION_CODE, entity.getOperationCode());
        Operation foundEntity = this.repository.findOne(builder.build());
        return Optional.ofNullable(foundEntity);
    }
}
