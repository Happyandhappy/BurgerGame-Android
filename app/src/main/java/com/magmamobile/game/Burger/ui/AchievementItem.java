package com.magmamobile.game.Burger.ui;

import com.magmamobile.game.engine.Label;

public class AchievementItem
{

    public String count;
    public int counterY;
    public int h;
    public Label label;
    public boolean last;
    public int logoY;
    public String name;
    public int nameY;
    public int separatorY;
    public int validY;
    public int y;

    public AchievementItem()
    {
        last = false;
        label = new Label();
    }
}
