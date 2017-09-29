package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.engine.Game;

public class ScoreDisplay
{

    private final int GOAL_COLOR = 0xffbbbbbb;
    private final float MIN_SCALE = 0.8F;
    private int goal;
    private String goalStr;
    private int goalX;
    private int goalY;
    private int maxGoalW;
    private int maxScoreW;
    private Paint pAlias;
    private Paint pGoal;
    private Paint pScore;
    private Paint pTitle;
    private int score;
    private String scoreStr;
    private int scoreX;
    private int scoreY;
    private String titleStr;
    private int titleX;
    private int titleY;
    private int validH;
    private int validW;
    private int validX;
    private int validY;
    private boolean validated;

    public ScoreDisplay()
    {
        pAlias = new Paint();
        pAlias.setAntiAlias(true);
        titleX = Game.scalei(265);
        titleY = Game.scalei(254);
        titleStr = Game.getResString(R.string.res_score).toUpperCase();
        pTitle = new Paint(pAlias);
        pTitle.setTypeface(App.scoreFont);
        pTitle.setColor(0xff000000);
        Paint paint = pTitle;
        float f;
        if(validLang())
        {
            f = 24F;
        } else
        {
            f = 16F;
        }
        paint.setTextSize(Game.scalef(f));
        pTitle.setTextScaleX(0.8F);
        scoreX = Game.scalei(345);
        scoreY = Game.scalei(280);
        pScore = new Paint(pTitle);
        pScore.setTextSize(Game.scalef(57.6F));
        pScore.setColor(-1);
        pScore.setTextAlign(android.graphics.Paint.Align.RIGHT);
        goalX = Game.scalei(344);
        goalY = Game.scalei(309);
        pGoal = new Paint(pScore);
        pGoal.setTextSize(Game.scalef(36F));
        pGoal.setTextScaleX(1.0F);
        validX = Game.scalei(268);
        validY = Game.scalei(294);
        validW = (int)(0.45F * (float)BitmapManager.goodJobValid.getWidth());
        validH = (int)(0.45F * (float)BitmapManager.goodJobValid.getHeight());
        maxScoreW = Game.scalei(78);
        maxGoalW = Game.scalei(62);
        goalStr = "";
        scoreStr = "";
    }

    private boolean validLang()
    {
        String as[];
        int i;
        int j;
        boolean flag;
        as = (new String[] {
            "ja", "ko", "ru", "zh-rCN", "zh-rTW", "el", "iw", "ar"
        });
        i = as.length;
        j = 0;
        while(j < i) {
            flag = as[j].equals(Game.getResString(R.string.lang));
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    public void draw()
    {
        Game.drawText(titleStr, titleX, titleY, pTitle);
        Game.drawText(scoreStr, scoreX, scoreY, pScore);
        Game.drawText(goalStr, goalX, goalY, pGoal);
        if(validated)
        {
            Game.drawBitmap(BitmapManager.goodJobValid, validX, validY, validW, validH, pAlias);
        }
    }

    public void init()
    {
        validated = false;
        score = GameManager.dayTotal;
        scoreStr = String.valueOf(score);
        goal = GameManager.dayGoal;
        goalStr = String.valueOf(goal);
        pGoal.setColor(0xffbbbbbb);
        pGoal.setTextScaleX(1.0F);
        int i = Game.getTextWidth(goalStr, pGoal);
        if(i > maxGoalW)
        {
            pGoal.setTextScaleX((float)maxGoalW / (float)i);
        }
    }

    public void update()
    {
        if(score != GameManager.dayTotal) {
            score = GameManager.dayTotal;
            scoreStr = String.valueOf(score);
            if (scoreStr.length() <= 5) {
                if (pScore.getTextScaleX() != 0.8F) {
                    pScore.setTextScaleX(0.8F);
                }
            } else {
                pScore.setTextScaleX(0.8F);
                int i = Game.getTextWidth(scoreStr, pScore);
                pScore.setTextScaleX(0.8F * ((float) maxScoreW / (float) i));
            }
        }
        if(score >= goal && !validated)
        {
            validated = true;
            pGoal.setColor(0xff00bb00);
            SoundManager.playSound(28);
        }
    }
}
