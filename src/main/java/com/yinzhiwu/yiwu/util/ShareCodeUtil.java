package com.yinzhiwu.yiwu.util;

import java.util.Random;

/**
 * 邀请码生成器，算法原理：<br>
 * 1) 获取id: 1127738 <br>
 * 2) 使用自定义进制转为：gpm6 <br>
 * 3) 转为字符串，并在后面加'o'字符：gpm6o <br>
 * 4）在后面随机产生若干个随机数字字符：gpm6o7 <br>
 * <p>
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'o'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。<br> </p>
 * @author jiayu.qiu
 */
public class ShareCodeUtil {

    /** 自定义进制(0,1没有加入,容易与o,l混淆) */
    private static final char[] r=new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};

    /** (不能与自定义进制有重复) */
    private static final char b='o';

    /** 进制长度 */
    private static final int binLength=r.length;

    /** 序列最小长度 */
    private static final int serialLength=8;

    /**
     * 根据ID生成{@link ShareCodeUtil#serialLength}位随机码
     * @param id id
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf=new char[32];
        int charPos=32;

        while((id / binLength) > 0) {
            int ind=(int)(id % binLength);
            buf[--charPos]=r[ind];
            id /= binLength;
        }
        buf[--charPos]=r[(int)(id % binLength)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if(str.length() < serialLength) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < serialLength - str.length(); i++) {
            sb.append(r[rnd.nextInt(binLength)]);
            }
            str+=sb.toString();
        }
        return str;
    }

    /**
     * 
     * @param code the share code
     * @return return a long value that used to generate the  random code in method {@link ShareCodeUtil#toSerialCode}
     */
    public static long codeToId(String code) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLength; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLength + ind;
            } else {
                res=ind;
            }
            // System.out.println(ind + "-->" + res);
        }
        return res;
    }
}