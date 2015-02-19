package com.example.jon.someme.dataAccess;

/**
 * Created by Jon on 2/15/2015.
 */
public class URLS {
    public static final String host = "192.168.0.12:80";
//    public static final String host = "192.168.2.11:80";//ian

    public static final String memeList = "http://"+host+"/SoMeme/data/memeListData.php";
    public static final String memeView = "http://"+host+"/SoMeme/data/memeViewData.php";
    public static final String userProfile = "http://"+host+"/SoMeme/data/userProfileData.php";
    public static final String vote = "http://"+host+"/SoMeme/data/vote.php";
    public static final String comment = "http://"+host+"/SoMeme/data/postComment.php";
    public static final String authenticate = "http://"+host+"SoMeme/data/authenticate.php";
    public static final String register = "http://"+host+"SoMeme/data/CreateAccount.php";
    public static final String updateFavorite = "http://"+host+"SoMeme/data/updateFavorite.php";
    public static final String TAG_SUCCESS = "success";
}
