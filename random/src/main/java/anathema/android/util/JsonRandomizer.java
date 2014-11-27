package anathema.android.util;

import anathema.android.DiceAndCoins;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonRandomizer implements Randomizer {

  private final DiceAndCoins diceAndCoins;
  private final FileToString fileToString;
  private final String folder;

  public JsonRandomizer(String folder, DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.folder = folder;
    this.diceAndCoins = diceAndCoins;
    this.fileToString = fileToString;
  }

  @Override
  public String pickElementFromJsonArray(String array) {
    String content = loadArrayFileToString(array);
    try {
      JSONArray entries = new JSONObject(content).getJSONArray(array);
      int roll = diceAndCoins.rollWithSides(entries.length());
      return entries.getString(roll - 1);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String pickNameFromJsonArray(String array) {
    return pickPropertyFromJsonArray(array, "name");
  }

  @Override
  public String pickAttributeFromJsonArray(String array) {
    return pickPropertyFromJsonArray(array, "attribute");
  }

  private String pickPropertyFromJsonArray(String array, String property) {
    String content = loadArrayFileToString(array);
    try {
      JSONArray entries = new JSONObject(content).getJSONArray(array);
      int roll = diceAndCoins.rollWithSides(entries.length());
      return entries.getJSONObject(roll - 1).getString(property);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  private String loadArrayFileToString(String array) {
    String filename = array + ".json";
    if (folder != null) {
      filename = folder + "/" + filename;
    }
    return fileToString.loadFile(filename);
  }
}