package anathema.android;

import anathema.android.fashion.FashionGenerator;
import anathema.android.flashbacks.FlashbackGenerator;
import anathema.android.lifepath.LifepathGenerator;
import anathema.android.manse.ManseGenerator;
import anathema.android.village.VillageGenerator;
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

  private final DiceAndCoins diceAndCoins = new DiceAndCoins();
  private TitleTextFragment fragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      this.fragment = new TitleTextFragment();
      getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    } else {
      this.fragment = (TitleTextFragment) getFragmentManager().getFragment(savedInstanceState, TitleTextFragment.class.getName());
    }
  }

  @SuppressWarnings("NullableProblems")
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getFragmentManager().putFragment(outState, TitleTextFragment.class.getName(), fragment);
  }

  public void generateManse(View view) {
    generateAndShow(new ManseGenerator());
  }

  public void generateFashion(View view) {
    generateAndShow(new FashionGenerator(getAssets()));
  }

  public void generateFlashback(View view) {
    generateAndShow(new FlashbackGenerator(getAssets()));
  }

  public void generateVillage(View view) {
    generateAndShow(new VillageGenerator(getAssets()));
  }

  public void generateLifepath(View view) {
    generateAndShow(new LifepathGenerator(getAssets()));
  }

  private void generateAndShow(Generator generator) {
    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    getFragmentManager().executePendingTransactions();
    Result result = generator.generate(diceAndCoins);
    fragment.setTitle(result.title);
    fragment.setText(result.text);
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