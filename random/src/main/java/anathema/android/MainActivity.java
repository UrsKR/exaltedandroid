package anathema.android;

import anathema.android.manse.ManseFragment;
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

  private Die die = new Die();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EmptyFragment empty= new EmptyFragment();
    getFragmentManager().beginTransaction().add(R.id.fragment_container, empty).commit();
  }

  public void generateLooks(View view) {
    //nothing to do
  }

  //http://lovethelabyrinth.blogspot.de/2014/11/this-old-manse.html
  public void generateManse(View view) {
    ManseFragment manse = new ManseFragment();
    getFragmentManager().beginTransaction().replace(R.id.fragment_container, manse).commit();
    getFragmentManager().executePendingTransactions();
    manse.generateManse(die);
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