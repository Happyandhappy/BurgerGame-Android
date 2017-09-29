package com.magmamobile.mmusia.data;

import com.magmamobile.mmusia.MCommon;

public class LanguageBase
{

    public static String BEFOREEXIT_BTNCLOSE;
    public static String BEFOREEXIT_CHKDONTSHOW;
    public static String DIALOG_LOADING;
    public static String DIALOG_MOREGAMES_TITLE;
    public static String DIALOG_PLEASEWAIT;
    public static String DIALOG_SETTINGS_CHKTEXT;
    public static String DIALOG_SETTINGS_CLOSE;
    public static String DIALOG_SETTINGS_TITLE;
    public static String DIALOG_UPDATE_MESSAGE;
    public static String DIALOG_UPDATE_NO;
    public static String DIALOG_UPDATE_TITLE;
    public static String DIALOG_UPDATE_YES;
    public static String LatestLang;
    public static String MARKET_NOT_FOUND;
    public static String MENU_QUIT;
    public static String MENU_REFRESH;
    public static String MENU_SETTINGS;
    public static String NOTIF_NEWS_DESC;
    public static String NOTIF_NEWS_TITLE;
    public static String NOTIF_UPDATE_DESC;
    public static String NOTIF_UPDATE_TITLE;
    public static String TAB_NEWS;
    public static String TAB_UPDATES;

    public LanguageBase()
    {
        setLanguageEN();
    }

    public static void reloadIfNeeded()
    {
        if(MENU_QUIT == null)
        {
            MCommon.Log_w("RELOADING LANGUAGE STRINGS !!!");
            setLanguageEN();
        }
    }

    public static void setLanguageEN()
    {
        MENU_QUIT = "Close";
        MENU_SETTINGS = "Settings";
        MENU_REFRESH = "Refresh";
        DIALOG_LOADING = "Loading...";
        DIALOG_PLEASEWAIT = "Please wait...";
        DIALOG_SETTINGS_TITLE = "Settings";
        DIALOG_SETTINGS_CHKTEXT = "Enable notifications";
        DIALOG_SETTINGS_CLOSE = "Close";
        TAB_UPDATES = "Updates";
        TAB_NEWS = "Magma News";
        NOTIF_UPDATE_TITLE = "Update available";
        NOTIF_UPDATE_DESC = "A new update is available";
        NOTIF_NEWS_TITLE = "Magma Mobile News";
        NOTIF_NEWS_DESC = "Click here to see latest news";
        MARKET_NOT_FOUND = "Market not found";
        DIALOG_UPDATE_TITLE = "New version available";
        DIALOG_UPDATE_MESSAGE = "A newer version is available in the Android Market, would you like to update it " +
"?"
;
        DIALOG_UPDATE_YES = "Yes";
        DIALOG_UPDATE_NO = "No";
        DIALOG_MOREGAMES_TITLE = "Our other Games & Apps";
        BEFOREEXIT_BTNCLOSE = "Close";
        BEFOREEXIT_CHKDONTSHOW = "Do not show this again";
    }

    public static void setLanguageFR()
    {
        setLanguageEN();
        MENU_QUIT = "Fermer";
        MENU_SETTINGS = "Options";
        MENU_REFRESH = "Actualiser";
        DIALOG_PLEASEWAIT = "Veuillez patienter...";
        DIALOG_LOADING = "Chargement...";
        DIALOG_SETTINGS_TITLE = "Options";
        DIALOG_SETTINGS_CHKTEXT = "Activer les notifications";
        DIALOG_SETTINGS_CLOSE = "Fermer";
        TAB_UPDATES = "Mises \340 jour";
        TAB_NEWS = "Actus Magma Mobile";
        NOTIF_UPDATE_TITLE = "Mise \340 jour disponible";
        NOTIF_UPDATE_DESC = "Une mise \340 jour est disponible";
        NOTIF_NEWS_TITLE = "Magma Mobile Actu";
        NOTIF_NEWS_DESC = "Nouvelle actu Magma Mobile. Cliquez ici pour la consulter";
        MARKET_NOT_FOUND = "Market introuvable";
        DIALOG_UPDATE_TITLE = "Nouvelle version disponible";
        DIALOG_UPDATE_MESSAGE = "Une nouvelle version est disponible dans le Market Android. Voulez-vous mettre \340" +
" jour maintenant ?"
;
        DIALOG_UPDATE_YES = "Oui";
        DIALOG_UPDATE_NO = "Non";
        DIALOG_MOREGAMES_TITLE = "Nos autres Jeux & Apps";
        BEFOREEXIT_BTNCLOSE = "Fermer";
        BEFOREEXIT_CHKDONTSHOW = "Ne plus afficher";
    }
}
