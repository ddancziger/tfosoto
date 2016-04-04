package com.ddancziger.tfosoto.tfosoto;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class threadsSMSActivity extends BaseActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads_sms);

        ContentResolver contentResolver = getContentResolver();
        Cursor cur = null;
        Uri uri = Uri.parse("content://sms/");
        cur = getApplicationContext().getApplicationContext().getContentResolver().query(uri, null, null, null, null);

        final Map<Long,List<DataSMS>> threadsSMS = new HashMap<>();

        // TODO: check for error and return bool from the function!
        if(cur.moveToFirst()) {
            while (cur.moveToNext()) {

                String address = cur.getString(cur.getColumnIndex("address"));
                Date date = new Date(cur.getLong(cur.getColumnIndexOrThrow(Telephony.Sms.DATE)));
                String body = cur.getString(cur.getColumnIndexOrThrow("body"));
                Long threadId = cur.getLong(cur.getColumnIndexOrThrow("thread_id"));
                Long messageID = cur.getLong(cur.getColumnIndexOrThrow("_id"));
                String smsDirection = cur.getString(cur.getColumnIndexOrThrow(Telephony.Sms.TYPE));

                DataSMS sms = new DataSMS(address,date,body,threadId,messageID,smsDirection);
                if(!threadsSMS.containsKey(threadId)){
                    threadsSMS.put(threadId, new ArrayList<DataSMS>());
                }
                threadsSMS.get(threadId).add(sms);

            }
            Log.d("SMS CONVERSATION", "Count threads: " + threadsSMS.size());

            final List<Map.Entry<Long,List<DataSMS>>> entries =
                    new ArrayList<Map.Entry<Long,List<DataSMS>>>(threadsSMS.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<Long, List<DataSMS>>>() {

                public int compareKeys(final Long o1, final Long o2) {
                    int result;
                    if (o1 > o2) {
                        result = 1;
                    } else if (o1 < o2) {
                        result = -1;
                    } else {
                        result = 0;
                    }
                    return result;
                }

                @Override
                public int compare(final Map.Entry<Long, List<DataSMS>> o1,
                                   final Map.Entry<Long, List<DataSMS>> o2) {
                    return this.compareKeys(o1.getKey(), o2.getKey());
                }

            });

            // Obtener el Recycler
            recycler = (RecyclerView) findViewById(R.id.rvThreadsSMS);
            recycler.setHasFixedSize(true);

            // Usar un administrador para LinearLayout
            lManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(lManager);

            // Crear un nuevo adaptador
            adapter = new ThreadsRVAdapter(entries, new ThreadsRVAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Map.Entry<Long, List<DataSMS>> item) {

                }
            });
            recycler.setAdapter(adapter);
        }
    }

}
