package okisum.example.xmlRest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import okisum.example.xmlRest.R;

public class MainActivity extends Activity {

	TextView sampleRest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sampleRest = (TextView) findViewById(R.id.sampleRest);
		new PostAsync().execute();
	}
	
	class PostAsync extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd;
		XMLHelper helper;
		
		
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(MainActivity.this, "Xml Sample", "Loading xml...", true, false);
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			helper = new XMLHelper();
			helper.get();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			StringBuilder builder = new StringBuilder(); 
			for(PostValue post : helper.posts) {
				builder.append("\nUser Id: " + post.getId());
				builder.append("\nUsername: " + post.getTitle());
				builder.append("\nPassword: " + post.getPass());
				builder.append("\n");
			}
			sampleRest.setText(builder.toString());
			pd.dismiss();
		}

	}

}
