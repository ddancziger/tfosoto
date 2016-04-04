package com.ddancziger.tfosoto.tfosoto;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Created by DanielDancziger on 4/1/2016.
 */
public class ParserSMS {

    public List<DataSMS> mSms;
    private Map<Long,List<DataSMS>> threads;
    //    protected Set<String> mRemoveWords;
    protected Vector<Long> mSmsThreadId;

    protected Integer mNumOfServerMessages;
    protected Integer mNumOfClientMessages;

    protected AppCompatActivity mActivity;


    public ParserSMS(AppCompatActivity activity)
    {
        mActivity = activity;
        mSms = new ArrayList<DataSMS>();
        mSmsThreadId = new Vector<Long>();
//        mRemoveWords = new HashSet<String>(Arrays.asList(mActivity.getResources().getStringArray(R.array.removeWordsArray)));
        mNumOfServerMessages = 0;
        mNumOfClientMessages = 0;
    }






    public void InitializeSpecificThreadIdInboxSms(ContentResolver contentResolver, Long threadId) {
        AddInboxSms(contentResolver, Telephony.Sms.Inbox.CONTENT_URI, "thread_id = " + threadId, true, DataSMS.DirectionSMS.toClient);
        AddInboxSms(contentResolver, Telephony.Sms.Sent.CONTENT_URI, "thread_id = " + threadId, true, DataSMS.DirectionSMS.toServer);
        Collections.sort(mSms);
        Collections.reverse(mSms);
    }

    public void getAllThreadsSMS(ContentResolver cr){

    }
    public void InitializeAllInboxSms(ContentResolver contentResolver) {
        AddInboxSms(contentResolver, Telephony.Sms.Conversations.CONTENT_URI, null, false, DataSMS.DirectionSMS.toClient);
    }

    public void AddInboxSms(ContentResolver contentResolver, Uri smsUri, String condition, boolean allowThreadIdDuplications, DataSMS.DirectionSMS direction) {

        Cursor cur = contentResolver.query(smsUri, null, condition, null, null);
        // TODO: check for error and return bool from the function!
        HashSet<Long> threadIdsSeen = new HashSet<Long>();
        while (cur.moveToNext()) {

            String address = cur.getString(cur.getColumnIndex("address"));
            Date date = new Date(cur.getLong(cur.getColumnIndex("date")));
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));
            Long threadId = cur.getLong(cur.getColumnIndexOrThrow("thread_id"));
            Long messageID = cur.getLong(cur.getColumnIndexOrThrow("_id"));
            String smsDirection = "aa";
            DataSMS smsDetails = new DataSMS(address,date,body,threadId,messageID,smsDirection);

            if (!allowThreadIdDuplications)
            {
                if (threadIdsSeen.contains(smsDetails.getmThreadId()))
                {
                    continue;
                }
                threadIdsSeen.add(smsDetails.getmThreadId());
            }
            mSms.add(smsDetails);
            mSmsThreadId.add(smsDetails.getmThreadId());
            if (direction == DataSMS.DirectionSMS.toClient)
            {
                mNumOfServerMessages++;
            }
            else
            {
                mNumOfClientMessages++;
            }
        }
    }


    // TODO: use it!
    public Integer GetNumberOfMessagesFromServerAfterRemoveMessageWasSent()
    {
        Set<String> removeWordsSeenInServer = new HashSet<String>();
        Boolean removeFound = false;
        Integer numOfServerMessagesAfterRemove = 0;
        for (int i = mSms.size() - 1; i >= 0; --i) { // need to search backwards because we sort reverse

            if (removeFound)
            {
                if (mSms.get(i).getmSmsDirection() == "client")
                {
                    numOfServerMessagesAfterRemove++;
                }
                continue;
            }

            if (mSms.get(i).getmSmsDirection() == "client")
            {
//                for (String s : mRemoveWords) {
//                    if (mSms.get(i).mBody.contains(s))
//                    {
//                        removeWordsSeenInServer.add(s);
//                    }
//                }
            }
            else
            {
                for (String s : removeWordsSeenInServer) {
                    if (mSms.get(i).getmBody().contains(s))
                    {
                        removeFound = true;
                    }
                }
            }
        }

        if (removeFound)
        {
            return numOfServerMessagesAfterRemove;
        }
        else
        {
            if (removeWordsSeenInServer.size() > 0)
            {
                return 0;
            }
            return mNumOfServerMessages;
        }

    }

    public Integer GetNumOfServerMessages()
    {
        return mNumOfServerMessages;
    }


    public Long GetThreadId(int smsLocation)
    {
        return mSmsThreadId.elementAt(smsLocation);
    }
}
