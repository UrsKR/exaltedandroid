package anathema.android.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallResolver implements PlaceholderResolver {
  public static final String METHOD_CALL_PATTERN = "%(.+?)%";
  private final Class clazz;
  private final Object instance;
  private final PatternBasedResolver resolver;

  public MethodCallResolver(Class clazz, Object instance) {
    this.resolver = new PatternBasedResolver(METHOD_CALL_PATTERN);
    this.clazz = clazz;
    this.instance = instance;
  }

  @Override
  public String resolvePlaceholders(String unresolved) {
    Pattern methodCall = Pattern.compile(METHOD_CALL_PATTERN);
    Matcher methodMatcher = methodCall.matcher(unresolved);
    return resolveThroughMethodCalls(methodMatcher, unresolved);
  }

  @Override
  public boolean moreToResolve(String resolved) {
    return resolver.moreToResolve(resolved);
  }

  @SuppressWarnings("unchecked")
  private String resolveThroughMethodCalls(Matcher matcher, String unresolved) {
    String resolvedText = unresolved;
    while (matcher.find()) {
      try {
        String placeholder = matcher.group(1);
        String replacement = (String) clazz.getDeclaredMethod(placeholder).invoke(instance);
        resolvedText = resolvedText.replaceAll("%" + placeholder + "%", replacement);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }
    return resolvedText;
  }
}