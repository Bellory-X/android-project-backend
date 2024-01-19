package com.person.platform;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class SpecificationUtils {

    public static String lowerCaseSubstringPattern(String substring) {
        return substringPattern(substring.toLowerCase());
    }

    public static String substringPattern(String substring) {
        return "%" + substring + "%";
    }

    public static Predicate substringIgnoringCase(CriteriaBuilder criteriaBuilder, Path<String> path, String substring) {
        return criteriaBuilder.like(criteriaBuilder.lower(path), lowerCaseSubstringPattern(substring));
    }
}
