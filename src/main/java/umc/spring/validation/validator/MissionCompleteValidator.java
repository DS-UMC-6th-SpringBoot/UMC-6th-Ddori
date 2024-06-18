package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.validation.annotation.CompleteMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberRequestDTO.CompleteMissionDto;

@Component
@RequiredArgsConstructor
public class MissionCompleteValidator implements ConstraintValidator<CompleteMission, CompleteMissionDto> {

  private final MemberCommandService memberCommandService;

  @Override
  public void initialize(CompleteMission constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(MemberRequestDTO.CompleteMissionDto value, ConstraintValidatorContext context) {
    boolean isValid = memberCommandService.isMemberMissionCompleted(value);

    if (isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_COMPLETE_MEMBER_MISSION.toString())
          .addConstraintViolation();
    }

    return !isValid;
  }
}