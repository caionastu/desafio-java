package com.caionastu.desafiojava.comum.validacoes;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
public class UnicidadeValidator implements ConstraintValidator<Unicidade, Object> {

    private final EntityManager entityManager;
    private final HttpServletRequest servletRequest;

    private String id;
    private String entidade;
    private String campo;

    @Override
    public void initialize(Unicidade constraintAnnotation) {
        this.id = constraintAnnotation.id();
        this.entidade = constraintAnnotation.entidade();
        this.campo = constraintAnnotation.campo();
    }

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext contexto) {
        if (Objects.isNull(objeto)) {
            return true;
        }

        Optional<String> optionalId = recuperaId();

        TypedQuery<Long> query;
        if (optionalId.isPresent()) {
            String querySQL = String.format("select count(*) from %s where %s = ?1 and %s != ?2", entidade, campo, id);
            query = entityManager.createQuery(querySQL, Long.class);
            query.setParameter(2, optionalId.get());
        } else {
            String querySQL = String.format("select count(*) from %s where %s = ?1", entidade, campo);
            query = entityManager.createQuery(querySQL, Long.class);
        }

        query.setParameter(1, objeto);

        return query.getSingleResult() == 0L;
    }

    private Optional<String> recuperaId() {
        Map<?, ?> pathVariables = Optional.ofNullable(servletRequest.getAttribute("org.springframework.web.servlet.View.pathVariables"))
                .map(variables -> (Map<?, ?>) variables)
                .orElse(Collections.emptyMap());

        return Optional.ofNullable(pathVariables.get("id")).map(Object::toString);

    }
}
