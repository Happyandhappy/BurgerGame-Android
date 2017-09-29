package com.magmamobile.game.Burger.utils;


public final class Crc32
{

    private static final int table[];

    public Crc32()
    {
    }

    public static final int getValue(String s)
    {
        byte abyte0[];
        if(s != null)
        {
            abyte0 = s.getBytes();
        } else
        {
            abyte0 = null;
        }
        return getValue(abyte0);
    }

    public static final int getValue(byte abyte0[])
    {
        int j;
        int k;
        int i = -1;
        if(abyte0 != null) {
            j = 0;
            k = abyte0.length;
            while (j < k) {
                i = i >>> 8 ^ table[0xff & (i ^ abyte0[j])];
                j++;
            }
        }
        return ~i;
    }

    static 
    {
        int ai[] = new int[256];
        ai[1] = 0x77073096;
        ai[2] = 0xee0e612c;
        ai[3] = 0x990951ba;
        ai[4] = 0x76dc419;
        ai[5] = 0x706af48f;
        ai[6] = 0xe963a535;
        ai[7] = 0x9e6495a3;
        ai[8] = 0xedb8832;
        ai[9] = 0x79dcb8a4;
        ai[10] = 0xe0d5e91e;
        ai[11] = 0x97d2d988;
        ai[12] = 0x9b64c2b;
        ai[13] = 0x7eb17cbd;
        ai[14] = 0xe7b82d07;
        ai[15] = 0x90bf1d91;
        ai[16] = 0x1db71064;
        ai[17] = 0x6ab020f2;
        ai[18] = 0xf3b97148;
        ai[19] = 0x84be41de;
        ai[20] = 0x1adad47d;
        ai[21] = 0x6ddde4eb;
        ai[22] = 0xf4d4b551;
        ai[23] = 0x83d385c7;
        ai[24] = 0x136c9856;
        ai[25] = 0x646ba8c0;
        ai[26] = 0xfd62f97a;
        ai[27] = 0x8a65c9ec;
        ai[28] = 0x14015c4f;
        ai[29] = 0x63066cd9;
        ai[30] = 0xfa0f3d63;
        ai[31] = 0x8d080df5;
        ai[32] = 0x3b6e20c8;
        ai[33] = 0x4c69105e;
        ai[34] = 0xd56041e4;
        ai[35] = 0xa2677172;
        ai[36] = 0x3c03e4d1;
        ai[37] = 0x4b04d447;
        ai[38] = 0xd20d85fd;
        ai[39] = 0xa50ab56b;
        ai[40] = 0x35b5a8fa;
        ai[41] = 0x42b2986c;
        ai[42] = 0xdbbbc9d6;
        ai[43] = 0xacbcf940;
        ai[44] = 0x32d86ce3;
        ai[45] = 0x45df5c75;
        ai[46] = 0xdcd60dcf;
        ai[47] = 0xabd13d59;
        ai[48] = 0x26d930ac;
        ai[49] = 0x51de003a;
        ai[50] = 0xc8d75180;
        ai[51] = 0xbfd06116;
        ai[52] = 0x21b4f4b5;
        ai[53] = 0x56b3c423;
        ai[54] = 0xcfba9599;
        ai[55] = 0xb8bda50f;
        ai[56] = 0x2802b89e;
        ai[57] = 0x5f058808;
        ai[58] = 0xc60cd9b2;
        ai[59] = 0xb10be924;
        ai[60] = 0x2f6f7c87;
        ai[61] = 0x58684c11;
        ai[62] = 0xc1611dab;
        ai[63] = 0xb6662d3d;
        ai[64] = 0x76dc4190;
        ai[65] = 0x1db7106;
        ai[66] = 0x98d220bc;
        ai[67] = 0xefd5102a;
        ai[68] = 0x71b18589;
        ai[69] = 0x6b6b51f;
        ai[70] = 0x9fbfe4a5;
        ai[71] = 0xe8b8d433;
        ai[72] = 0x7807c9a2;
        ai[73] = 0xf00f934;
        ai[74] = 0x9609a88e;
        ai[75] = 0xe10e9818;
        ai[76] = 0x7f6a0dbb;
        ai[77] = 0x86d3d2d;
        ai[78] = 0x91646c97;
        ai[79] = 0xe6635c01;
        ai[80] = 0x6b6b51f4;
        ai[81] = 0x1c6c6162;
        ai[82] = 0x856530d8;
        ai[83] = 0xf262004e;
        ai[84] = 0x6c0695ed;
        ai[85] = 0x1b01a57b;
        ai[86] = 0x8208f4c1;
        ai[87] = 0xf50fc457;
        ai[88] = 0x65b0d9c6;
        ai[89] = 0x12b7e950;
        ai[90] = 0x8bbeb8ea;
        ai[91] = 0xfcb9887c;
        ai[92] = 0x62dd1ddf;
        ai[93] = 0x15da2d49;
        ai[94] = 0x8cd37cf3;
        ai[95] = 0xfbd44c65;
        ai[96] = 0x4db26158;
        ai[97] = 0x3ab551ce;
        ai[98] = 0xa3bc0074;
        ai[99] = 0xd4bb30e2;
        ai[100] = 0x4adfa541;
        ai[101] = 0x3dd895d7;
        ai[102] = 0xa4d1c46d;
        ai[103] = 0xd3d6f4fb;
        ai[104] = 0x4369e96a;
        ai[105] = 0x346ed9fc;
        ai[106] = 0xad678846;
        ai[107] = 0xda60b8d0;
        ai[108] = 0x44042d73;
        ai[109] = 0x33031de5;
        ai[110] = 0xaa0a4c5f;
        ai[111] = 0xdd0d7cc9;
        ai[112] = 0x5005713c;
        ai[113] = 0x270241aa;
        ai[114] = 0xbe0b1010;
        ai[115] = 0xc90c2086;
        ai[116] = 0x5768b525;
        ai[117] = 0x206f85b3;
        ai[118] = 0xb966d409;
        ai[119] = 0xce61e49f;
        ai[120] = 0x5edef90e;
        ai[121] = 0x29d9c998;
        ai[122] = 0xb0d09822;
        ai[123] = 0xc7d7a8b4;
        ai[124] = 0x59b33d17;
        ai[125] = 0x2eb40d81;
        ai[126] = 0xb7bd5c3b;
        ai[127] = 0xc0ba6cad;
        ai[128] = 0xedb88320;
        ai[129] = 0x9abfb3b6;
        ai[130] = 0x3b6e20c;
        ai[131] = 0x74b1d29a;
        ai[132] = 0xead54739;
        ai[133] = 0x9dd277af;
        ai[134] = 0x4db2615;
        ai[135] = 0x73dc1683;
        ai[136] = 0xe3630b12;
        ai[137] = 0x94643b84;
        ai[138] = 0xd6d6a3e;
        ai[139] = 0x7a6a5aa8;
        ai[140] = 0xe40ecf0b;
        ai[141] = 0x9309ff9d;
        ai[142] = 0xa00ae27;
        ai[143] = 0x7d079eb1;
        ai[144] = 0xf00f9344;
        ai[145] = 0x8708a3d2;
        ai[146] = 0x1e01f268;
        ai[147] = 0x6906c2fe;
        ai[148] = 0xf762575d;
        ai[149] = 0x806567cb;
        ai[150] = 0x196c3671;
        ai[151] = 0x6e6b06e7;
        ai[152] = 0xfed41b76;
        ai[153] = 0x89d32be0;
        ai[154] = 0x10da7a5a;
        ai[155] = 0x67dd4acc;
        ai[156] = 0xf9b9df6f;
        ai[157] = 0x8ebeeff9;
        ai[158] = 0x17b7be43;
        ai[159] = 0x60b08ed5;
        ai[160] = 0xd6d6a3e8;
        ai[161] = 0xa1d1937e;
        ai[162] = 0x38d8c2c4;
        ai[163] = 0x4fdff252;
        ai[164] = 0xd1bb67f1;
        ai[165] = 0xa6bc5767;
        ai[166] = 0x3fb506dd;
        ai[167] = 0x48b2364b;
        ai[168] = 0xd80d2bda;
        ai[169] = 0xaf0a1b4c;
        ai[170] = 0x36034af6;
        ai[171] = 0x41047a60;
        ai[172] = 0xdf60efc3;
        ai[173] = 0xa867df55;
        ai[174] = 0x316e8eef;
        ai[175] = 0x4669be79;
        ai[176] = 0xcb61b38c;
        ai[177] = 0xbc66831a;
        ai[178] = 0x256fd2a0;
        ai[179] = 0x5268e236;
        ai[180] = 0xcc0c7795;
        ai[181] = 0xbb0b4703;
        ai[182] = 0x220216b9;
        ai[183] = 0x5505262f;
        ai[184] = 0xc5ba3bbe;
        ai[185] = 0xb2bd0b28;
        ai[186] = 0x2bb45a92;
        ai[187] = 0x5cb36a04;
        ai[188] = 0xc2d7ffa7;
        ai[189] = 0xb5d0cf31;
        ai[190] = 0x2cd99e8b;
        ai[191] = 0x5bdeae1d;
        ai[192] = 0x9b64c2b0;
        ai[193] = 0xec63f226;
        ai[194] = 0x756aa39c;
        ai[195] = 0x26d930a;
        ai[196] = 0x9c0906a9;
        ai[197] = 0xeb0e363f;
        ai[198] = 0x72076785;
        ai[199] = 0x5005713;
        ai[200] = 0x95bf4a82;
        ai[201] = 0xe2b87a14;
        ai[202] = 0x7bb12bae;
        ai[203] = 0xcb61b38;
        ai[204] = 0x92d28e9b;
        ai[205] = 0xe5d5be0d;
        ai[206] = 0x7cdcefb7;
        ai[207] = 0xbdbdf21;
        ai[208] = 0x86d3d2d4;
        ai[209] = 0xf1d4e242;
        ai[210] = 0x68ddb3f8;
        ai[211] = 0x1fda836e;
        ai[212] = 0x81be16cd;
        ai[213] = 0xf6b9265b;
        ai[214] = 0x6fb077e1;
        ai[215] = 0x18b74777;
        ai[216] = 0x88085ae6;
        ai[217] = 0xff0f6a70;
        ai[218] = 0x66063bca;
        ai[219] = 0x11010b5c;
        ai[220] = 0x8f659eff;
        ai[221] = 0xf862ae69;
        ai[222] = 0x616bffd3;
        ai[223] = 0x166ccf45;
        ai[224] = 0xa00ae278;
        ai[225] = 0xd70dd2ee;
        ai[226] = 0x4e048354;
        ai[227] = 0x3903b3c2;
        ai[228] = 0xa7672661;
        ai[229] = 0xd06016f7;
        ai[230] = 0x4969474d;
        ai[231] = 0x3e6e77db;
        ai[232] = 0xaed16a4a;
        ai[233] = 0xd9d65adc;
        ai[234] = 0x40df0b66;
        ai[235] = 0x37d83bf0;
        ai[236] = 0xa9bcae53;
        ai[237] = 0xdebb9ec5;
        ai[238] = 0x47b2cf7f;
        ai[239] = 0x30b5ffe9;
        ai[240] = 0xbdbdf21c;
        ai[241] = 0xcabac28a;
        ai[242] = 0x53b39330;
        ai[243] = 0x24b4a3a6;
        ai[244] = 0xbad03605;
        ai[245] = 0xcdd70693;
        ai[246] = 0x54de5729;
        ai[247] = 0x23d967bf;
        ai[248] = 0xb3667a2e;
        ai[249] = 0xc4614ab8;
        ai[250] = 0x5d681b02;
        ai[251] = 0x2a6f2b94;
        ai[252] = 0xb40bbe37;
        ai[253] = 0xc30c8ea1;
        ai[254] = 0x5a05df1b;
        ai[255] = 0x2d02ef8d;
        table = ai;
    }
}
