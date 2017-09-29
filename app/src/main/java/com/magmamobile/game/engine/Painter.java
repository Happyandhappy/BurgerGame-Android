package com.magmamobile.game.engine;

import android.graphics.*;

public class Painter
{

    private Rect bounds;
    private android.graphics.Paint.Align fontAlign;
    private int fontAlpha;
    private boolean fontBold;
    private int fontColor;
    private Typeface fontFace;
    private int fontGradient1;
    private int fontGradient2;
    private float fontSize;
    private Paint pFill;
    private Paint pShadow;
    private Paint pStroke;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int strokeColor;
    private float strokeWidth;
    private boolean useGradient;

    public Painter()
    {
        bounds = new Rect();
        useGradient = false;
        fontGradient1 = 0;
        fontGradient2 = 0;
        strokeWidth = 0.0F;
        strokeColor = 0;
        shadowRadius = 0.0F;
        shadowColor = 0;
        shadowDx = 0.0F;
        shadowDy = 0.0F;
        fontColor = Game.parameters.DEFAULT_BUTTON_TEXT_COLOR;
        fontSize = Game.parameters.DEFAULT_BUTTON_TEXT_SIZE;
        fontFace = Game.typeface;
        fontBold = false;
        pFill = new Paint();
        pFill.setAntiAlias(true);
        pFill.setColor(fontColor);
        pFill.setTextSize(fontSize);
        pFill.setFilterBitmap(true);
        pFill.setStyle(android.graphics.Paint.Style.FILL);
        pFill.setFakeBoldText(fontBold);
        pFill.setTypeface(fontFace);
        pFill.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        pStroke = new Paint();
        pStroke.setAntiAlias(true);
        pStroke.setColor(strokeColor);
        pStroke.setTextSize(fontSize);
        pStroke.setFilterBitmap(true);
        if(Game.androidSDKVersion >= 19)
        {
            pStroke.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        } else
        {
            pStroke.setStyle(android.graphics.Paint.Style.STROKE);
        }
        pStroke.setFakeBoldText(fontBold);
        pStroke.setTypeface(fontFace);
        pStroke.setStrokeWidth(strokeWidth);
        pShadow = new Paint();
        pShadow.setAntiAlias(true);
        pShadow.setColor(0);
        pShadow.setTextSize(fontSize);
        pShadow.setFilterBitmap(true);
        pShadow.setStyle(android.graphics.Paint.Style.FILL);
        pShadow.setFakeBoldText(fontBold);
        pShadow.setTypeface(fontFace);
    }

    public void draw(String s, float f, float f1)
    {
        if(shadowColor != 0)
        {
            Game.mCanvas.drawText(s, f, f1, pShadow);
        }
        if(strokeWidth > 0.0F)
        {
            Game.mCanvas.drawText(s, f, f1, pStroke);
        }
        if(useGradient)
        {
            pFill.setShader(new LinearGradient(0.0F, f1 - pFill.getTextSize(), 0.0F, f1, fontGradient1, fontGradient2, android.graphics.Shader.TileMode.CLAMP));
        }
        Game.mCanvas.drawText(s, f, f1, pFill);
    }

    public int getAlpha()
    {
        return fontAlpha;
    }

    public Paint getFillPaint()
    {
        return pFill;
    }

    public android.graphics.Paint.Align getFontAlign()
    {
        return fontAlign;
    }

    public boolean getFontBold()
    {
        return fontBold;
    }

    public int getFontColor()
    {
        return fontColor;
    }

    public Typeface getFontFace()
    {
        return fontFace;
    }

    public float getFontSize()
    {
        return fontSize;
    }

    public int getGradient1()
    {
        return fontGradient1;
    }

    public int getGradient2()
    {
        return fontGradient2;
    }

    public int getShadowColor()
    {
        return shadowColor;
    }

    public float getShadowDx()
    {
        return shadowDx;
    }

    public float getShadowDy()
    {
        return shadowDy;
    }

    public float getShadowRadius()
    {
        return shadowRadius;
    }

    public int getStrokeColor()
    {
        return strokeColor;
    }

    public float getStrokeWidth()
    {
        return strokeWidth;
    }

    public Rect getTextBounds(String s)
    {
        pFill.getTextBounds(s, 0, s.length(), bounds);
        return bounds;
    }

    public void getTextBounds(String s, int i, int j, Rect rect)
    {
        pFill.getTextBounds(s, i, j, rect);
    }

    public float getTextSize()
    {
        return pFill.getTextSize();
    }

    public int getTextWidth(String s)
    {
        pFill.getTextBounds(s, 0, s.length(), bounds);
        return bounds.right - bounds.left;
    }

    public boolean getUseGradient()
    {
        return useGradient;
    }

    public void setAlpha(int i)
    {
        if(fontAlpha != i)
        {
            pFill.setAlpha(i);
            pStroke.setAlpha(i);
            pShadow.setAlpha(i);
            fontAlpha = i;
        }
    }

    public void setFontAlign(android.graphics.Paint.Align align)
    {
        if(fontAlign != align)
        {
            pFill.setTextAlign(align);
            pStroke.setTextAlign(align);
            pShadow.setTextAlign(align);
            fontAlign = align;
        }
    }

    public void setFontBold(boolean flag)
    {
        if(fontBold != flag)
        {
            pFill.setFakeBoldText(flag);
            pStroke.setFakeBoldText(flag);
            pShadow.setFakeBoldText(flag);
            fontBold = flag;
        }
    }

    public void setFontColor(int i)
    {
        if(fontColor != i)
        {
            fontColor = i;
            pFill.setColor(fontColor);
        }
    }

    public void setFontFace(Typeface typeface)
    {
        if(fontFace != typeface)
        {
            pFill.setTypeface(typeface);
            pStroke.setTypeface(typeface);
            pShadow.setTypeface(typeface);
            fontFace = typeface;
        }
    }

    public void setFontSize(float f)
    {
        if(fontSize != f)
        {
            pFill.setTextSize(f);
            pStroke.setTextSize(f);
            pShadow.setTextSize(f);
            fontSize = f;
        }
    }

    public void setGradient1(int i)
    {
        fontGradient1 = i;
    }

    public void setGradient2(int i)
    {
        fontGradient2 = i;
    }

    public void setShadowColor(int i)
    {
        if(shadowColor != i)
        {
            shadowColor = i;
            pShadow.setColor(shadowColor);
            pShadow.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        }
    }

    public void setShadowDensity(float f)
    {
        pShadow.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        pShadow.setStrokeWidth(f);
    }

    public void setShadowDx(float f)
    {
        if(shadowDx != f)
        {
            shadowDx = f;
            pShadow.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        }
    }

    public void setShadowDy(float f)
    {
        if(shadowDy != f)
        {
            shadowDy = f;
            pShadow.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        }
    }

    public void setShadowRadius(float f)
    {
        if(shadowRadius != f)
        {
            shadowRadius = f;
            pShadow.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        }
    }

    public void setStrokeColor(int i)
    {
        if(strokeColor != i)
        {
            strokeColor = i;
            pStroke.setColor(strokeColor);
        }
    }

    public void setStrokeWidth(float f)
    {
        if(strokeWidth != f)
        {
            strokeWidth = f;
            pStroke.setStrokeWidth(strokeWidth);
        }
    }

    public void setUseGradient(boolean flag)
    {
        if(useGradient && !flag)
        {
            pFill.setShader(null);
        }
        useGradient = flag;
    }
}
