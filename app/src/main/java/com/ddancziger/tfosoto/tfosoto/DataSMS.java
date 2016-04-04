package com.ddancziger.tfosoto.tfosoto;

import java.util.Date;

/**
 * Created by DanielDancziger on 4/2/2016.
 */
public class DataSMS implements Comparable<DataSMS>
{
    public static enum DirectionSMS{
        toClient,
        toServer
    }

    private String mAddress;
    private Date mDate;
    private String mBody;
    private Long mThreadId;
    private Long mId;
    private String mSmsDirection;

    public DataSMS(String mAddress, Date mDate, String mBody, Long mThreadId, Long mId, String mSmsDirection) {
        this.mAddress = mAddress;
        this.mDate = mDate;
        this.mBody = mBody;
        this.mThreadId = mThreadId;
        this.mId = mId;
        this.mSmsDirection = mSmsDirection;
    }

    @Override
    public int compareTo(DataSMS other) {
        if (this.mId < other.mId)
        {
            return -1;
        }
        else if (this.mId > other.mId)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public String toString()
    {
        String messageFormat = new String();
        messageFormat += "Sender: " + mAddress;
        messageFormat += "\nDate: " + mDate.toString();
        messageFormat += "\nThread_id: " + mThreadId.toString();
        messageFormat += "\nId: " + mId.toString();
        messageFormat += "\nFrom: " + (mSmsDirection);
        messageFormat += "\n" + mBody;
        return messageFormat;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public Long getmThreadId() {
        return mThreadId;
    }

    public void setmThreadId(Long mThreadId) {
        this.mThreadId = mThreadId;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmSmsDirection() {
        return mSmsDirection;
    }

    public void setmSmsDirection(String mSmsDirection) {
        this.mSmsDirection = mSmsDirection;
    }



}
