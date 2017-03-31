package com.example.timelapse;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class CaptureSettings extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();

    public static final String MyPREFERENCES = "MyPrefs" ;

    private Map<String, String> times = new HashMap<>();
    private String[] ordtimes = new String[4];
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_settings);
        times.put("days","86400");
        ordtimes[3] = "days";
        times.put("hours","3600");
        ordtimes[2] = "hours";
        times.put("minutes","60");
        ordtimes[1] = "minutes";
        times.put("seconds","1");
        ordtimes[0] = "seconds";
        EditText et = (EditText)findViewById(R.id.smallinterval);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //sharedpreferences = this.getPreferences(Context.MODE_PRIVATE);
        et.setText(sharedpreferences.getString("sval","10"));




    }

    @Override
    protected void onResume(){
        super.onResume();
        final Spinner s = (Spinner)findViewById(R.id.biginterval);
        //String[] btime = new String[1];
        //btime = times.keySet().toArray(btime);
        String[] btime = ordtimes;
        sharedpreferences = this.getPreferences(Context.MODE_PRIVATE);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, btime);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(spinnerArrayAdapter);
        s.setOnItemSelectedListener(new timesListener());
        s.setSelection(Integer.valueOf(sharedpreferences.getString("bval","0")));

    }

    public void save(View v){
        Spinner spin = (Spinner)findViewById(R.id.biginterval);
        EditText et = (EditText)findViewById(R.id.smallinterval);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("sval", et.getText().toString());
        editor.putString("bval", Integer.toString(spin.getSelectedItemPosition()));
        editor.putString("bval2", times.get(ordtimes[spin.getSelectedItemPosition()]));
        editor.commit();

        int a = spin.getSelectedItemPosition();
        Log.d(TAG, Integer.toString(a));



    }

    class timesListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
        {
            EditText et = (EditText)findViewById(R.id.smallinterval);
            String sval = et.getText().toString();
            TextView itemTextView =(TextView) view;
            String bval = times.get(itemTextView.getText());
            float actualtime = Float.valueOf(bval) * Float.valueOf(sval);
            String atime = Float.toString(actualtime);
            Log.d(TAG, atime);



        }
        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }



    }

}
