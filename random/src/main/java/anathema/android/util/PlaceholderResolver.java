package anathema.android.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderResolver {

  public static final String METHOD_CALL_PATTERN = "%(.+?)%";
  public static final String JSON_TABLE_PATTERN = "#(.+?)#";
  private final Randomizer randomizer;
  private final Class clazz;
  private final Object instance;

  public PlaceholderResolver(Class clazz, Object instance) {
    this(clazz, instance, new NullRandomizer());
  }
  public PlaceholderResolver(Class clazz, Object instance, Randomizer randomizer){
    this.clazz = clazz;
    this.instance = instance;
    this.randomizer = randomizer;
  }

  public String resolvePlaceholders(String unresolved) {
    Pattern methodCall = Pattern.compile(METHOD_CALL_PATTERN);
    Pattern jsonTable = Pattern.compile(JSON_TABLE_PATTERN);
    Matcher jsonMatcher = jsonTable.matcher(unresolved);
    String halfResolved = resolveThroughJsonRolls(jsonMatcher, unresolved);
    Matcher methodMatcher = methodCall.matcher(halfResolved);
    String fullyResolved = resolveThroughMethodCalls(methodMatcher, halfResolved);
    if (methodCall.matcher(fullyResolved).find() || jsonTable.matcher(fullyResolved).find()) {
      fullyResolved = resolvePlaceholders(fullyResolved);
    }
    return fullyResolved;
  }

  private String resolveThroughJsonRolls(Matcher matcher, String unresolved) {
    String resolvedText = unresolved;
    while (matcher.find()) {
      String group = matcher.group(1);
      String element = randomizer.pickElementFromJsonArray(group);
      resolvedText = resolvedText.replaceAll("#" + group + "#", element);
    }
    return resolvedText;
  }

  @SuppressWarnings("unchecked")
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
