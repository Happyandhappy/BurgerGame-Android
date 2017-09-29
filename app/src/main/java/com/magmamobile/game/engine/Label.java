package com.magmamobile.game.engine;

import android.graphics.*;
import java.util.StringTokenizer;

public class Label extends GameObject
{
    public static class Align
    {

        public static final byte BOTTOM = 2;
        public static final byte CENTER = 0;
        public static final byte LEFT = 1;
        public static final byte RIGHT = 2;
        public static final byte TOP = 1;

        public Align()
        {
        }
    }

    public static class Gravity
    {

        public static final int CENTER = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;

        public Gravity()
        {
        }
    }

    private static class StringParcel
    {

        public Rect bounds;
        public int height;
        public int ofsX;
        public int ofsY;
        public String string;
        public int width;

        public void computeBounds(Painter painter)
        {
            painter.getTextBounds(string, 0, string.length(), bounds);
            width = bounds.right - bounds.left;
            height = bounds.bottom - bounds.top;
        }

        public StringParcel()
        {
            bounds = new Rect();
        }
    }


    private boolean _autoSize;
    private byte _hAlign;
    private int _innerHeight;
    private int _innerWidth;
    private int _interline;
    private int _maxHeight;
    protected int _maxLines;
    private int _maxWidth;
    private int _minHeight;
    private int _minWidth;
    private Painter _painter;
    private StringParcel _parcels[];
    private LabelShader _shader;
    private String _text;
    private byte _vAlign;
    private boolean _wordWrap;

    public Label()
    {
        _painter = new Painter();
        _interline = Game.scalei(4);
        _maxLines = 10;
        visible = true;
        enabled = true;
        _autoSize = false;
        _wordWrap = false;
        _vAlign = 0;
        _hAlign = 0;
    }

    public Label(int i, int j, int k, int l, String s)
    {
        this();
        setX(i);
        setY(j);
        setW(k);
        setH(l);
        setText(s);
    }

    public Label(int i, int j, int k, int l, String s, int i1)
    {
        this(i, j, k, l, s);
        setHorizontalAlign((byte)i1);
    }

    private void computeHorizontalOfs()
    {
        int i = _parcels.length;
        switch(_hAlign){
            default:
                break;
            case 0:
                int l = 0;
                while(l < i)
                {
                    StringParcel stringparcel1 = _parcels[l];
                    stringparcel1.ofsX = cw - stringparcel1.width / 2;
                    l++;
                }
                break;
            case 1:
                int k = 0;
                while(k < i)
                {
                    _parcels[k].ofsX = 0;
                    k++;
                }
                break;
            case 2:
                int j = 0;
                while(j < i)
                {
                    StringParcel stringparcel = _parcels[j];
                    stringparcel.ofsX = w - stringparcel.width;
                    j++;
                }
                break;
        }
    }

    private void computeParcelBounds(Painter painter)
    {
        int i = _parcels.length;
        _minWidth = 0x7fffffff;
        _minHeight = 0x7fffffff;
        _maxWidth = 0x80000000;
        _maxHeight = 0x80000000;
        _innerHeight = 0;
        int j = 0;
        do
        {
            if(j >= i)
            {
                _innerWidth = _maxWidth;
                return;
            }
            StringParcel stringparcel = _parcels[j];
            stringparcel.computeBounds(painter);
            if(_minWidth > stringparcel.width)
            {
                _minWidth = stringparcel.width;
            }
            if(_minHeight > stringparcel.height)
            {
                _minHeight = stringparcel.height;
            }
            if(_maxWidth < stringparcel.width)
            {
                _maxWidth = stringparcel.width;
            }
            if(_maxHeight < stringparcel.height)
            {
                _maxHeight = stringparcel.height;
            }
            if(i > 0)
            {
                _innerHeight = _innerHeight + _interline;
            }
            _innerHeight = _innerHeight + stringparcel.height;
            j++;
        } while(true);
    }

