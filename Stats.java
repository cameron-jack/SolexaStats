package solexastats;

//
// Performs all calculations and holds values till the end of file
//

import java.util.Iterator;
import java.util.Vector;

class Stats {
    public long numBasesConsidered_ = 0;
    public long numBasesOverS30_ = 0;
    public final int MAXLINES_ = 1000000;
    public Vector<String> allQualityLines_;

    public void doWork() {
        Iterator dataItr_ = allQualityLines_.iterator();
        String dataLine_; char c_;
        while (dataItr_.hasNext()) {
            dataLine_ = (String) dataItr_.next();
            for (int i = 0; i < dataLine_.length(); i++) {
                numBasesConsidered_++;
                c_ = dataLine_.charAt(i);
                switch(c_) {
                    // FASTQ symbol, ASCII number, Phred score, p value
                    //case ';':	//-5	0.7597
                    //case '<':	//-4	0.7153
                    //case '=':	//-3	0.6661
                    //case '>':	//-2	0.6131
                    //case '?':	//-1	0.5573
                    //case '@':	//0	0.5000
                    //case 'A':	//1	0.4427
                    //case 'B':	//2	0.3869
                    //case 'C':	//3	0.3339
                    //case 'D':	//4	0.2847
                    //case 'E':	//5	0.2403
                    //case 'F':	//6	0.2008
                    //case 'G':	//7	0.1663
                    //case 'H':	//8	0.1368
                    //case 'I':	//9	0.1118
                    //case 'J':	//10	0.0909
                    //case 'K':	//11	0.0736
                    //case 'L':	//12	0.0594
                    //case 'M':	//13	0.0477
                    //case 'N':	//14	0.0383
                    //case 'O':	//15	0.0307
                    //case 'P':	//16	0.0245
                    //case 'Q':	//17	0.0196
                    //case 'R':	//18	0.0156
                    //case 'S':	//19	0.0124
                    //case 'T': //20	0.0099
                    //case 'U':	//21	0.0079
                    //case 'V':	//22	0.0063
                    //case 'W':	//23	0.0050
                    //case 'X':	//24	0.0040
                    //case 'Y':	//25	0.0032
                    //case 'Z':	//26	0.0025
                    //case '[':	//27	0.0020
                    //case '\\':	//28	0.0016
                    //case ']':	//29	0.0013
                    case '^': numBasesOverS30_++; break;//	30	0.0010
                    case '_': numBasesOverS30_++; break;//	31	0.0008
                    case '`': numBasesOverS30_++; break;//	32	0.0006
                    case 'a': numBasesOverS30_++; break;//	33	0.0005
                    case 'b': numBasesOverS30_++; break;//	34	0.0004
                    case 'c': numBasesOverS30_++; break;//	35	0.0003
                    case 'd': numBasesOverS30_++; break;//	36	0.0003
                    case 'e': numBasesOverS30_++; break;//	37	0.0002
                    case 'f': numBasesOverS30_++; break;//	38	0.0002
                    case 'g': numBasesOverS30_++; break;//	39	0.0001
                    case 'h': numBasesOverS30_++; break;//      40	0.0001
                }
            }
        }
    }
}