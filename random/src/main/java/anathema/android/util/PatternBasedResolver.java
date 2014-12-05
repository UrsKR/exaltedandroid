package anathema.android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternBasedResolver implements PlaceholderResolver{
  private final Pattern pattern;
  private final String markingCharacter;
  private final ResolutionStrategy strategy;

  public PatternBasedResolver(String markingCharacter, ResolutionStrategy strategy) {
    this.markingCharacter = markingCharacter;
    this.strategy = strategy;
    this.pattern = Pattern.compile(markingCharacter + "(.+?)" + markingCharacter);
  }

  public boolean moreToResolve(String resolved) {
    return pattern.matcher(resolved).find();
  }

  public String resolvePlaceholders(String unresolved) {
    Matcher matcher = pattern.matcher(unresolved);
    String resolvedText = unresolved;
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String replacement = strategy.findReplacement(placeholder);
      resolvedText = resolvedText.replaceAll(markingCharacter + placeholder + markingCharacter, replacement);
    }
    return resolvedText;
  }
}
