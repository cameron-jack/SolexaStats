# SolexaStats v0.1
# Cameron Jack, Monica Gruber 2010. Victoria University of Wellington.
#
# SolexaStats implements Illumina's suggest quality baseline of the total FASTQ file
# having a minimum of 80% of bases of Phred score 30 or better. This corresponds to
# p < 0.001 or the ^_`abcdefgh symbols.
#
# Required Python 2.x

import sys

def main():
    """
        Usage: python solexastats.py sequences.fastq [phred64]
    """

    if len(sys.argv) < 2 or len(sys.argv) > 3:
        print 'Usage: python solexastats.py sequences.fastq [phred64]'
        sys.exit()

    seqfile = sys.argv[1]
    encoding = 33
    if len(sys.argv) == 3:
        encoding = 64

    numBasesOverS30 = 0
    numBasesConsidered = 0
    numLines = 0

    with open(seqfile, 'r') as sf:
        for i,line in enumerate(sf):
            if i%4 == 3:
                # quality scores
                quals = map(ord, line)
                for q in quals:
                    if q-encoding >= 30:
                        numBasesOverS30 += 1
                    numBasesConsidered += 1
            numLines += 1

    p = numBasesOverS30/float(numBasesConsidered)
    print 'Number of fragments considered', numLines
    print 'Proportion of bases of quality score Phred 30 or greater', p

if __name__ == '__main__':
    main()