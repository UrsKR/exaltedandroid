package anathema.android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTableResolver implements PlaceholderResolver {
  public static final String JSON_TABLE_PATTERN = "#(.+?)#";
  private final PatternBasedResolver resolver;
  private Randomizer randomizer;

  public JsonTableResolver(Randomizer randomizer) {
    this.resolver = new PatternBasedResolver(JSON_TABLE_PATTERN);
    this.randomizer = randomizer;
  }

  @Override
  public String resolvePlaceholders(String unresolved) {
    Pattern jsonTable = Pattern.compile(JSON_TABLE_PATTERN);
    Matcher jsonMatcher = jsonTable.matcher(unresolved);
    return resolveThroughJsonRolls(jsonMatcher, unresolved);
  }

  @Override
  public boolean moreToResolve(String resolved) {
    return resolver.moreToResolve(resolved);
  }

  private String resolveThroughJsonRolls(Matcher matcher, String unresolved) {
    String resolvedText = unresolved;
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String replacement;
      if (placeholder.contains(",")) {
        String[] split = placeholder.split(",");
        replacement = randomizer.pickPropertyFromJsonArray(split[0], split[1]);
      } else {
        replacement = randomizer.pickElementFromJsonArray(placeholder);
      }
      resolvedText = resolvedText.replaceFirst("#" + placeholder + "#", replacement);
    }
    return resolvedText;
  }
}