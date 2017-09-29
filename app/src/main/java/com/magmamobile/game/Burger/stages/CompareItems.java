package com.magmamobile.game.Burger.stages;

import com.magmamobile.game.Burger.managers.AchievementManager;
import java.util.Comparator;

class CompareItems
    implements Comparator
{

    CompareItems()
    {
    }

    public int compare(Integer integer, Integer integer1)
    {
        boolean flag = AchievementManager.state[integer.intValue()];
        boolean flag1 = AchievementManager.state[integer1.intValue()];
        if(flag && !flag1)
        {
            return -1;
        }
        return flag || !flag1 ? 0 : 1;
    }

    public int compare(Object obj, Object obj1)
    {
        return compare((Integer)obj, (Integer)obj1);
    }
}
