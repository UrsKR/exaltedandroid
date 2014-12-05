package anathema.android.util;

import java.util.Map;

public class CombinedResolver {

  public static PlaceholderResolver create(Class clazz, Object instance, Randomizer randomizer,
                                           Map<String, String> namedValues) {
    return new DelegatingResolver(
            new PatternBasedResolver("~", new NamedValueStrategy(namedValues)),
            new PatternBasedResolver("#", new JsonTableStrategy(randomizer)),
            new PatternBasedResolver("%", new MethodCallStrategy(clazz, instance)));
  }

  public static PlaceholderResolver createWithoutNamedValues(Class clazz, Object instance, Randomizer randomizer) {
    return new DelegatingResolver(
            new PatternBasedResolver("#", new JsonTableStrategy(randomizer)),
            new PatternBasedResolver("%", new MethodCallStrategy(clazz, instance)));
  }
}