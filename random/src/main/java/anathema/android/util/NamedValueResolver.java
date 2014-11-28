package anathema.android.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedValueResolver implements PlaceholderResolver {
  public static final String NAMED_VALUE_PATTERN = "~(.+?)~";
  private Map<String, String> namedValues;

  public NamedValueResolver(Map<String, String> namedValues) {
    this.namedValues = namedValues;
  }

  @Override
  public String resolvePlaceholders(String unresolved) {
    Pattern namedValue = Pattern.compile(NAMED_VALUE_PATTERN);
    Matcher valueMatcher = namedValue.matcher(unresolved);
    return resolveWithNamedValues(valueMatcher, unresolved);
  }

  @Override
  public boolean moreToResolve(String resolved) {
    return Pattern.compile(NAMED_VALUE_PATTERN).matcher(resolved).find();
  }

  private String resolveWithNamedValues(Matcher matcher, String unresolved) {
    String resolvedText = unresolved;
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String replacement = namedValues.get(placeholder);
      resolvedText = resolvedText.replaceAll("~" + placeholder + "~", replacement);
    }
    return resolvedText;
  }
}