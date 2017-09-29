package com.magmamobile.game.engine;

public final class FeedbackButton extends Button
{

    private String formkey;

    public FeedbackButton(String s)
    {
        formkey = s;
        setX(0.0F);
        setY(Game.scalei(60));
        setWidth(Game.getBufferWidth());
        setHeight(Game.scalei(50));
        getPainter().setFontSize(Game.scalef(30F));
        getPainter().setStrokeWidth(Game.scalef(4F));
        getPainter().setStrokeColor(0xff000000);
        getPainter().setFontColor(-1);
        getLabel().setHorizontalAlign((byte)0);
        getLabel().setVerticalAlign((byte)0);
        setText("Feedback");
    }

    public String getFormkey()
    {
        return formkey;
    }

    public void onAction()
    {
        super.onAction();
        if(onClick)
        {
            Game.showBrowser((new StringBuilder("https://docs.google.com/spreadsheet/embeddedform?formkey=")).append(formkey).toString());
        }
    }

    public void onRender()
    {
        super.onRender();
    }

    public void setFormkey(String s)
    {
        formkey = s;
    }
}
