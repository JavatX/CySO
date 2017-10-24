package es.uvigo.esei.cyso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import static es.uvigo.esei.cyso.MainActivity.CONECTION_TIMEOUT;

public class ReportInfoActivity extends AppCompatActivity {

    private Bitmap bm;
    private ImageView image;
    private AccessTask aT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Bundle extras = getIntent().getExtras();
        final String nD = extras.getString("numDenuncia");
        setTitle(nD);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        ImageTask imageTask = new ImageTask(nD);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView fec = (TextView) findViewById(R.id.fec);
        fec.setText(extras.getString("fecha"));
        TextView cons = (TextView) findViewById(R.id.cons);
        cons.setText(extras.getString("construccion"));
        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aT = new AccessTask(extras.getString("construccion"));
                aT.execute();
            }
        });
        TextView des = (TextView) findViewById(R.id.des);
        des.setText(extras.getString("descripcion"));

        image = (ImageView) findViewById(R.id.fotoden);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportInfoActivity.this, ShowImage.class);
                i.putExtra("image", nD);
                startActivity(i);
            }
        });
        imageTask.execute();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class ImageTask extends AsyncTask<Void, Void, Bitmap> {

        private String numDen;
        ImageTask(String den){
            this.numDen = den;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            image.setImageBitmap(s);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String uri = "http://83.165.253.135/obras/webroot/img/Denuncias/" + numDen + ".jpg";
            bm = getBitmapFromURL(uri);
            return bm;
        }
    }

    public class AccessTask extends AsyncTask<Void, Void, String> {

        URL url = null;
        HttpURLConnection connection;
        private String construction, report;
        private boolean called = false;
        private boolean typeCall; //true - Construction, false - Report

        AccessTask() {
        }

        AccessTask(String construction) {
            this.construction = construction;
            typeCall = true;
        }

        public void setConstruccion(String value) {
            this.construction = value;
            typeCall = true;
        }

        public boolean getCalled(){return called;}

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                Log.d("INFO", "ReportInfo");
                if(typeCall)
                    url = new URL("http://83.165.253.135/android/cons_info.php");
                else
                    url = new URL("http://83.165.253.135/android/report_info.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(CONECTION_TIMEOUT);
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder;
                if(typeCall)
                    builder = new Uri.Builder().appendQueryParameter("cons", construction);
                else
                    builder = new Uri.Builder().appendQueryParameter("cons", report);
                String query = builder.build().getEncodedQuery();
                Log.d("INFO", query);
                OutputStream os = connection.getOutputStream();
                Log.d("INFO", "os");
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );
                Log.d("INFO", "writer start");
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                Log.d("INFO", "connected");
                connection.connect();
                //ss Simulate network access.
                //Thread.sleep(2000);

            } catch (IOException ie) {
                Log.d("INFO", ie.toString());
                return "exception";
            }

            try {
                int response_code = connection.getResponseCode();
                Log.d("INFO", "Connection response");
                if (response_code == HttpURLConnection.HTTP_OK) {
                    Log.d("INFO", "Connection if");
                    InputStream input = connection.getInputStream();
                    Log.d("INFO", "Connection input");
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(input)
                    );
                    Log.d("INFO", "Connection buffer");
                    StringBuilder result = new StringBuilder();
                    String line;
                    Log.d("INFO", "Connection string");
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    Log.d("INFO", result.toString());
                    return result.toString();

                } else {
                    return "unsuccessful";
                }
            } catch (IOException io) {
                return "exception";
            } finally {
                Log.d("INFO", "Disconnected");
                connection.disconnect();
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

            // TODO: register the new account here.
            //return true;
        }

        @Override
        protected void onPostExecute(final String result) {
            Log.d("INFO-PostExecute", "Result contains: " + result);

            if(typeCall) {
                if (!result.equals("exception")) {
                    aT = null;
                    //showProgress(false);
                    Log.d("INFO-PostExecute", "Split data");
                    String data[] = result.split("&");
                    Log.d("INFO-PostExecute", "Data lenght: " + data.length);
                    for (int i = 0; i < data.length; i++)
                        Log.d("INFO-PostExecute", "Dato " + i + " es: " + data[i]);
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
*/
                    Log.d("INFO-PostExecute", data[0]);
                    Log.d("INFO-PostExecute", data[1]);
                    Log.d("INFO-PostExecute", data[2]);
                    Log.d("INFO-PostExecute", data[3]);
                    Log.d("INFO-PostExecute", data[4]);
                    Log.d("INFO-PostExecute", data[5]);
                    Intent intent = new Intent(ReportInfoActivity.this, InformationActivity.class);
                    intent.putExtra("id", data[0]);
                    intent.putExtra("direccion", data[1]);
                    intent.putExtra("superficie", data[2]);
                    intent.putExtra("descripcion", data[3]);
                    intent.putExtra("constructor", data[4] + " " + data[5]);
                    called = true;
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
                    //Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
                }
            }
        }

        @Override
        protected void onCancelled() {
            aT = null;
            //showProgress(false);
        }
    }
}
