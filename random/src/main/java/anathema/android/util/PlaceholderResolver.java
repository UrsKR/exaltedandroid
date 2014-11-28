package anathema.android.util;

public interface PlaceholderResolver {
  String resolvePlaceholders(String unresolved);

  boolean moreToResolve(String resolved);
}
