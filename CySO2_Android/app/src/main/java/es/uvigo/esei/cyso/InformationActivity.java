package es.uvigo.esei.cyso;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static es.uvigo.esei.cyso.MainActivity.CONECTION_TIMEOUT;
import static es.uvigo.esei.cyso.SavedData.data;

public class InformationActivity extends AppCompatActivity {

    private UploadTask mUpTask = null;
    private PermissionsTask pT = null;

    private File folder, file;
    private Uri fileUri;
    private Context context;
    private boolean exito = true;

    String id, denuncia;
    View iView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            id = extras.getString("id");
            setTitle(id);
            data.addItemIdList("id", id);

            iView = findViewById(android.R.id.content);
            context = this;
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Log.d("CAMERA_2", "Foto");
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            Log.d("CAMERA_2", "Foto");
                            Log.d("External Storage", getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString());
                            folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                            //folder.mkdirs();
                            Log.d("CAMERA_2-Carpeta", folder.toString());
                            DateFormat dt = new SimpleDateFormat("ddMMyy_HHmmss");
                            Date date = new Date();
                            denuncia = dt.format(date) + id;
                            Log.d("CAMERA_2-Denuncia", denuncia);
                            try {
                                file = File.createTempFile(denuncia, ".jpg", folder);
                                Log.d("TEMP-File", file.toString());
                            } catch (IOException io) {
                            }
                            //file = new File(folder.toString() + denuncia);
                            //fileUri = Uri.fromFile(file);
                            //Log.d("CAMERA_2", file.toString());

