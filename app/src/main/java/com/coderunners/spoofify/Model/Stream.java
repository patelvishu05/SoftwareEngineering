package com.coderunners.spoofify.Model;

import com.coderunners.spoofify.R;

public class Stream
{
    private static Stream streamInstance;

    private Boolean hasUrl;
    private String URL;
    private String streamName;
    private int albumArt;

    private Stream()
    {
        hasUrl = false;
        streamName = "No Stream Selected";
        albumArt = (R.drawable.default_album_art);
    }

    public static Stream getStreamInstance()
    {
        if(streamInstance == null)
        {
            streamInstance = new Stream();
        }

        return streamInstance;
    }

    public int getAlbumArt() {
        return albumArt;
    }

    public Boolean hasUrl() {
        return hasUrl;
    }

    public void setHasUrl(Boolean hasUrl) {
        this.hasUrl = hasUrl;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String streamExt)
    {
        this.URL = "http://10.100.118.102:8000/" + streamExt;
        this.hasUrl = true;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName)
    {
        this.streamName = streamName;
        setAlbumArt();
    }

    private void setAlbumArt()
    {
        switch(streamName.toLowerCase()) {
            case "muse":
                albumArt = (R.drawable.album_muse);
                break;
            case "red hot chili peppers":
                albumArt = (R.drawable.album_rhcp);
                break;
            case "elvis presley":
                albumArt = (R.drawable.album_elvis);
                break;
            case "jimi hendrix":
                albumArt = (R.drawable.album_hendrix);
                break;
            case "gaming":
                albumArt = (R.drawable.album_nintendo);
                break;
            case "tom petty & the heartbreakers":
                albumArt = (R.drawable.album_petty);
                break;
            default:
                albumArt = (R.drawable.default_album_art);
                break;
        }
    }




}
