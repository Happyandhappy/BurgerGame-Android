package com.magmamobile.tool.debugTool;

import java.io.Serializable;

public final class DebugTool
{
    public static class MenuItem2
        implements Serializable
    {

        public String name;
        public int tag;

        public MenuItem2()
        {
        }
    }


    public static final int ACTION_ATLAS = 0xa7d8c0;
    public static final int ACTION_ATLAS_HIDE = 0xa7d8c2;
    public static final int ACTION_ATLAS_NEXT = 0xa7d8c3;
    public static final int ACTION_ATLAS_PREV = 0xa7d8c4;
    public static final int ACTION_ATLAS_SHOW = 0xa7d8c1;
    public static final int ACTION_ENGINE = 0x989680;
    public static final int ACTION_ENGINE_QUIT = 0x989681;
    public static final int ACTION_RATE = 0xb71b00;
    public static final int ACTION_RATE_ACTION = 0xb8a1a0;
    public static final int ACTION_RATE_ACTION_FAST = 0xb8a1a1;
    public static final int ACTION_RATE_ACTION_NORMAL = 0xb8a1a2;
    public static final int ACTION_RATE_ACTION_SLOW = 0xb8a1a3;
    public static final int ACTION_RATE_RENDER = 0xba2840;
    public static final int ACTION_RATE_RENDER_FAST = 0xba2841;
    public static final int ACTION_RATE_RENDER_NORMAL = 0xba2842;
    public static final int ACTION_RATE_RENDER_SLOW = 0xba2843;
    public static final String DEBUG_MESSAGE = "com.magmamobile.debugtool";
    public static final String EXTRA_PARAMS = "params";
    public static final String EXTRA_PNAME = "pname";
    public static final String EXTRA_TAG = "tag";

    public DebugTool()
    {
    }
}
