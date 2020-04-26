package de.youtclubstage.virtualyouthclub.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityAnotation {
    UserType[] adminType() default {UserType.USER};
    boolean openCheck() default false;
    boolean agreementCheck() default false;
    OrgType orgType() default OrgType.PUBLIC;
}