                            if (intent.resolveActivity(getPackageManager()) != null) {
                                fileUri = FileProvider.getUriForFile(context, "es.uvigo.esei.cyso.fileprovider", file);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                Log.d("CAMERA_2-URI", fileUri.toString());
                                startActivityForResult(intent, 1111);
                            }


                        } else {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 111);
                        }
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        folder = new File(Environment.getExternalStorageDirectory().toString() + "Denuncias/");
                        Log.d("Camera folder", folder.toString());
                        folder.mkdirs();
                        DateFormat dt = new SimpleDateFormat("ddMMyy_HHmmss");
                        Date date = new Date();
                        file = new File(folder.toString() + "/" + dt.format(date) + id);
                        fileUri = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        startActivityForResult(intent, 1111);
                    }

                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView dir = (TextView) findViewById(R.id.dir);
            dir.setText(extras.getString("direccion"));
            data.addItemIdList("direccion", extras.getString("direccion"));
            TextView sup = (TextView) findViewById(R.id.sup);
            sup.setText(extras.getString("superficie"));
            data.addItemIdList("superficie", extras.getString("superficie"));
            TextView cons = (TextView) findViewById(R.id.cons);
            cons.setText(extras.getString("constructor"));
            data.addItemIdList("constructor", extras.getString("constructor"));
            TextView des = (TextView) findViewById(R.id.des);
            des.setText(extras.getString("descripcion"));
            data.addItemIdList("descripcion", extras.getString("descripcion"));
        }else{

            id = data.getIdList().get("id");
            setTitle(data.getIdList().get("id"));

            iView = findViewById(android.R.id.content);
            context = this;
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Log.d("CAMERA_2", "Foto");
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            Log.d("CAMERA_2", "Foto");
                            folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                            //folder.mkdirs();
                            Log.d("CAMERA_2-Carpeta", folder.toString());
                            DateFormat dt = new SimpleDateFormat("ddMMyy_HHmmss");
                            Date date = new Date();
                            denuncia = dt.format(date) + id;
                            Log.d("CAMERA_2-Denuncia", denuncia);
                            try {
                                file = File.createTempFile(denuncia, ".jpg", folder);
                            } catch (IOException io) {
                            }
                            //file = new File(folder.toString() + denuncia);
                            //fileUri = Uri.fromFile(file);
                            //Log.d("CAMERA_2", file.toString());

                            if (intent.resolveActivity(getPackageManager()) != null) {
                                fileUri = FileProvider.getUriForFile(context, "es.uvigo.esei.cyso.fileprovider", file);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                Log.d("CAMERA_2-URI", fileUri.toString());
                                startActivityForResult(intent, 1111);
                            }


                        } else {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 111);
                        }
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        folder = new File(Environment.getExternalStorageDirectory().toString() + "Denuncias/");
                        folder.mkdirs();
                        DateFormat dt = new SimpleDateFormat("ddMMyy_HHmmss");
                        Date date = new Date();
                        file = new File(folder.toString() + "/" + dt.format(date) + id);
                        fileUri = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        startActivityForResult(intent, 1111);
                    }

                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView dir = (TextView) findViewById(R.id.dir);
            dir.setText(data.getIdList().get("direccion"));
            TextView sup = (TextView) findViewById(R.id.sup);
            sup.setText(data.getIdList().get("superficie"));
            TextView cons = (TextView) findViewById(R.id.cons);
            cons.setText(data.getIdList().get("constructor"));
            TextView des = (TextView) findViewById(R.id.des);
            des.setText(data.getIdList().get("descripcion"));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int btn = item.getItemId();

        if (btn == R.id.permis) {
            pT = new PermissionsTask(id);
            pT.execute();
            //Toast.makeText(getApplicationContext(), "Login option clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO: implemet BD storage
        switch (requestCode){
            case 1111:
                if(resultCode == Activity.RESULT_OK){
                    //Log.d("CAMERA", file.getPath());

                    ImageTask mImgTask = new ImageTask(file);
                    try {
                        mImgTask.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //UploadTask uploadTask = new UploadTask(denuncia, id, file.getPath());
                    //Toast.makeText(getApplicationContext(), file.getPath(), Toast.LENGTH_SHORT).show();

                    Snackbar.make(iView, "Se ha tomado la instantanea", Snackbar.LENGTH_LONG).show();
                    if(exito)
                        startActivityForResult(new Intent(InformationActivity.this, ReportActivity.class), 1112);
                    else
                        Snackbar.make(iView, "Tamaño de la imagen excesivo", Snackbar.LENGTH_LONG).show();
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;
            case 1112:
                if(resultCode == Activity.RESULT_OK){
                    //Log.d("CAMERA", file.getPath());
                    Bundle bnl = data.getExtras();
                    String des = bnl.getString("description");
                    UploadTask mUploadTask = new UploadTask(des, denuncia);
                    try {
                        mUploadTask.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //UploadTask uploadTask = new UploadTask(denuncia, id, file.getPath());
                    //Toast.makeText(getApplicationContext(), file.getPath(), Toast.LENGTH_SHORT).show();
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                    UploadTask mUploadTask = new UploadTask(denuncia);
                    try {
                        mUploadTask.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    private SpannableStringBuilder add(String string){
        SpannableStringBuilder span = new SpannableStringBuilder();
        span.append("     ");
        ImageSpan iS = new ImageSpan(iView.getContext(), fileUri);
        span.setSpan(iS, span.length()-1, span.length(), 0);
        span.append("     ");
        span.append(string);

        return span;
    }


    public class UploadTask  extends AsyncTask<Void, Void, String> {

        private final String description, reportId;
        private boolean cancelled = false;
        URL url = null;
        HttpURLConnection connection;

        UploadTask(String description, String reportId) {
            this.description = description;
            this.reportId = reportId;
        }

        UploadTask(String reportId) {
            this.reportId = reportId;
            this.description = null;
            cancelled = true;
        }

        @Override
        protected String doInBackground(Void... args) {
            try {
                Log.d("INFO", "InformacionActivity");
                url = new URL("http://83.165.253.135/android/add_description.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder;

                if(cancelled == false) {
                    builder = new Uri.Builder().appendQueryParameter("description", description);
                    builder.appendQueryParameter("id", denuncia);
                }else{
                    builder = new Uri.Builder().appendQueryParameter("id", denuncia);
                }
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
                connection.connect();
                // Simulate network access.
                //Thread.sleep(2000);

            }catch (IOException ie){
                Log.d("INFO", ie.toString());
                return "exception";
            }

            try{
                int response_code = connection.getResponseCode();
                Log.d("INFO", "Connection response");
                if (response_code == HttpURLConnection.HTTP_OK){
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
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    Log.d("INFO", result.toString());
                    return result.toString();

                }else{
                    return "unsuccessful";
                }
            }catch (IOException io){
                return "exception";
            }finally {
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
            if(result.length() > 0) {
                mUpTask = null;
                //showProgress(false);
                Log.d("INFO-PostExecute", "Split data");
                if(result.equals("success")){
                    Log.d("INFO-PostExecute", "SUCCESS");
                    Snackbar.make(iView, "Denuncia enviada", Snackbar.LENGTH_LONG).show();
                }
                if (result.equals("no_id")){
                    Log.d("INFO-PostExecute", "NO ID");
                    Snackbar.make(iView, "Se ha producido un error", Snackbar.LENGTH_LONG).show();
                }
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
            */
                /*Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                intent.putExtra("id", data[0]);
                intent.putExtra("direccion", data[1]);
                intent.putExtra("superficie", data[2]);
                intent.putExtra("descripcion", data[3]);
                intent.putExtra("constructor", data[4]);
                startActivity(intent);*/
            }else {
                Snackbar.make(iView, "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mUpTask = null;
            //showProgress(false);
        }
    }
    public class ImageTask  extends AsyncTask<Void, Void, String> {

        URL url = null;
        HttpURLConnection connection;
        private File image;
        private String path, id, den;
        private int bytes, buffSize, read;
        private byte[] buffer;

        ImageTask(File img) {
            this.image = img;
            this.path = image.getPath();
        }

        ImageTask(File img, String id, String den) {
            this.image = img;
            this.path = image.getPath();
            this.id = id;
            this.den = den;
        }

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                FileInputStream fileInputStream = new FileInputStream(new File(this.path));
                Log.d("INFO", "InformacionActivity-Image");
                url = new URL("http://83.165.253.135/android/upload.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);

                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=*****");

                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes("--*****\r\n");
                os.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + this.path +"\"" + "\r\n");
                os.writeBytes("\r\n");

                bytes = fileInputStream.available();
                buffSize = Math.min(bytes, (1024*1024));
                buffer = new byte[buffSize];

                read = fileInputStream.read(buffer, 0, buffSize);

                while(read > 0){
                    os.write(buffer, 0, buffSize);
                    bytes = fileInputStream.available();
                    buffSize = Math.min(bytes, buffSize);
                    read = fileInputStream.read(buffer, 0, buffSize);
                }

                os.writeBytes("\r\n");
                os.writeBytes("--*****--\r\n");

                fileInputStream.close();
                os.flush();
                os.close();
                /*
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("cons", construction);
                builder.appendQueryParameter("file", file.getPath());
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
                connection.connect();
                // Simulate network access.
                //Thread.sleep(2000);
                */
            }catch (IOException ie){
                Log.d("INFO", ie.toString());
                return "exception";
            }

            try{
                int response_code = connection.getResponseCode();
                Log.d("INFO", "Connection response");
                if (response_code == HttpURLConnection.HTTP_OK){
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
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    Log.d("INFO", result.toString());
                    return result.toString();

                }else{
                    return "unsuccessful";
                }
            }catch (IOException io){
                return "exception";
            }finally {
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
            Log.d("POST-UPLOAD", result);
            if(result.length() > 0) {
                if (result.equals("tam_exc"))
                    exito = false;

                if(result.equals("success"))
                    Snackbar.make(iView, "Denuncia enviada", Snackbar.LENGTH_LONG).show();
                else
                    if(result.contains("sql"))
                        Snackbar.make(iView, "No ha sido posible guardar su denuncia", Snackbar.LENGTH_LONG).show();
                    else
                        Snackbar.make(iView, "El archivo no es válido", Snackbar.LENGTH_LONG).show();
                mUpTask = null;
                //showProgress(false);
                //Log.d("INFO-PostExecute", "Split data");
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
            */
                Log.d("INFO-PostExecute", result);
                /*Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                intent.putExtra("id", data[0]);
                intent.putExtra("direccion", data[1]);
                intent.putExtra("superficie", data[2]);
                intent.putExtra("descripcion", data[3]);
                intent.putExtra("constructor", data[4]);
                startActivity(intent);*/
            }else {
                Snackbar.make(iView, "Ha ocurrido un error", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mUpTask = null;
            //showProgress(false);
        }
    }

    public class PermissionsTask extends AsyncTask<Void, Void, String> {

        URL url = null;
        HttpURLConnection connection;
        private String construction, report;
        private boolean called = false;

        PermissionsTask() {
        }

        PermissionsTask(String construction) {
            this.construction = construction;
        }

        public void setConstruccion(String value) {
            this.construction = value;
        }

        public boolean getCalled(){return called;}

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                Log.d("INFO", "ReportInfo");
                Log.d("INFO-data", data.getDni());
                if(data.getDni() == "NC")
                    url = new URL("http://83.165.253.135/android/satisfaction.php");
                else
                    url = new URL("http://83.165.253.135/android/satisfaction_ins_up.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(CONECTION_TIMEOUT);
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder;
                builder = new Uri.Builder().appendQueryParameter("cons", construction);
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
            if(result.equals("vacio")){
                Toast.makeText(context, "Esta construcción no cumple ninguna exigencia en este momento", Toast.LENGTH_SHORT).show();
            }

                if (!result.equals("exception")) {
                    pT = null;
                    //showProgress(false);
                    Log.d("INFO-PostExecute", "Split data");
                    String[] data = result.split(":");
                    String[][] extraArray = new String[data.length][];
                    for (int i = 0; i < data.length; i++) {
                         extraArray[i] = data[i].split("&");
                    }
                    Log.d("INFO-PostExecute", "Data lenght: " + data.length);
                    for (int i = 0; i < data.length; i++)
                        Log.d("INFO-PostExecute", "Dato " + i + " es: " + data[i]);
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
*/
                    ArrayList<String> extra = new ArrayList<>();
                    Intent intent = new Intent(InformationActivity.this, PermissionsActivity.class);


                    for(int i = 0; i < extraArray.length; i++) {
                        for (int j = 0; j < extraArray[i].length; j++) {
                            Log.d("Info-per", extraArray[i][j].toString());
                            extra.add(extraArray[i][j]);
                        }
                    }
                    intent.putExtra("size", extra.size());
                    intent.putExtra("cons", construction);
                    intent.putExtra("array", extra);
                    Log.d("Info-per-extra", extra.toString());
                    called = true;
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
                    //Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
                }

        }

        @Override
        protected void onCancelled() {
            pT = null;
            //showProgress(false);
        }
    }
}
