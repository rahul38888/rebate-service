package com.speedybrand.rebate.repo.mongodb.condition;

import lombok.NonNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDbEnabled implements Condition {
    @Override
    public boolean matches(@NonNull final ConditionContext context,
                           @NonNull final AnnotatedTypeMetadata metadata) {
        return true;
    }
}
