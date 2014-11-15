package anathema.android.util;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class FileToString {
  private AssetManager manager;

  public FileToString(AssetManager manager) {
    this.manager = manager;
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  public String loadFile(String filename) {
    try (InputStream stream = manager.open(filename)){
      int size = stream.available();
      byte[] buffer = new byte[size];
      stream.read(buffer);
      return new String(buffer, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}