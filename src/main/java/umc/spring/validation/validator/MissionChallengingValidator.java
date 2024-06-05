package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.ChallengingMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionRequestDTO.CreateMemberMission;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<ChallengingMission, CreateMemberMission> {

  private final MemberMissionCommandService memberMissionCommandService;

  @Override
  public void initialize(ChallengingMission constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(MemberMissionRequestDTO.CreateMemberMission value, ConstraintValidatorContext context) {
    boolean isValid = memberMissionCommandService.existsMemberMission(value);

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_EXIST_MEMBER_MISSION.toString())
          .addConstraintViolation();
    }

    return isValid;
  }

}
