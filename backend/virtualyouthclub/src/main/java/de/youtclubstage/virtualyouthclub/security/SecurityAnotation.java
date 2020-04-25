package de.youtclubstage.virtualyouthclub.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityAnotation {
    AdminType[] adminType() default {AdminType.USER};
    boolean openCheck() default false;
}
