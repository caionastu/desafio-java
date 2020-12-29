package com.caionastu.desafiojava.comum.validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnicidadeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unicidade {

    String id() default "id";

    String campo();

    String entidade();

    String message() default "Entidade duplicada.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
