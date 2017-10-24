package es.uvigo.esei.cyso;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static es.uvigo.esei.cyso.MainActivity.CONECTION_TIMEOUT;
import static es.uvigo.esei.cyso.SavedData.data;

public class PermissionsActivity extends AppCompatActivity {

    private ArrayList<String> id;
    private ArrayList<ArrayList<String>> cambios;
    private View PerV;
    private String construc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PerV = findViewById(R.id.card_container);
        Bundle extras = getIntent().getExtras();
        ArrayList<ArrayList<String>> toAdapt = new ArrayList<>();
        int tam = extras.getInt("size");
        construc = extras.getString("cons");
        Log.d("Construccion", construc);
        Log.d("Tamaño", Integer.toString(tam));
        int init = 0;
        ArrayList<String> aux;

        id = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_container);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //cambios = new ArrayList<>();
        if(extras.get("cambios") == null) {
            aux = extras.getStringArrayList("array");
            cambios = toAdapt;

            if (data.getDni().equals("NC")) {
                Log.d("Permi-1", aux.get(0).toString());
                Log.d("Permi-2", aux.get(1).toString());
                Log.d("Permi-3", aux.get(2).toString());
                while (init < tam) {

                    ArrayList<String> aux2 = new ArrayList<>();

                    for (int j = init; j < init + 3; j++) {
                        aux2.add(aux.get(j));
                    }
                    Log.d("Permi", aux2.toString());
                    toAdapt.add(aux2);
                    init += 3;
                }
            } else {
                Log.d("Permi-1", aux.get(0).toString());
                Log.d("Permi-2", aux.get(1).toString());
                Log.d("Permi-3", aux.get(2).toString());
                Log.d("Permi-4", aux.get(3).toString());
                while (init < tam) {

                    ArrayList<String> aux2 = new ArrayList<>();

                    for (int j = init; j < init + 4; j++) {
                        aux2.add(aux.get(j));
                    }
                    Log.d("Permi", aux2.toString());
                    toAdapt.add(aux2);
                    init += 4;
                }
            }

            Log.d("Permissions", Integer.toString(toAdapt.size()));
            Log.d("Permissions", toAdapt.toString());


            ListAdapter adp = new ListAdapter(toAdapt);
            recyclerView.setAdapter(adp);
        }else{
            cambios = (ArrayList<ArrayList<String>>) extras.get("cambios");
            ListAdapter adp = new ListAdapter(cambios);
            recyclerView.setAdapter(adp);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(!data.getDni().equals("NC")) {
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Cosas tam", Integer.toString(recyclerView.getAdapter().getItemCount()));
                    /*for (int childCount = recyclerView.getAdapter().getItemCount(), i = 0; i < childCount; ++i) {

                        final ListAdapter.ListViewHolder holder = (ListAdapter.ListViewHolder) recyclerView.getChildViewHolder(i);

                        ArrayList<String> aux = new ArrayList<>();
                        aux.add(id.get(i));
                        aux.add(holder.nota.getText().toString());
                        aux.add(Integer.toString(holder.getChecked()));
                        Log.d("AUX", aux.toString());
                        cambios.add(aux);
                        Log.d("Cosas", cambios.toString());
                    }*/
                    SavePermissionsTask saveP = new SavePermissionsTask();
                    try {
                        saveP.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Intent refresh = getIntent();
                    refresh.putExtra("cambios", cambios);
                    finish();
                    startActivity(refresh);
                }
            });
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

        private ArrayList<ArrayList<String>> list;
        private ArrayList<ListViewHolder> myHolders = new ArrayList<>();
        public ListAdapter(ArrayList<ArrayList<String>> a){
            list = a;
        }
        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.permissions_items, parent, false);
            ListViewHolder lv = new ListViewHolder(v);
            return lv;
        }

        public ListViewHolder getHolder(int pos){
            return myHolders.get(pos);
        }

        @Override
        public void onBindViewHolder(final ListViewHolder holder, final int position) {
            myHolders.add(holder);
            if(data.getDni().equals("NC")) {
                holder.identification.setText(list.get(position).get(0) + " - " + list.get(position).get(1));
                holder.description.setText(list.get(position).get(2));
            }else{
                id.add(list.get(position).get(0));
                holder.identification.setText(list.get(position).get(0) + " - " + list.get(position).get(1));
                if(!list.get(position).get(3).equals("nada")) {
                    holder.nota.setText(list.get(position).get(3));
                }
                if(list.get(position).get(2).equals("1"))
                    holder.cumplimiento.setChecked(true);
                else
                    holder.cumplimiento.setChecked(false);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ListViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView identification;
            TextView description;
            MultiAutoCompleteTextView nota;
            Switch cumplimiento;

            private int checked;

            ListViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card);
                identification = (TextView)itemView.findViewById(R.id.id_perm);
                description = (TextView)itemView.findViewById(R.id.des_perm);
                cumplimiento = (Switch)itemView.findViewById(R.id.switch1);
                nota = (MultiAutoCompleteTextView)itemView.findViewById(R.id.nota);
                nota.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            if(nota.getText().equals(""))
                                Toast.makeText(getBaseContext(), "Hey loco", Toast.LENGTH_SHORT).show();
                            else {
                                String[] splited = identification.getText().toString().split("-");
                                Log.d("Identificador texto", splited[0].trim());
                                //Log.d("Posicion Adpter", Integer.toString(list.indexOf(splited[0].trim())));
                                int posicion = -1;
                                boolean ex = false;
                                while(posicion < list.size() && ex == false) {
                                    posicion++;
                                    Log.d("Lista " + posicion, list.get(posicion).get(0));
                                    if(list.get(posicion).get(0).equals(splited[0].trim())){
                                        list.get(posicion).set(3, nota.getText().toString());
                                        ex = true;
                                    }
                                }
                                Log.d("Adapter changing", list.get(posicion).toString());
                                cambios.set(posicion, list.get(posicion));
                                Log.d("Cambios changing", cambios.get(posicion).toString());
                            }
                        }
                    }
                });
                nota.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if(keyCode == KeyEvent.KEYCODE_ENTER)
                            nota.clearFocus();
                            InputMethodManager manager = (InputMethodManager) v.getContext()
                                .getSystemService(INPUT_METHOD_SERVICE);
                            if (manager != null)
                                manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        return false;
                    }
                });
                if(!data.getDni().equals("NC")) {
                    cumplimiento.setVisibility(View.VISIBLE);
                    cumplimiento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked) {
                                checked = 1;
                                for(int i = 0; i< list.size(); i++){
                                    Log.d("LISTA", list.get(i).get(0).toString());
                                    Log.d("IDENTIFICACION", identification.getText().toString());
                                    if(identification.getText().toString().contains(list.get(i).get(0))) {
                                        list.get(i).set(2, Integer.toString(checked));
                                        cambios.set(i, list.get(i));
                                        Log.d("CAMBUIOS", cambios.get(i).toString());
                                    }
                                }
                                Log.d("CHECK", Integer.toString(checked));
                            }
                            else {
                                checked = 0;
                                for(int i = 0; i< list.size(); i++){
                                    if(identification.getText().toString().contains(list.get(i).get(0))) {
                                        list.get(i).set(2, Integer.toString(checked));
                                        cambios.set(i, list.get(i));
                                        Log.d("CAMBUIOS", cambios.get(i).toString());
                                    }
                                }
                                Log.d("CHECK", Integer.toString(checked));
                            }

                        }
                    });
                    nota.setVisibility(View.VISIBLE);
                    description.setVisibility(View.GONE);
                }
            }


            public int getChecked(){
                return checked;
            }

            public ArrayList<String> getItems(){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(identification.getText().toString());
                arrayList.add(nota.getText().toString());
                arrayList.add(Integer.toString(getChecked()));

                return arrayList;
            }
        }

    }

    public class SavePermissionsTask extends AsyncTask<Void, Void, String> {

        URL url = null;
        HttpURLConnection connection;
        private String construction, report;
        private boolean called = false;

        SavePermissionsTask() {
        }

        SavePermissionsTask(String construction) {
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
                url = new URL("http://83.165.253.135/android/satisfaction_save.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(CONECTION_TIMEOUT);
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder;
                builder = new Uri.Builder().appendQueryParameter("exigencias", cambios.toString());
                builder.appendQueryParameter("constr", construc);
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
                Toast.makeText(getApplicationContext(), "Nada por aquí", Toast.LENGTH_SHORT).show();
            }

            if (!result.equals("exception")) {
                if(result.contains("exito")){
                    Log.d("EXITO", result);
                    Snackbar.make(PerV, "Datos actualizados", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(PerV, "Ha ocurrido un error", Snackbar.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
                //Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onCancelled() {
            //pT = null;
            //showProgress(false);
        }
    }

}
