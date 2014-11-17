package anathema.android.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderResolver {

  private Class clazz;
  private Object instance;

  public PlaceholderResolver(Class clazz, Object instance) {
    this.clazz = clazz;
    this.instance = instance;
  }

  @SuppressWarnings("unchecked")
  public String resolvePlaceholders(String textWithPlaceholders) {
    Pattern pattern = Pattern.compile("%(.+?)%");
    Matcher matcher = pattern.matcher(textWithPlaceholders);
    String resolvedText = textWithPlaceholders;
    while (matcher.find()) {
      try {
        String group = matcher.group(1);
        String element = (String) clazz.getDeclaredMethod(group).invoke(instance);
        resolvedText = resolvedText.replaceAll("%" + group + "%", element);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }
    return resolvedText;
  }
}
