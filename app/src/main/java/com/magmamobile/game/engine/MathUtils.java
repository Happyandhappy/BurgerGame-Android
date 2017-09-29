package com.magmamobile.game.engine;


public final class MathUtils
{

    public static final float DEGREE = 57.29578F;
    public static final float EPSILON = 0.0001F;
    public static final float PI = 3.141593F;
    public static final float RADIAN = 0.01745329F;

    public MathUtils()
    {
    }

    public static final boolean BoxInBox(int i, int j, int k, int l, int i1, int j1, int k1, int l1)
    {
        return i >= i1 && j >= j1 && k <= k1 && l <= l1;
    }

    public static final boolean BoxIntersect(int i, int j, int k, int l, int i1, int j1, int k1, int l1)
    {
        return i < k1 && i1 < k && j < l1 && j1 < l;
    }

    public static final int Int(float f)
    {
        int i;
        for(i = (int)f; f >= 0.0F || f - (float)i == 0.0F;)
        {
            return i;
        }

        return i - 1;
    }

    public static final boolean PtInRect(int i, int j, int k, int l, int i1, int j1)
    {
        return i1 >= i && i1 <= k && j1 >= j && j1 <= l;
    }

    public static final boolean RectIntersect(int i, int j, int k, int l, int i1, int j1, int k1, int l1)
    {
        if(PtInRect(i, j, k, l, i1, j1))
        {
            return true;
        }
        if(PtInRect(i, j, k, l, k1, l1))
        {
            return true;
        }
        return SegmentInRect(i, j, k, j, i1, j1, k1, l1) || SegmentInRect(i, j, i, l, i1, j1, k1, l1) || SegmentInRect(k, j, k, j, i1, j1, k1, l1) || SegmentInRect(k, j, k, l, i1, j1, k1, l1);
    }

    public static final boolean SegmentInRect(int i, int j, int k, int l, int i1, int j1, int k1, int l1)
    {
        int i2 = k - i;
        int j2 = l - j;
        int k2 = k1 - i1;
        int l2 = l1 - j1;
        if(k2 * j2 - l2 * i2 != 0)
        {
            float f = (float)(i2 * (j1 - j) + j2 * (i - i1)) / (float)(k2 * j2 - l2 * i2);
            float f1 = (float)(k2 * (j - j1) + l2 * (i1 - i)) / (float)(l2 * i2 - k2 * j2);
            return f >= 0.0F && f <= 1.0F && f1 >= 0.0F && f1 <= 1.0F;
        } else
        {
            return false;
        }
    }

    public static final float absf(float f)
    {
        if(f == 0.0F)
        {
            f = 0.0F;
        } else
        if(f <= 0.0F)
        {
            return -f;
        }
        return f;
    }

    public static final int absi(int i)
    {
        if(i == 0)
        {
            i = 0;
        } else
        if(i <= 0)
        {
            return -i;
        }
        return i;
    }

    public static final int argb(float f, float f1, float f2, float f3)
    {
        return (int)(f * 255F) << 24 | (int)(f1 * 255F) << 16 | (int)(f2 * 255F) << 8 | (int)(f3 * 255F);
    }

    public static final int argb(int i, int j, int k, int l)
    {
        return (i & 0xff) << 24 | (j & 0xff) << 16 | (k & 0xff) << 8 | l & 0xff;
    }

    public static final float getAccelerateInterpolation(float f, float f1)
    {
        if(f1 == 1.0F)
        {
            return f * f;
        } else
        {
            return (float)Math.pow(f, 2.0F * f1);
        }
    }

    public static final float getAngle(float f, float f1)
    {
        if(f > 0.0001F)
        {
            return (float)Math.atan(f1 / f);
        }
        if(f < -0.0001F)
        {
            return (float)(3.1415927410125732D - Math.atan(-f1 / f));
        } else
        {
            return (3.141593F * Math.signum(f1)) / 2.0F;
        }
    }

    public static final float getAnticipateInterpolation(float f, float f1)
    {
        return f * f * (f * (1.0F + f1) - f1);
    }

    public static final float getBackAndForthInterpolation(float f)
    {
        if(f < 0.5F)
        {
            return f + f;
        } else
        {
            return 2.0F - f - f;
        }
    }

    public static final float getCycleInterpolation(float f, float f1)
    {
        return (float)Math.abs(Math.sin(3.1415926535897931D * (double)f1 * (double)f));
    }

    public static final float getDecelerateInterpolation(float f, float f1)
    {
        if(f1 == 1.0F)
        {
            return 1.0F - (1.0F - f) * (1.0F - f);
        } else
        {
            return (float)(1.0D - Math.pow(1.0F - f, 2.0F * f1));
        }
    }

