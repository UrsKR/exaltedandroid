package anathema.android.util;

import java.lang.reflect.InvocationTargetException;

class MethodCallStrategy implements ResolutionStrategy {
  private final Class clazz;
  private final Object instance;

  public MethodCallStrategy(Class clazz, Object instance) {
    this.clazz = clazz;
    this.instance = instance;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String findReplacement(String placeholder) {
    try {
      return (String) clazz.getDeclaredMethod(placeholder).invoke(instance);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}