package org.aoju.bus.extra.locale;

/**
 * @author Kimi Liu
 * @version 5.5.0
 * @since JDK 1.8+
 */
public class HZ extends Verifier {

    static int[] cclass;
    static int[] states;
    static int stFactor;
    static String charset;

    public HZ() {
        cclass = new int[256 / 8];

        cclass[0] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (1)))))))));
        cclass[1] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[2] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[3] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((1) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[4] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[5] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[6] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[7] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[8] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[9] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[10] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[11] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[12] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[13] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[14] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0))))))) << 16) | (((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[15] = ((((((((((((0) << 4) | (2)))) << 8) | (((((5) << 4) | (0))))))) << 16) | (((((((((4) << 4) | (0)))) << 8) | (((((0) << 4) | (0)))))))));
        cclass[16] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[17] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[18] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[19] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[20] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[21] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[22] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[23] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[24] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[25] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[26] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[27] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[28] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[29] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[30] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[31] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));

        states = new int[6];

        states[0] = ((((((((((((eError) << 4) | (eError)))) << 8) | (((((eStart) << 4) | (eStart))))))) << 16) | (((((((((eStart) << 4) | (3)))) << 8) | (((((eError) << 4) | (eStart)))))))));
        states[1] = ((((((((((((eItsMe) << 4) | (eItsMe)))) << 8) | (((((eItsMe) << 4) | (eItsMe))))))) << 16) | (((((((((eError) << 4) | (eError)))) << 8) | (((((eError) << 4) | (eError)))))))));
        states[2] = ((((((((((((eError) << 4) | (4)))) << 8) | (((((eStart) << 4) | (eStart))))))) << 16) | (((((((((eError) << 4) | (eError)))) << 8) | (((((eItsMe) << 4) | (eItsMe)))))))));
        states[3] = ((((((((((((eError) << 4) | (4)))) << 8) | (((((5) << 4) | (5))))))) << 16) | (((((((((eError) << 4) | (6)))) << 8) | (((((eError) << 4) | (5)))))))));
        states[4] = ((((((((((((eError) << 4) | (4)))) << 8) | (((((eError) << 4) | (4))))))) << 16) | (((((((((4) << 4) | (4)))) << 8) | (((((eError) << 4) | (4)))))))));
        states[5] = ((((((((((((eStart) << 4) | (eStart)))) << 8) | (((((eStart) << 4) | (eStart))))))) << 16) | (((((((((eStart) << 4) | (eStart)))) << 8) | (((((eItsMe) << 4) | (4)))))))));

        charset = "HZ-GB-2312";
        stFactor = 6;
    }

    public int[] cclass() {
        return cclass;
    }

    public int[] states() {
        return states;
    }

    public int stFactor() {
        return stFactor;
    }

    public String charset() {
        return charset;
    }

    public boolean isUCS2() {
        return false;
    }

}
