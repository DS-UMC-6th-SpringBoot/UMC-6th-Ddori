package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import umc.spring.validation.validator.MissionCompleteValidator;

@Documented
@Constraint(validatedBy = MissionCompleteValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompleteMission {
  String message() default "이미 진행 완료된 미션입니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}