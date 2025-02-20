package io.quarkus.runtime.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be used to force a class to be registered for reflection in native image mode.
 * Note that by default nested classes and interfaces are not registered, unless {@link #ignoreNested()} is set to false.
 * Similarly, by default only the class itself is registered, not the full class hierarchy. This can be changed by setting
 * {@link #registerFullHierarchy()} to true.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterForReflection {

    /**
     * If the methods should be registered
     */
    boolean methods() default true;

    /**
     * If the fields should be registered
     */
    boolean fields() default true;

    /**
     * If nested classes/interfaces should be ignored/registered
     *
     * This is useful when it's necessary to register inner (especially private) classes for Reflection.
     */
    boolean ignoreNested() default true;

    /**
     * Alternative classes that should actually be registered for reflection instead of the current class.
     *
     * This allows for classes in 3rd party libraries to be registered without modification or writing an
     * extension. If this is set then the class it is placed on is not registered for reflection, so this should
     * generally just be placed on an empty class that is not otherwise used.
     */
    Class<?>[] targets() default {};

    /**
     * This allows for classes to be registered for reflection via class names. This is useful when it's necessary to
     * register private classes for Reflection.
     */
    String[] classNames() default {};

    boolean serialization() default false;

    boolean unsafeAllocated() default false;

    /**
     * The lambda capturing types performing serialization in the native image
     */
    String[] lambdaCapturingTypes() default {};

    /**
     * If the full class hierarchy and dependencies should be registered.
     * This is useful in order to use a class to be transfered through a restful service API
     *
     * @return
     */
    boolean registerFullHierarchy() default false;
}
