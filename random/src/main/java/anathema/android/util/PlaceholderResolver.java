package anathema.android.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderResolver {

  public static final String METHOD_CALL_PATTERN = "%(.+?)%";
  public static final String JSON_TABLE_PATTERN = "#(.+?)#";
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
    resolvedText = resolveThroughMethodCalls(matcher, resolvedText);
    if (pattern.matcher(resolvedText).find()) {
      resolvedText = resolvePlaceholders(resolvedText);
    }
    return resolvedText;
  }

  public String resolvePlaceholders(String unresolved, JsonRandomizer randomizer) {
    Pattern methodCall = Pattern.compile(METHOD_CALL_PATTERN);
    Pattern jsonTable = Pattern.compile(JSON_TABLE_PATTERN);
    Matcher jsonMatcher = jsonTable.matcher(unresolved);
    String halfResolved = resolveThroughJsonRolls(jsonMatcher, unresolved, randomizer);
    Matcher methodMatcher = methodCall.matcher(halfResolved);
    String fullyResolved = resolveThroughMethodCalls(methodMatcher, halfResolved);
    if (methodCall.matcher(fullyResolved).find() || jsonTable.matcher(fullyResolved).find()) {
      fullyResolved = resolvePlaceholders(fullyResolved, randomizer);
    }
    return fullyResolved;
  }

  private String resolveThroughJsonRolls(Matcher matcher, String unresolved, JsonRandomizer randomizer) {
    String resolvedText = unresolved;
    while (matcher.find()) {
      String group = matcher.group(1);
      String element = randomizer.pickElementFromJsonArray(group);
      resolvedText = resolvedText.replaceAll("#" + group + "#", element);
    }
    return resolvedText;
  }

  private String resolveThroughMethodCalls(Matcher matcher, String unresolved) {
    String resolvedText = unresolved;
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
