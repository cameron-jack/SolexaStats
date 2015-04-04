/*
 * SolexaStats v0.1
 * Cameron Jack, Monica Gruber 2010. Victoria University of Wellington.
 *
 * Code released under GPL v3.0
 * 
 * SolexaStats implements Illumina's suggested quality baseline of the total FASTQ file
 * having a minimum of 80% of bases of Phred score 30 or better. Phred 30 corresponds to
 * p < 0.001 or the ^_`abcdefgh symbols.
 * 
 * Requires Java 1.5 or above.
 *
 */

package solexastats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class SolexaStats{

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: solexastats filename.ext");
            System.exit(0);
        }
        Stats stats_ = new Stats();
        stats_.allQualityLines_ = new Vector<String>();
        
	try {

            File inputDataFile_ = new File(args[0]);

            // The next line checks to if the output file has already been created and exits if it has
            //if (outputFile.exists()) { System.out.println("Output file already exists!"); System.exit(-1);}

            // create file readers and their buffers
            FileReader inputDataFileReader_ = new FileReader (inputDataFile_);
            BufferedReader inDataFileBR_ = new BufferedReader (inputDataFileReader_);

            try { // open data file

                String dataLine_ = null;

                int lineCounter_ = 0;
                Iterator dataItr_;
                char c_;

                while (inDataFileBR_.ready()) { // until the end of the file
                    if (lineCounter_ < stats_.MAXLINES_) {
                        dataLine_ = inDataFileBR_.readLine();
                        dataLine_ = dataLine_.trim();
                        lineCounter_++;
                        if (lineCounter_%4 == 0) { // the 4th line is the quality scores
                            stats_.allQualityLines_.add(dataLine_);
                        }
                    } else {
                        stats_.doWork();
                        lineCounter_ = 0;
                        stats_.allQualityLines_.clear();
                    }

                } // end read file
                stats_.doWork();
                
            } catch (IOException e) {
                System.out.println("File cannot be read from " + e);
            }

            try {
                inDataFileBR_.close();
            } catch (IOException e) {
                System.out.println("File close error " + e);
            }
        } catch (IOException e) {
            System.out.println("Problem opening input data file " + e);
        }

        float megaBases_ = (float) stats_.numBasesConsidered_ / 1000000;
        float proportionPassed_ = (float) stats_.numBasesOverS30_/ (float) stats_.numBasesConsidered_;

        System.out.println(megaBases_ + " Megabases evaluated in " + args[0]);
        System.out.println("The proportion of bases of Phred quality score 30 or greater is " + proportionPassed_);    
    }
}
