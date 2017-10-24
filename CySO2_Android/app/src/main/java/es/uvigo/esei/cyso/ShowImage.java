package es.uvigo.esei.cyso;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImage extends AppCompatActivity {

    private ImageView imgV;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        Bundle bundle = getIntent().getExtras();

        imgV = (ImageView) findViewById(R.id.imgdetail);
        ImageTask iT = new ImageTask(bundle.getString("image"));
        iT.execute();
    }

    public class ImageTask extends AsyncTask<Void, Void, Bitmap> {

        private String numDen;
        ImageTask(String den){
            this.numDen = den;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            imgV.setImageBitmap(s);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String uri = "http://83.165.253.135/obras/webroot/img/Denuncias/" + numDen + ".jpg";
            bitmap = ReportInfoActivity.getBitmapFromURL(uri);
            return bitmap;
        }
    }
}
