import java.util.ArrayList;
 
public class bnet {
    static ArrayList<String> queryList = new ArrayList<String>();
    static ArrayList<String> observation = new ArrayList<String>();
 
    public static void main(String[] args) {
        String[] query = args;
        if (query.length == 0) {
            System.exit(0);
        }
 
        int givenI = 10;
        for (int i = 0; i < query.length; i++) {
            if (query[i].equals("given")) {
                givenI = i;
                break;
            }
        }
 
        for (int i = 0; i < query.length; i++) {
            if (query[i].equals("given"))
                continue;
            if (i < givenI) {
                queryList.add(query[i]);
            } else {
                observation.add(query[i]);
            }
 
        }
        if (givenI != 10) {
            if (observation.size() < 1 || observation.size() > 4) {
                System.exit(0);
            }
        }
 
        queryList.addAll(observation);
 
        double numt = calcNumerator();
        if (observation.size() == 0) {
            System.out.println("Computed Probability: " + numt);
        }
       
        double dent = calcDenominator();
        if (observation.size() > 0) {
            System.out.println("Computed Probability: " + numt / dent);
        }
 
    }
 
    static double calcNumerator() {
        int countB = 0, countE = 0, countA = 0, countJ = 0, countM = 0;
        int i = 0;
        while (i < queryList.size()) {
            if (!queryList.contains("Bt") && !queryList.contains("Bf")) {
                queryList.add("Bt");
                queryList.add("Bf");
                countB = 1;
            }
            if (!queryList.contains("Et") && !queryList.contains("Ef")) {
                queryList.add("Et");
                queryList.add("Ef");
                countE = 1;
            }
            if (!queryList.contains("At") && !queryList.contains("Af")) {
                queryList.add("At");
                queryList.add("Af");
                countA = 1;
            }
            if (!(queryList.contains("Jt")) && !queryList.contains("Jf")) {
                queryList.add("Jt");
                queryList.add("Jf");
                countJ = 1;
            }
            if (!queryList.contains("Mt") && !queryList.contains("Mf")) {
                queryList.add("Mt");
                queryList.add("Mf");
                countM = 1;
            }
            i++;
        }
 
        double numt = genValues(countB, countE, countA, countJ, countM, queryList);
 
        return numt;
    }
 
    static double calcDenominator() {
        int countB = 0, countE = 0, countA = 0, countJ = 0, countM = 0;
        int j = 0;
        while (j < observation.size()) {
 
            if (!observation.contains("Bt") && !observation.contains("Bf")) {
                observation.add("Bt");
                observation.add("Bf");
                countB = 1;
            }
            if (!observation.contains("Et") && !observation.contains("Ef")) {
                observation.add("Et");
                observation.add("Ef");
                countE = 1;
            }
            if (!observation.contains("At") && !observation.contains("Af")) {
                observation.add("At");
                observation.add("Af");
                countA = 1;
            }
            if (!observation.contains("Jt") && !observation.contains("Jf")) {
                observation.add("Jt");
                observation.add("Jf");
                countJ = 1;
            }
            if (!observation.contains("Mt") && !observation.contains("Mf")) {
                observation.add("Mt");
                observation.add("Mf");
                countM = 1;
            }
            j++;
        }
 
        double dent = genValues(countB, countE, countA, countJ, countM, observation);
        return dent;
    }
    public static double genValues(int bCount, int eCount, int aCount, int jCount, int mCount, ArrayList<String> queryArr) {
        double probability = 0.0;
        Boolean b_bool = false, e_bool = false, a_bool = false, j_bool = false, m_bool = false;
        if (bCount == 0) {
            if (queryArr.contains("Bt")) {
                b_bool = true;
            } else
                b_bool = false;
        }
        if (eCount == 0) {
            if (queryArr.contains("Et")) {
                e_bool = true;
            } else
                e_bool = false;
        }
        if (aCount == 0) {
            if (queryArr.contains("At")) {
                a_bool = true;
            } else
                a_bool = false;
        }
        if (jCount == 0) {
            if (queryArr.contains("Jt")) {
                j_bool = true;
            } else
                j_bool = false;
        }
        if (mCount == 0) {
            if (queryArr.contains("Mt")) {
                m_bool = true;
            } else
                m_bool = false;
        }
        for (int i1 = 0; i1 <= bCount; i1++) {
            for (int i2 = 0; i2 <= eCount; i2++) {
                for (int i3 = 0; i3 <= aCount; i3++) {
                    for (int i4 = 0; i4 <= jCount; i4++) {
                        for (int i5 = 0; i5 <= mCount; i5++) {
                            probability += BayesianNetwork.computeProbability(b_bool, e_bool, a_bool, j_bool, m_bool);
                            if (mCount == 1 && m_bool == false)
                                m_bool = true;
                            else if (mCount == 1 && m_bool == true)
                                m_bool = false;
                        }
                        if (jCount == 1 && j_bool == false)
                            j_bool = true;
                        else if (jCount == 1 && j_bool == true)
                            j_bool = false;
                    }
                    if (aCount == 1 && a_bool == false)
                        a_bool = true;
                    else if (aCount == 1 && a_bool == true)
                        a_bool = false;
                }
                if (eCount == 1 && e_bool == false)
                    e_bool = true;
                else if (eCount == 1 && e_bool == true)
                    e_bool = false;
            }
            if (bCount == 1 && b_bool == false)
                b_bool = true;
            else if (bCount == 1 && b_bool == true)
                b_bool = false;
        }
        return probability;
    }
 
}
 
class BayesianNetwork {
    static double B = 0.001;
    static double E = 0.002;
    static double A[] = { 0.95, 0.94, 0.29, 0.001 };
    static double J[] = { 0.90, 0.05 };
    static double M[] = { 0.70, 0.01 };
 
    public static double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m) {
        double Mval = 0.0;
        if (m) {
            if (a == true)
                Mval = M[0];
            else if (a == false)
                Mval = M[1];
        }
 
        else {
            if (a == true)
                Mval = 1 - M[0];
            else if (a == false)
                Mval = 1 - M[1];
        }
        double Bval = 0.0;
        if (b) {
            Bval = B;
        } else {
            Bval = 1 - B;
        }
        double Aval = 0.0;
        if (a) {
            if (b == true && e == true)
                Aval = A[0];
            else if (b == true && e == false)
                Aval = A[1];
            else if (b == false && e == true)
                Aval = A[2];
            else if (b == false && e == false)
                Aval = A[3];
        } else {
            if (b == true && e == true)
                Aval = 1 - A[0];
            else if (b == true && e == false)
                Aval = 1 - A[1];
            else if (b == false && e == true)
                Aval = 1 - A[2];
            else if (b == false && e == false)
                Aval = 1 - A[3];
        }
        double Eval;
        if (e) {
            Eval = E;
        } else {
            Eval = 1 - E;
        }
 
        double Jval = 0.0;
        if (j) {
            if (a == true)
                Jval = J[0];
            else if (a == false)
                Jval = J[1];
        }
 
        else {
            if (a == true)
                Jval = 1 - J[0];
            else if (a == false)
                Jval = 1 - J[1];
        }
 
        return Bval * Eval * Aval * Jval * Mval;
    }
 
}