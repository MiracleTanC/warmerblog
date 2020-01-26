package warmer.star.blog.util.AutoAbstract;

import java.util.Iterator;

public class TextRank {
    String language = "1", type = "1", abNum = "100",
            stopwordPath = "stopwords.txt",
            stemmerOrNot = "1";
    String ReMethod = "1", RePara = "0.7", beta = "0";
    public String Summarize(String content)
    {
        Doc myDoc = new Doc();
        myDoc.maxlen = Integer.parseInt(abNum);
        myDoc.readContent(content, stopwordPath);

        /* Calculate similarity matrix of sentences */
        myDoc.calcTfidf(Integer.parseInt(type), Integer.parseInt(stemmerOrNot));
        myDoc.calcSim();
        double[][]  similarity = new double[myDoc.snum][myDoc.snum];
        for(int i = 0; i < myDoc.snum; ++i) {
            double sumISim= 0.0;
            for(int j = 0; j < myDoc.snum; ++j) {
                if(i == j) similarity[i][j] = 0.0;
                else {
                    int tmpNum = 0;
                    for(Iterator<Integer> iter = myDoc.sVector.get(i).iterator(); iter.hasNext(); ) {
                        int now = iter.next();
                        if(myDoc.sVector.get(j).contains(now)){
                            tmpNum++;
                        }
                    }
                    similarity[i][j] = tmpNum / ( Math.log(1.0 * myDoc.senLen.get(i)) + Math.log(1.0 * myDoc.senLen.get(j)));
                }
                sumISim += similarity[i][j];
            }

            /* Normalization the similarity matrix by row */
            for(int j = 0; j < myDoc.snum; ++j) {
                if(sumISim == 0.0) {
                    similarity[i][j] = 0.0;
                }else {
                    similarity[i][j] = similarity[i][j] / sumISim;
                }
            }
        }

        //Calculate the TextRank score of sentences
        double[] uOld = new double[myDoc.snum];
        double[] u = new double[myDoc.snum];
        for(int i = 0; i < myDoc.snum; ++i) {
            uOld[i] = 1.0;
            u[i] = 1.0;
        }

        double eps = 0.00001, alpha = 0.85 , minus = 1.0;

        while (minus > eps) {
            uOld = u.clone();
            for (int i = 0; i < myDoc.snum; i++) {
                double sumSim = 0.0;
                for (int j = 0; j < myDoc.snum; j++) {
                    if(j == i) continue;
                    else {
                        sumSim = sumSim + similarity[j][i] * uOld[j];
                    }

                }
                u[i] = alpha * sumSim + (1 - alpha);
            }
            minus = 0.0;
            for (int j = 0; j < myDoc.snum; j++) {
                double add = java.lang.Math.abs(u[j] - uOld[j]);
                minus += add;
            }
        }

        /* Set redundancy removal method and parameter */
        double threshold = 0.9, Beta = 0.1;

        if (Double.parseDouble(RePara)>=0){
            threshold = Double.parseDouble(RePara);
        }
        if (Double.parseDouble(beta)>=0){
            Beta = Double.parseDouble(beta);
        }

        /* Remove redundancy and get the abstract */
        if (ReMethod.equals("-1"))
            myDoc.pickSentenceMMR(u, threshold, Beta);
        else if (ReMethod.equals("1"))
            myDoc.pickSentenceMMR(u, threshold, Beta);
        else if (ReMethod.equals("2"))
            myDoc.pickSentenceThreshold(u, threshold, Beta);
        else if (ReMethod.equals("3"))
            myDoc.pickSentenceSumpun(u, threshold);

        StringBuilder builder=new StringBuilder();
        for (int i : myDoc.summaryId){
            builder.append(myDoc.originalSen.get(i));
        }
        return builder.toString();
    }
}