    private void computeVerticalOfs()
    {
        int i;
        int j;
        i = _parcels.length;
        j = 0;
        switch (_vAlign){
            default:
                break;
            case 0:
                int i1 = 0;
                while(i1 < i)
                {
                    StringParcel stringparcel2 = _parcels[i1];
                    stringparcel2.ofsY = ((j + ch) - _innerHeight / 2) + (stringparcel2.height - stringparcel2.bounds.bottom);
                    j += stringparcel2.height + _interline;
                    i1++;
                }
                break;
            case 1:
                int l = 0;
                while(l < i)
                {
                    StringParcel stringparcel1 = _parcels[l];
                    stringparcel1.ofsY = j + (stringparcel1.height - stringparcel1.bounds.bottom);
                    j += stringparcel1.height + _interline;
                    l++;
                }
                break;
            case 2:
                int k = 0;
                while(k < i)
                {
                    StringParcel stringparcel = _parcels[k];
                    stringparcel.ofsY = ((j + h) - _innerHeight) + (stringparcel.height - stringparcel.bounds.bottom);
                    j += stringparcel.height + _interline;
                    k++;
                }
                break;
        }
    }

    public static Paint createLabelPaint(float f, int i, boolean flag, boolean flag1, boolean flag2)
    {
        return createLabelPaint(f, i, flag, flag1, flag2, 0.0F);
    }

    public static Paint createLabelPaint(float f, int i, boolean flag, boolean flag1, boolean flag2, float f1)
    {
        Paint paint = new Paint();
        paint.setTextSize(f);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setFakeBoldText(flag);
        paint.setTypeface(Game.typeface);
        if(flag1)
        {
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        } else
        {
            paint.setTextAlign(android.graphics.Paint.Align.LEFT);
        }
        if(flag2)
        {
            paint.setShadowLayer(Game.scalef(3F), Game.scalef(1.0F), Game.scalef(1.0F), 0x80000000);
        }
        paint.setColor(i);
        if(f1 != 0.0F)
        {
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setStrokeWidth(f1);
        }
        return paint;
    }

    private void drawWordWrap()
    {
        StringTokenizer stringtokenizer;
        float f;
        String as[];
        int ai[];
        int i;
        stringtokenizer = new StringTokenizer(_text, " ,:;.", true);
        f = _painter.getTextSize();
        as = new String[_maxLines];
        ai = new int[_maxLines];
        i = 0;
        as[0] = "";
        while(stringtokenizer.hasMoreTokens()){
            String s;
            int j;
            s = stringtokenizer.nextToken();
            j = _painter.getTextWidth((new StringBuilder(String.valueOf(as[i]))).append(s).toString());
            if(j <= w){
                as[i] = (new StringBuilder(String.valueOf(as[i]))).append(s).toString();
                ai[i] = j;
            }else {
                as[i + 1] = s;
                ai[i + 1] = _painter.getTextWidth(s);
                if (++i >= _maxLines)
                    break;
            }
        }
        if(_hAlign == 1) {
            int i1 = 0;
            while (i1 <= i) {
                _painter.draw(as[i1], (int) x, (int) ((float) (int) y + f * (float) i1));
                i1++;
            }
        }else if(_hAlign == 0){
            int l = 0;
            while(l <= i)
            {
                _painter.draw(as[l], ((int)x + cw) - ai[l] / 2, (int)((float)(int)y + f * (float)l));
                l++;
            }
        }else if(_hAlign == 2) {
            int k = 0;
            while (k <= i) {
                _painter.draw(as[k], ((int) x + cw) - ai[k], (int) ((float) (int) y + f * (float) k));
                k++;
            }
        }
    }

    public static Paint getDefaultPaint()
    {
        AppParameters appparameters = Game.getParameters();
        return createLabelPaint(appparameters.DEFAULT_BUTTON_TEXT_SIZE, appparameters.DEFAULT_BUTTON_TEXT_COLOR, false, true, false);
    }

    public static Typeface getDefaultTypeface()
    {
        return Game.typeface;
    }

    public static Typeface getSystemDefaultTypeface()
    {
        return Typeface.DEFAULT;
    }

    public static Typeface loadTypeface(String s)
    {
        try {
            if (s != null) {
                Typeface typeface = Typeface.createFromAsset(Game.getContext().getAssets(), s);
                return typeface;
            }
        }catch(Exception e){}
        return getSystemDefaultTypeface();
    }

    public static void setDefaultTypeface(Typeface typeface)
    {
        Game.typeface = typeface;
    }

    public static void setDefaultTypeface(String s)
    {
        setDefaultTypeface(loadTypeface(s));
    }

    public int getAlpha()
    {
        return _painter.getAlpha();
    }

    public int getColor()
    {
        return _painter.getFontColor();
    }

    public byte getHorizontalAlign()
    {
        return _hAlign;
    }

    public int getInterline()
    {
        return _interline;
    }

    public int getMaxLines()
    {
        return _maxLines;
    }

    public int getMesuredHeight()
    {
        return _innerHeight;
    }

