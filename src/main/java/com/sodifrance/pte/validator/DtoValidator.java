package com.sodifrance.pte.validator;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.util.CollectionUtils;

import com.sodifrance.pte.commun.utils.PteCollectionUtils;
import com.sodifrance.pte.exceptions.PteParametersException;

/**
 * The Class DtoValidator.<br>
 * Permet de valider les données en entrée des services.<br>
 * Fonctionne avec les annotations sur les classes DTO
 */
public class DtoValidator {

    private DtoValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    /** The validator. */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Validation du Dto en entrée du service
     *
     * @param <T> Type du Dto
     * @param dto dto à valider
     */
    public static <T> void validateDto(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!CollectionUtils.isEmpty(violations)) {
            Set<String> messages = violations.stream()
                    .filter(PteCollectionUtils.distinctByKey(c -> c.getPropertyPath().toString()))
                    .map(ConstraintViolation::getMessage).collect(Collectors.toSet());

            throw new PteParametersException("L'input n'est pas valide : " + String.join(" ", messages));
        }
    }
}
