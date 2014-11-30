package anathema.android.lifepath;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import android.content.Context;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-lifepathing-your-character.html
public class LifepathGenerator implements Generator {
  private static final String Pattern_Suggestion = "\\((.*?)\\)";
  private static final String Pattern_DoubleBlank = "  ";
  private Context context;

  public LifepathGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String lifepath = new GenerateLifepath(diceAndCoins, new FileToString(context.getAssets())).generate();
    String suggestionString = compileSuggestedTraits(lifepath);
    lifepath = removeSuggestionsFromLifepath(lifepath);
    lifepath += "\n\nSuggested traits to reflect this background: " + suggestionString;
    Result result = new Result();
    result.title = context.getString(R.string.title_lifepath);
    result.text = lifepath;
    return result;
  }

  private String compileSuggestedTraits(String lifepath) {
    Set<String> suggestions = findSuggestedTraitsInLifepath(lifepath);
    return buildSuggestionsAsString(suggestions);
  }

  private String buildSuggestionsAsString(Set<String> suggestions) {
    if (suggestions.isEmpty()) {
      return "None";
    }
    return TextUtils.join(", ", suggestions);
  }

  private Set<String> findSuggestedTraitsInLifepath(String lifepath) {
    Matcher suggestionMatcher = Pattern.compile(Pattern_Suggestion).matcher(lifepath);
    Set<String> suggestions = new LinkedHashSet<>();
    while (suggestionMatcher.find()) {
      for (int groupCount = 1; groupCount <= suggestionMatcher.groupCount(); groupCount++) {
        String suggestion = suggestionMatcher.group(groupCount);
        suggestions.add(suggestion);
      }
    }
    return suggestions;
  }

  private String removeSuggestionsFromLifepath(String lifepath) {
    lifepath = lifepath.replaceAll(Pattern_Suggestion, "");
    lifepath = lifepath.replaceAll(Pattern_DoubleBlank, " ");
    lifepath = lifepath.replaceAll(" \\.", ".");
    lifepath = lifepath.replaceAll(" ,", ",");
    lifepath = lifepath.replaceAll(" s\\.", "s.");
    lifepath = lifepath.replaceAll(" 's", "'s");
    return lifepath;
  }
}