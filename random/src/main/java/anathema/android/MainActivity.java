package anathema.android;

import anathema.android.fashion.FashionGenerator;
import anathema.android.flashbacks.FlashbackGenerator;
import anathema.android.lifepath.LifepathGenerator;
import anathema.android.manse.ManseGenerator;
import anathema.android.name.NameGenerator;
import anathema.android.village.VillageGenerator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.*;
import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends Activity {

  private final DiceAndCoins diceAndCoins = new DiceAndCoins();
  private List<Result> dataset = new ArrayList<>();
  private RecyclerView resultView;
  private ShareActionProvider shareActionProvider;
  private ResultAdapter resultAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    resultView = (RecyclerView) findViewById(R.id.result_view);
    resultView.setLayoutManager(new LinearLayoutManager(this));
    resultAdapter = new ResultAdapter(dataset);
    resultView.setAdapter(resultAdapter);
  }

  public void generateManse(View view) {
    generateAndShow(new ManseGenerator(getApplicationContext()));
  }

  public void generateFashion(View view) {
    generateAndShow(new FashionGenerator(getApplicationContext()));
  }

  public void generateFlashback(View view) {
    generateAndShow(new FlashbackGenerator(getApplicationContext()));
  }

  public void generateVillage(View view) {
    generateAndShow(new VillageGenerator(getApplicationContext()));
  }

  public void generateLifepath(View view) {
    generateAndShow(new LifepathGenerator(getApplicationContext()));
  }
  
  public void generateName(View view) {
    generateAndShow(new NameGenerator(getApplicationContext()));
  }

  private void generateAndShow(Generator generator) {
    Result result = generator.generate(diceAndCoins);
    dataset.add(0, result);
    resultAdapter.notifyItemInserted(0);
    resultView.scrollToPosition(0);
    setShareIntent(result);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    MenuItem item = menu.findItem(R.id.menu_item_share);
    shareActionProvider = (ShareActionProvider) item.getActionProvider();
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
    AlertDialog dialog = new AlertDialog.Builder(this).setPositiveButton(android.R.string.ok, null).setTitle(
            R.string.credits_title).setMessage(credits).create();
    dialog.show();
    ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
  }

  public void setShareIntent(Result result) {
    Intent shareIntent = new Intent();
    shareIntent.setAction(ACTION_SEND);
    shareIntent.putExtra(EXTRA_SUBJECT, result.title + " (" + getString(R.string.text_generated_with) + ")");
    shareIntent.putExtra(EXTRA_TEXT, result.text);
    shareIntent.setType("text/plain");
    if (shareActionProvider != null) {
      shareActionProvider.setShareIntent(shareIntent);
    }
  }

  public void suggestImprovement(MenuItem item) {
    Intent suggestIntent = new Intent(ACTION_SENDTO);
    suggestIntent.setType("text/plain");
    String uriText = "mailto:" + Uri.encode("ursreupke+exaltedrandom@gmail.com") +
            "?subject=" + Uri.encode(getString(R.string.text_suggestion_mail_subject)) +
            "&body=" + Uri.encode(getString(R.string.text_suggestion_mail_body));
    Uri data = Uri.parse(uriText);
    suggestIntent.setData(data);
    try {
      startActivity(Intent.createChooser(suggestIntent, getString(R.string.label_send_mail)));
    } catch (android.content.ActivityNotFoundException ex) {
      Toast.makeText(this, getString(R.string.toast_no_mail_clients), LENGTH_SHORT).show();
    }
  }
}