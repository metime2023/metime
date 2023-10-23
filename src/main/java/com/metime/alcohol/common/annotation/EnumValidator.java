package com.metime.alcohol.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
/*
정렬조건을 String으로 입력받아 애플리케이션에 정의해논 Enum타입과 매칭이 안되는경우 검증실패처리하기위한 클래스
매칭이 안되면 false를 반환합니다.
*/
public class EnumValidator implements ConstraintValidator<SortCondition, String> {

    private SortCondition sortConditionEnum;

    @Override
    public void initialize(SortCondition sortConditionEnum) {
        this.sortConditionEnum = sortConditionEnum;
    }

    @Override
    public boolean isValid(String sort, ConstraintValidatorContext context) {
        if (sort == null) {
            return true;
        }

        Object[] sortConditions = this.sortConditionEnum.enumClass().getEnumConstants();
        if (sortConditions == null) {
            return false;
        }

        return Arrays.stream(sortConditions)
                .anyMatch(sortCondition -> sort.equals(sortCondition.toString()) || (
                        sortConditionEnum.ignoreCase() && sort.equalsIgnoreCase(sortCondition.toString())));
    }
}
