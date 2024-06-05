package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;

public class PageCheckValidator implements ConstraintValidator<CheckPage, Number> {

  @Override
  public void initialize(CheckPage constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Number value, ConstraintValidatorContext context) {

//    if (value == null) {
//      return true;
//    }

    boolean isValid = value.longValue() >= 0;

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_SIZE_TOO_SMALL.toString()).addConstraintViolation();
    }

    return isValid;
  }
}
