package com.magmamobile.game.Burger.managers;

import android.graphics.Bitmap;
import com.magmamobile.game.engine.Game;

public final class BitmapManager
{

    public static Bitmap accompItems[];
    public static Bitmap arrowD;
    public static Bitmap arrowL;
    public static Bitmap arrowR;
    public static Bitmap arrowU;
    public static Bitmap background;
    public static Bitmap boardItems[];
    public static Bitmap boxes;
    public static Bitmap burger;
    public static Bitmap burgerParts[];
    public static Bitmap calendarArrow[];
    public static Bitmap calendarBack;
    public static Bitmap calendarCross;
    public static Bitmap calendarCross2;
    public static Bitmap calendarFlag;
    public static Bitmap calendarLeft;
    public static Bitmap calendarLeft2;
    public static Bitmap calendarLock;
    public static Bitmap calendarM;
    public static Bitmap calendarRight;
    public static Bitmap calendarRight2;
    public static Bitmap calendarRound;
    public static Bitmap calendarRound2;
    public static Bitmap clockBG1;
    public static Bitmap clockBG2;
    public static Bitmap clockDots;
    public static Bitmap clockHammer;
    public static Bitmap clockHour;
    public static Bitmap clockMin;
    public static Bitmap clockNum[];
    public static Bitmap cocheOff;
    public static Bitmap cocheOn;
    public static Bitmap coin[];
    private static int currentBackground = -1;
    public static Bitmap flashItems[];
    public static Bitmap galleryButtons[];
    public static Bitmap galleryOptions[];
    public static Bitmap goodJobArrow;
    public static Bitmap goodJobArrowOn;
    public static Bitmap goodJobBest;
    public static Bitmap goodJobGoal;
    public static Bitmap goodJobIcons[];
    public static Bitmap goodJobIncome;
    public static Bitmap goodJobM[];
    public static Bitmap goodJobReject;
    public static Bitmap goodJobTips;
    public static Bitmap goodJobTotal;
    public static Bitmap goodJobValid;
    public static boolean inited;
    public static Bitmap logo;
    public static Bitmap mIntro;
    public static Bitmap menu;
    public static Bitmap menuBack;
    public static Bitmap menuCoche;
    public static Bitmap menuItems[];
    public static Bitmap menuR;
    public static Bitmap menuTimer;
    public static Bitmap menus[];
    public static Bitmap modeIcons[];
    public static Bitmap modeIconsOff[];
    public static Bitmap modeIconsOn[];
    public static Bitmap newItemNext;
    public static Bitmap newItemNextOn;
    public static Bitmap optionsDown[];
    public static Bitmap optionsUp[];
    public static Bitmap persoIntro;
    public static Bitmap photos[];
    public static Bitmap plate;
    public static Bitmap prefIcons[];
    public static Bitmap scrollTiles[];
    public static Bitmap serviceBell;
    public static Bitmap serviceButton;
    public static Bitmap snapDown;
    public static Bitmap snapUp;
    public static Bitmap successBottom;
    public static Bitmap successIcon[];
    public static Bitmap successLock;
    public static Bitmap successSeparator;
    public static Bitmap successTop;
    public static Bitmap successValid;
    public static Bitmap tiledBG;
    public static Bitmap trash;
    public static Bitmap tray;
    public static Bitmap undoBox;
    public static Bitmap undoButton;

    public BitmapManager()
    {
    }

    public static void createPhotos()
    {
        int i = (int)(0.8F * (float)GalleryManager.PHOTO_W);
        int j = (int)(0.8F * (float)GalleryManager.PHOTO_H);
        int k = 0;
        do
        {
            if(k >= 3)
            {
                return;
            }
            if(photos[k] == null || photos[k].isRecycled())
            {
                photos[k] = Game.createBitmap(i, j);
            }
            k++;
        } while(true);
    }

