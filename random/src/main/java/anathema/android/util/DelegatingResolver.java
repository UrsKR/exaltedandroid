package anathema.android.util;

public class DelegatingResolver implements PlaceholderResolver {

  private PlaceholderResolver[] resolvers;

  public DelegatingResolver(PlaceholderResolver... resolvers) {
    this.resolvers = resolvers;
  }

  @Override
  public String resolvePlaceholders(String unresolved) {
    String resolved = resolve(unresolved);
    resolved = recurse(resolved);
    return resolved;
  }

  @Override
  public boolean moreToResolve(String resolved) {
    return false;
  }

  private String recurse(String resolved) {
    for (PlaceholderResolver resolver : resolvers) {
      boolean moreToResolve = resolver.moreToResolve(resolved);
      if (moreToResolve) {
        resolved = resolvePlaceholders(resolved);
      }
    }
    return resolved;
  }

  private String resolve(String unresolved) {
    String resolved = unresolved;
    for (PlaceholderResolver resolver : resolvers) {
      resolved = resolver.resolvePlaceholders(resolved);
    }
    return resolved;
  }
}
