package com.example.khaledsabry.entertainment;

import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Controller;

public class TorrentAvailableWebController extends Controller {

    private WebApi webApi;

    //Quality
    private final String qWebRip = "";
    private final String qBluRay = "";
    private final String qDvdRip = "";
    private final String qCam = "";

    //Language
    private final String lEnglish = "";
    private final String lFrench = "";
    private final String lSpanish = "";
    private final String lHindi = "";

    //Genres
    private final String gAction = "";
    private final String gAdventure = "";
    private final String gAnimation= "";
    private final String gBiography = "";
    private final String gComedy = "";
    private final String gCrime = "";
    private final String gDrama = "";
    private final String gFamily = "";
    private final String gFantasy = "";
    private final String gHistory = "";
    private final String gHorror = "";
    private final String gMusic = "";
    private final String gMusical = "";
    private final String gMystery = "";
    private final String gNews = "";
    private final String gRomance = "";
    private final String gSciFi = "";
    private final String gShort = "";
    private final String gSport = "";
    private final String gThriller = "";
    private final String gWar = "";

    //Resolution
    private final String rSd = "";
    private final String rHd = "";
    private final String rFhd = "";

    //Format

    public TorrentAvailableWebController() {
        webApi = WebApi.getInstance();
    }


}