    public static void init()
    {
        boxes = Game.loadBitmap(1);
        Bitmap abitmap[] = new Bitmap[4];
        abitmap[0] = Game.loadBitmap(4);
        abitmap[1] = Game.loadBitmap(5);
        abitmap[2] = Game.loadBitmap(6);
        abitmap[3] = Game.loadBitmap(7);
        scrollTiles = abitmap;
        int i = 8 * scrollTiles[0].getWidth();
        tiledBG = Game.createBitmap(i, i);
        Bitmap abitmap1[] = new Bitmap[7];
        abitmap1[0] = Game.loadBitmap(176);
        abitmap1[1] = Game.loadBitmap(178);
        abitmap1[2] = Game.loadBitmap(180);
        abitmap1[3] = Game.loadBitmap(182);
        abitmap1[4] = Game.loadBitmap(184);
        abitmap1[5] = Game.loadBitmap(186);
        abitmap1[6] = Game.loadBitmap(188);
        optionsUp = abitmap1;
        Bitmap abitmap2[] = new Bitmap[7];
        abitmap2[0] = Game.loadBitmap(177);
        abitmap2[1] = Game.loadBitmap(179);
        abitmap2[2] = Game.loadBitmap(181);
        abitmap2[3] = Game.loadBitmap(183);
        abitmap2[4] = Game.loadBitmap(185);
        abitmap2[5] = Game.loadBitmap(187);
        abitmap2[6] = Game.loadBitmap(189);
        optionsDown = abitmap2;
        logo = Game.loadBitmap(118);
        persoIntro = Game.loadBitmap(117);
        cocheOn = Game.loadBitmap(136);
        cocheOff = Game.loadBitmap(135);
        Bitmap abitmap3[] = new Bitmap[4];
        abitmap3[0] = Game.loadBitmap(138);
        abitmap3[1] = Game.loadBitmap(137);
        abitmap3[2] = Game.loadBitmap(139);
        abitmap3[3] = Game.loadBitmap(134);
        prefIcons = abitmap3;
        Bitmap abitmap4[] = new Bitmap[3];
        abitmap4[0] = Game.loadBitmap(166);
        abitmap4[1] = Game.loadBitmap(168);
        abitmap4[2] = Game.loadBitmap(171);
        modeIcons = abitmap4;
        Bitmap abitmap5[] = new Bitmap[3];
        abitmap5[0] = Game.loadBitmap(167);
        abitmap5[1] = Game.loadBitmap(170);
        abitmap5[2] = Game.loadBitmap(173);
        modeIconsOn = abitmap5;
        Bitmap abitmap6[] = new Bitmap[3];
        abitmap6[1] = Game.loadBitmap(169);
        abitmap6[2] = Game.loadBitmap(172);
        modeIconsOff = abitmap6;
        calendarRight = Game.loadBitmap(130);
        calendarLeft = Game.loadBitmap(126);
        calendarRight2 = Game.loadBitmap(131);
        calendarLeft2 = Game.loadBitmap(127);
        calendarBack = Game.loadBitmap(122);
        calendarCross = Game.loadBitmap(123);
        calendarRound = Game.loadBitmap(132);
        calendarCross2 = Game.loadBitmap(124);
        calendarRound2 = Game.loadBitmap(133);
        calendarLock = Game.loadBitmap(128);
        calendarFlag = Game.loadBitmap(125);
        calendarM = Game.loadBitmap(129);
        Bitmap abitmap7[] = new Bitmap[3];
        abitmap7[0] = Game.loadBitmap(121);
        abitmap7[1] = Game.loadBitmap(119);
        abitmap7[2] = Game.loadBitmap(120);
        calendarArrow = abitmap7;
        successLock = Game.loadBitmap(233);
        successValid = Game.loadBitmap(236);
        successSeparator = Game.loadBitmap(234);
        successTop = Game.loadBitmap(235);
        successBottom = Game.loadBitmap(232);
        Bitmap abitmap8[] = new Bitmap[42];
        abitmap8[0] = Game.loadBitmap(190);
        abitmap8[1] = Game.loadBitmap(191);
        abitmap8[2] = Game.loadBitmap(192);
        abitmap8[3] = Game.loadBitmap(193);
        abitmap8[4] = Game.loadBitmap(194);
        abitmap8[5] = Game.loadBitmap(195);
        abitmap8[6] = Game.loadBitmap(196);
        abitmap8[7] = Game.loadBitmap(197);
        abitmap8[8] = Game.loadBitmap(198);
        abitmap8[9] = Game.loadBitmap(199);
        abitmap8[10] = Game.loadBitmap(200);
        abitmap8[11] = Game.loadBitmap(201);
        abitmap8[12] = Game.loadBitmap(202);
        abitmap8[13] = Game.loadBitmap(203);
        abitmap8[14] = Game.loadBitmap(204);
        abitmap8[15] = Game.loadBitmap(205);
        abitmap8[16] = Game.loadBitmap(206);
        abitmap8[17] = Game.loadBitmap(207);
        abitmap8[18] = Game.loadBitmap(208);
        abitmap8[19] = Game.loadBitmap(209);
        abitmap8[20] = Game.loadBitmap(210);
        abitmap8[21] = Game.loadBitmap(211);
        abitmap8[22] = Game.loadBitmap(212);
        abitmap8[23] = Game.loadBitmap(213);
        abitmap8[24] = Game.loadBitmap(214);
        abitmap8[25] = Game.loadBitmap(215);
        abitmap8[26] = Game.loadBitmap(216);
        abitmap8[27] = Game.loadBitmap(217);
        abitmap8[28] = Game.loadBitmap(218);
        abitmap8[29] = Game.loadBitmap(219);
        abitmap8[30] = Game.loadBitmap(220);
        abitmap8[31] = Game.loadBitmap(221);
        abitmap8[32] = Game.loadBitmap(222);
        abitmap8[33] = Game.loadBitmap(223);
        abitmap8[34] = Game.loadBitmap(224);
        abitmap8[35] = Game.loadBitmap(225);
        abitmap8[36] = Game.loadBitmap(226);
        abitmap8[37] = Game.loadBitmap(227);
        abitmap8[38] = Game.loadBitmap(228);
        abitmap8[39] = Game.loadBitmap(229);
        abitmap8[40] = Game.loadBitmap(230);
        abitmap8[41] = Game.loadBitmap(231);
        successIcon = abitmap8;
        newItemNext = Game.loadBitmap(174);
        newItemNextOn = Game.loadBitmap(175);
        Bitmap abitmap9[] = new Bitmap[5];
        abitmap9[0] = Game.loadBitmap(161);
        abitmap9[1] = Game.loadBitmap(150);
        abitmap9[2] = Game.loadBitmap(152);
        abitmap9[3] = Game.loadBitmap(153);
        abitmap9[4] = Game.loadBitmap(151);
        goodJobIcons = abitmap9;
        goodJobGoal = Game.loadBitmap(154);
        goodJobIncome = Game.loadBitmap(155);
        goodJobTips = Game.loadBitmap(163);
        goodJobTotal = Game.loadBitmap(164);
        Bitmap abitmap10[] = new Bitmap[3];
        abitmap10[0] = Game.loadBitmap(156);
        abitmap10[1] = Game.loadBitmap(157);
        abitmap10[2] = Game.loadBitmap(158);
        goodJobM = abitmap10;
        goodJobBest = Game.loadBitmap(149);
        goodJobArrow = Game.loadBitmap(159);
        goodJobArrowOn = Game.loadBitmap(160);
        goodJobValid = Game.loadBitmap(165);
        goodJobReject = Game.loadBitmap(162);
        Bitmap abitmap11[] = new Bitmap[18];
        abitmap11[0] = Game.loadBitmap(46);
        abitmap11[1] = Game.loadBitmap(48);
        abitmap11[2] = Game.loadBitmap(50);
        abitmap11[3] = Game.loadBitmap(52);
        abitmap11[4] = Game.loadBitmap(53);
        abitmap11[5] = Game.loadBitmap(54);
        abitmap11[6] = Game.loadBitmap(55);
        abitmap11[7] = Game.loadBitmap(57);
        abitmap11[8] = Game.loadBitmap(58);
        abitmap11[9] = Game.loadBitmap(60);
        abitmap11[10] = Game.loadBitmap(61);
        abitmap11[11] = Game.loadBitmap(62);
        abitmap11[12] = Game.loadBitmap(63);
        abitmap11[13] = Game.loadBitmap(64);
        abitmap11[14] = Game.loadBitmap(65);
        abitmap11[15] = Game.loadBitmap(67);
        abitmap11[16] = Game.loadBitmap(68);
        abitmap11[17] = Game.loadBitmap(70);
        boardItems = abitmap11;
        Bitmap abitmap12[] = new Bitmap[18];
        abitmap12[0] = Game.loadBitmap(47);
        abitmap12[1] = Game.loadBitmap(49);
        abitmap12[2] = Game.loadBitmap(51);
        abitmap12[6] = Game.loadBitmap(56);
        abitmap12[8] = Game.loadBitmap(59);
        abitmap12[14] = Game.loadBitmap(66);
        abitmap12[16] = Game.loadBitmap(69);
        flashItems = abitmap12;
        Bitmap abitmap13[] = new Bitmap[12];
        abitmap13[0] = Game.loadBitmap(93);
        abitmap13[1] = Game.loadBitmap(94);
        abitmap13[2] = Game.loadBitmap(95);
        abitmap13[3] = Game.loadBitmap(96);
        abitmap13[4] = Game.loadBitmap(97);
        abitmap13[5] = Game.loadBitmap(98);
        abitmap13[6] = Game.loadBitmap(99);
        abitmap13[7] = Game.loadBitmap(100);
        abitmap13[8] = Game.loadBitmap(101);
        abitmap13[9] = Game.loadBitmap(102);
        abitmap13[10] = Game.loadBitmap(103);
        abitmap13[11] = Game.loadBitmap(104);
        burgerParts = abitmap13;
        Bitmap abitmap14[] = new Bitmap[6];
        abitmap14[0] = Game.loadBitmap(105);
        abitmap14[1] = Game.loadBitmap(106);
        abitmap14[2] = Game.loadBitmap(107);
        abitmap14[3] = Game.loadBitmap(108);
        abitmap14[4] = Game.loadBitmap(109);
        abitmap14[5] = Game.loadBitmap(110);
        accompItems = abitmap14;
        menus = new Bitmap[4];
        Bitmap abitmap15[] = new Bitmap[18];
        abitmap15[0] = Game.loadBitmap(71);
        abitmap15[1] = Game.loadBitmap(72);
        abitmap15[2] = Game.loadBitmap(73);
        abitmap15[3] = Game.loadBitmap(74);
        abitmap15[4] = Game.loadBitmap(75);
        abitmap15[5] = Game.loadBitmap(76);
        abitmap15[6] = Game.loadBitmap(77);
        abitmap15[7] = Game.loadBitmap(78);
        abitmap15[8] = Game.loadBitmap(79);
        abitmap15[9] = Game.loadBitmap(80);
        abitmap15[10] = Game.loadBitmap(81);
        abitmap15[11] = Game.loadBitmap(82);
        abitmap15[12] = Game.loadBitmap(83);
        abitmap15[13] = Game.loadBitmap(84);
        abitmap15[14] = Game.loadBitmap(85);
        abitmap15[15] = Game.loadBitmap(86);
        abitmap15[16] = Game.loadBitmap(87);
        abitmap15[17] = Game.loadBitmap(88);
        menuItems = abitmap15;
        Bitmap abitmap16[] = new Bitmap[13];
        abitmap16[0] = Game.loadBitmap(33);
        abitmap16[1] = Game.loadBitmap(34);
        abitmap16[2] = Game.loadBitmap(35);
        abitmap16[3] = Game.loadBitmap(36);
        abitmap16[4] = Game.loadBitmap(37);
        abitmap16[5] = Game.loadBitmap(38);
        abitmap16[6] = Game.loadBitmap(39);
        abitmap16[7] = Game.loadBitmap(40);
        abitmap16[8] = Game.loadBitmap(41);
        abitmap16[9] = Game.loadBitmap(42);
        abitmap16[10] = Game.loadBitmap(43);
        abitmap16[11] = Game.loadBitmap(44);
        abitmap16[12] = Game.loadBitmap(45);
        coin = abitmap16;
        Bitmap abitmap17[] = new Bitmap[10];
        abitmap17[0] = Game.loadBitmap(16);
        abitmap17[1] = Game.loadBitmap(17);
        abitmap17[2] = Game.loadBitmap(18);
        abitmap17[3] = Game.loadBitmap(19);
        abitmap17[4] = Game.loadBitmap(20);
        abitmap17[5] = Game.loadBitmap(21);
        abitmap17[6] = Game.loadBitmap(22);
        abitmap17[7] = Game.loadBitmap(23);
        abitmap17[8] = Game.loadBitmap(24);
        abitmap17[9] = Game.loadBitmap(25);
        clockNum = abitmap17;
        mIntro = Game.loadBitmap(13);
        plate = Game.loadBitmap(14);
        tray = Game.loadBitmap(15);
        menu = Game.loadBitmap(89);
        menuR = Game.loadBitmap(90);
        menuBack = Game.loadBitmap(91);
        menuCoche = Game.loadBitmap(12);
        menuTimer = Game.loadBitmap(92);
        arrowL = Game.loadBitmap(9);
        arrowR = Game.loadBitmap(10);
        arrowU = Game.loadBitmap(11);
        arrowD = Game.loadBitmap(8);
        clockBG1 = Game.loadBitmap(27);
        clockBG2 = Game.loadBitmap(26);
        clockHour = Game.loadBitmap(30);
        clockMin = Game.loadBitmap(31);
        clockDots = Game.loadBitmap(28);
        clockHammer = Game.loadBitmap(29);
        inited = true;
        serviceBell = Game.loadBitmap(111);
        serviceButton = Game.loadBitmap(112);
        undoBox = Game.loadBitmap(115);
        undoButton = Game.loadBitmap(116);
        snapUp = Game.loadBitmap(114);
        snapDown = Game.loadBitmap(113);
        photos = new Bitmap[3];
        Bitmap abitmap18[] = new Bitmap[4];
        abitmap18[0] = Game.loadBitmap(140);
        abitmap18[1] = Game.loadBitmap(141);
        abitmap18[2] = Game.loadBitmap(142);
        abitmap18[3] = Game.loadBitmap(143);
        galleryButtons = abitmap18;
        Bitmap abitmap19[] = new Bitmap[6];
        abitmap19[0] = Game.loadBitmap(147);
        abitmap19[1] = Game.loadBitmap(148);
        abitmap19[3] = Game.loadBitmap(145);
        abitmap19[4] = Game.loadBitmap(146);
        abitmap19[5] = Game.loadBitmap(144);
        galleryOptions = abitmap19;
    }

    public static void recyclePhotos()
    {
        int i = 0;
        do
        {
            if(i >= 3)
            {
                return;
            }
            if(photos[i] != null && photos[i].isRecycled())
            {
                photos[i].recycle();
            }
            i++;
        } while(true);
    }

    public static void setBackground(int i)
    {
        if(currentBackground == i)
            return;
        switch(i) {
            default:case 3:case 4:case 5:
                break;
            case 2:
                background = Game.loadBitmap(0);
                break;
            case 6:
                background = Game.loadBitmap(2);
                break;
            case 7:
                background = Game.createBitmap(Game.mBufferWidth, Game.mBufferHeight);
                break;
            case 8:
                background = Game.loadBitmap(3);
                break;
        }
        currentBackground = i;
    }

}