    public int getMesuredWidth()
    {
        return _innerWidth;
    }

    public int getMinWidth()
    {
        return _minWidth;
    }

    public Paint getPaint()
    {
        return _painter.getFillPaint();
    }

    public Painter getPainter()
    {
        return _painter;
    }

    public LabelShader getShader()
    {
        return _shader;
    }

    public float getSize()
    {
        return _painter.getFontSize();
    }

    public String getText()
    {
        return _text;
    }

    public Typeface getTypeface()
    {
        return _painter.getFontFace();
    }

    public byte getVerticalAlign()
    {
        return _vAlign;
    }

    public boolean isAutoSize()
    {
        return _autoSize;
    }

    public boolean isWordWrap()
    {
        return _wordWrap;
    }

    protected final void measure()
    {
        if(_text == null)
        {
            return;
        }
        computeParcelBounds(_painter);
        if(_autoSize)
        {
            w = _maxWidth;
            h = _maxHeight;
            if(w < _minWidth)
            {
                w = _minWidth;
            }
            if(h < _minHeight)
            {
                h = _minHeight;
            }
            cw = w >> 1;
            ch = h >> 1;
        }
        computeHorizontalOfs();
        computeVerticalOfs();
    }

    public void onAction()
    {
    }

    public void onRender()
    {
        if(!(visible && enabled && _text != null))
            return;
        if(_shader != null)
        {
            _shader.onUpdate(this);
        }
        if(_wordWrap)
        {
            drawWordWrap();
            return;
        }
        if(_painter != null)
        {
            int i = _parcels.length;
            int j = 0;
            while(j < i) 
            {
                StringParcel stringparcel = _parcels[j];
                if(stringparcel != null)
                {
                    _painter.draw(stringparcel.string, (int)x + stringparcel.ofsX, (int)y + stringparcel.ofsY);
                }
                j++;
            }
        }
    }

    public void setAlpha(int i)
    {
        _painter.setAlpha(i);
    }

    public void setAutoSize(boolean flag)
    {
        if(_autoSize != flag)
        {
            _autoSize = flag;
            measure();
        }
    }

    public void setColor(int i)
    {
        _painter.setFontColor(i);
    }

    public void setGravity(int i)
    {
        setHorizontalAlign((byte)i);
    }

    public void setHeight(int i)
    {
        if(i != h)
        {
            h = i;
            ch = h >> 1;
            measure();
        }
    }

    public void setHorizontalAlign(byte byte0)
    {
        if(_hAlign != byte0)
        {
            _hAlign = byte0;
            measure();
        }
    }

    public void setInterline(int i)
    {
        if(_interline != i)
        {
            _interline = i;
            measure();
        }
    }

    public void setMaxLines(int i)
    {
        if(_maxLines != i)
        {
            _maxLines = i;
            measure();
        }
    }

    public void setMinWidth(int i)
    {
        _minWidth = i;
    }

    public void setPainter(Painter painter)
    {
        if(painter != _painter)
        {
            _painter = painter;
            measure();
        }
    }

    public void setShader(LabelShader labelshader)
    {
        _shader = labelshader;
    }

    public void setSize(float f)
    {
        if(f != _painter.getFontSize())
        {
            _painter.setFontSize(f);
            measure();
        }
    }

    public void setStrokeColor(int i)
    {
        _painter.setStrokeColor(i);
    }

    public void setStrokeWidth(float f)
    {
        _painter.setStrokeWidth(f);
    }

    public void setText(int i)
    {
        setText(Game.getResString(i));
    }

    public void setText(String s)
    {
        if(TextUtils.isEqual(s, _text))
        {
            return;
        }
        _text = s;
        if(_text == null)
        {
            _parcels = null;
        } else
        {
            String as[] = android.text.TextUtils.split(_text, "\n");
            _parcels = new StringParcel[as.length];
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                StringParcel stringparcel = new StringParcel();
                stringparcel.string = as[j];
                _parcels[j] = stringparcel;
                j++;
            }
        }
        measure();
    }

    public void setTypeface(Typeface typeface)
    {
        _painter.setFontFace(typeface);
        measure();
    }

    public void setVerticalAlign(byte byte0)
    {
        if(_vAlign != byte0)
        {
            _vAlign = byte0;
            measure();
        }
    }

    public void setWidth(int i)
    {
        if(i != w)
        {
            w = i;
            cw = w >> 1;
            measure();
        }
    }

    public void setWordWrap(boolean flag)
    {
        _wordWrap = flag;
    }
}