    public static final int getDirection(int i, int j)
    {
        if(j < 0 && i == 0)
        {
            return 0;
        }
        if(i > 0 && j == 0)
        {
            return 1;
        }
        if(j > 0 && i == 0)
        {
            return 2;
        }
        return i >= 0 || j != 0 ? -1 : 3;
    }

    public static final float getFactor(float f, float f1, float f2)
    {
        float f3 = f2 - f1;
        if(f3 == 0.0F)
        {
            return 0.0F;
        } else
        {
            return (f - f1) / f3;
        }
    }

    public static final float getOvershootInterpolation(float f, float f1)
    {
        float f2 = f - 1.0F;
        return 1.0F + f2 * f2 * (f1 + f2 * (f1 + 1.0F));
    }

    public static final float getShakeInterpolation(float f, float f1)
    {
        float f3;
        float f4;
label0:
        {
            float f2 = 0.5F;
            f3 = f1 / 4F;
            f4 = 2.0F / f1;
            if(f < f1)
            {
                if(f >= f3)
                {
                    break label0;
                }
                f2 += f * f4;
            }
            return f2;
        }
        if(f < 3F * f3)
        {
            return 1.5F - f * f4;
        } else
        {
            return f * f4 - 1.5F;
        }
    }

    public static final float length(float f, float f1)
    {
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    public static final float lerpAccelerate(float f, float f1, float f2)
    {
        return f + (f1 - f) * getAccelerateInterpolation(f2, 1.0F);
    }

    public static final float lerpAnim(int i, int j, int k, int l)
    {
        return (float)i + (float)(j - i) * getDecelerateInterpolation((float)k / (float)l, 1.0F);
    }

    public static final float lerpAnticipate(float f, float f1, float f2)
    {
        return f + (f1 - f) * getAnticipateInterpolation(f2, 2.0F);
    }

    public static final float lerpBackAndForth(float f, float f1, float f2)
    {
        return f + (f1 - f) * getBackAndForthInterpolation(f2);
    }

    public static final float lerpCycle(float f, float f1, float f2, float f3)
    {
        return f + (f1 - f) * getCycleInterpolation(f2, f3);
    }

    public static final float lerpDecelerate(float f, float f1, float f2)
    {
        return f + (f1 - f) * getDecelerateInterpolation(f2, 1.0F);
    }

    public static final float lerpLinear(float f, float f1, float f2)
    {
        return f + f2 * (f1 - f);
    }

    public static final float lerpOvershoot(float f, float f1, float f2)
    {
        return f + (f1 - f) * getOvershootInterpolation(f2, 2.0F);
    }

    public static final float lerpShake(float f, float f1, float f2, float f3)
    {
        return f + (f1 - f) * getShakeInterpolation(f2, f3);
    }

    public static final float maxf(float f, float f1)
    {
        if(f > f1)
        {
            return f;
        } else
        {
            return f1;
        }
    }

    public static final int maxi(int i, int j)
    {
        if(i > j)
        {
            return i;
        } else
        {
            return j;
        }
    }

    public static final float minf(float f, float f1)
    {
        if(f < f1)
        {
            return f;
        } else
        {
            return f1;
        }
    }

    public static final int mini(int i, int j)
    {
        if(i < j)
        {
            return i;
        } else
        {
            return j;
        }
    }

    public static final float minmaxf(float f, float f1, float f2)
    {
        if(f > f2)
        {
            return f2;
        }
        if(f < f1)
        {
            return f1;
        } else
        {
            return f;
        }
    }

    public static final int minmaxi(int i, int j, int k)
    {
        if(i > k)
        {
            return k;
        }
        if(i < j)
        {
            return j;
        } else
        {
            return i;
        }
    }

    public static final int modulo(int i, int j)
    {
        if(i < 0)
        {
            return j + i % j;
        } else
        {
            return i % j;
        }
    }

    public static final boolean randomb()
    {
        return Math.random() > 0.5D;
    }

    public static final float randomf()
    {
        return (float)Math.random();
    }

    public static final float randomf(float f)
    {
        return (float)(Math.random() * (double)f);
    }

    public static final float randomf(float f, float f1)
    {
        return (float)((double)f + Math.random() * (double)(f1 - f));
    }

    public static final int randomi(int i)
    {
        return (int)(Math.random() * (double)i);
    }

    public static final int randomi(int i, int j)
    {
        return (int)((double)i + Math.random() * (double)(j - i));
    }

    public static final int sgnf(float f)
    {
        if(f == 0.0F)
        {
            return 0;
        }
        return f <= 0.0F ? -1 : 1;
    }

    public static final int sgni(int i)
    {
        if(i == 0)
        {
            return 0;
        }
        return i <= 0 ? -1 : 1;
    }

    public static final float toDegree(float f)
    {
        return 57.29578F * f;
    }

    public static final float toRadian(float f)
    {
        return 0.01745329F * f;
    }
}
