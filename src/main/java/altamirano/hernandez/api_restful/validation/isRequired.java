package altamirano.hernandez.api_restful.validation;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface isRequired {
    String message() default "Es requerido usando anotaciones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
