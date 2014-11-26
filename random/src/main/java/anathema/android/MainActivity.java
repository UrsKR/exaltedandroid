package anathema.android;

import anathema.android.fashion.FashionFragment;
import anathema.android.flashbacks.FlashbackFragment;
import anathema.android.lifepath.LifepathFragment;
import anathema.android.manse.ManseFragment;
import anathema.android.village.VillageFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

  private DiceAndCoins diceAndCoins = new DiceAndCoins();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    EmptyFragment empty = new EmptyFragment();
    getFragmentManager().beginTransaction().add(R.id.fragment_container, empty).commit();
  }

  public void generateManse(View view) {
    showAndGenerate(new ManseFragment());
  }

  public void generateFashion(View view) {
    showAndGenerate(new FashionFragment());
  }

  public void generateFlashback(View view) {
    showAndGenerate(new FlashbackFragment());
  }

  public void generateVillage(View view) {
    showAndGenerate(new VillageFragment());
  }

  public void generateLifepath(View view) {
    showAndGenerate(new LifepathFragment());
  }

  private void showAndGenerate(GeneratorFragment generatorFragment) {
    getFragmentManager().beginTransaction().replace(R.id.fragment_container, generatorFragment).commit();
    getFragmentManager().executePendingTransactions();
    generatorFragment.generate(diceAndCoins);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item_credits:
        showCredits(item);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public void showCredits(MenuItem item) {
    SpannableString credits = new SpannableString(getString(R.string.credits_text));
    Linkify.addLinks(credits, Linkify.ALL);
    AlertDialog dialog = new AlertDialog.Builder(this)
            .setPositiveButton(android.R.string.ok, null)
            .setTitle(R.string.credits_title)
            .setMessage(credits)
            .create();
    dialog.show();
    ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
  }
}