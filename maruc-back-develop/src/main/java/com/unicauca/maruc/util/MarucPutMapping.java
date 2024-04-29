package com.unicauca.maruc.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.unicauca.maruc.comun.ConstantesGlogables;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.PUT, consumes = ConstantesGlogables.APPLICATION_JSON,
    produces = ConstantesGlogables.APPLICATION_JSON)
public @interface MarucPutMapping {

  /**
   * Alias for {@link RequestMapping#name}.
   */
  @AliasFor(annotation = RequestMapping.class)
  String name() default "";

  /**
   * Alias for {@link RequestMapping#value}.
   */
  @AliasFor(annotation = RequestMapping.class)
  String[] value() default {};

  /**
   * Alias for {@link RequestMapping#path}.
   */
  @AliasFor(annotation = RequestMapping.class)
  String[] path() default {};

  /**
   * Alias for {@link RequestMapping#params}.
   */
  @AliasFor(annotation = RequestMapping.class)
  String[] params() default {};

  /**
   * Alias for {@link RequestMapping#headers}.
   */
  @AliasFor(annotation = RequestMapping.class)
  String[] headers() default {};
}
