package anathema.android.util;

import java.util.regex.Pattern;

public class PatternBasedResolver {
  private final Pattern pattern;

  public PatternBasedResolver(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }

  public boolean moreToResolve(String resolved) {
    return pattern.matcher(resolved).find();
  }
}
