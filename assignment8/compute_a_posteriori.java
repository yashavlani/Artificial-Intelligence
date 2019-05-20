/**
 * @Name : Yash Avlani | UTA ID : 1001670008 | Net ID : yba0008 

 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class compute_a_posteriori {
    
    static String outputToFile="";
    static List<Double> priorVal = Arrays.asList(0.1,0.2,0.4,0.2,0.1);      
    static List<Double> cherryProbList = Arrays.asList(1.0,0.75,0.50,0.25,0.0);
    static List<Double> limeProbList = Arrays.asList(0.0,0.25,0.50,0.75,1.0);
    
    static void getOutput(char ob) {
        List<Double> priorProbList = priorVal;  
        Double alphaC=0.0,alphaL=0.0;

        for(int j=0;j<5;j++) {
            alphaC+= (priorProbList.get(j)) * cherryProbList.get(j);
            alphaL+=(priorProbList.get(j)) * limeProbList.get(j);
        }
        
        if(ob == 'C') {
            for(int j=0;j<5;j++) {
                Double temp=((priorProbList.get(j)) * cherryProbList.get(j))/alphaC;

                priorProbList.set(j, temp);
                outputToFile+="\nP(h"+(j+1)+" | Q) = "+String.format("%.10f", temp);
            }
        }
        else {
            for(int j=0;j<5;j++) {
                Double temp=((priorProbList.get(j)) * limeProbList.get(j))/alphaL;
                priorProbList.set(j, temp);
                outputToFile+="\nP(h"+(j+1)+" | Q) = "+String.format("%.10f", temp);

            }
        }

        Double nextC=0.0;
        Double nextL=0.0;
        /**Calculating Pt(Qi+1=C or L)*/
        for(int j=0;j<5;j++) {
            nextC+= (priorProbList.get(j)) * cherryProbList.get(j);
            nextL+=(priorProbList.get(j)) * limeProbList.get(j);
        }

        outputToFile+="\nProbability that the next candy we pick will be C, given Q: "+String.format("%.10f", nextC);
        outputToFile+="\nProbability that the next candy we pick will be L, given Q: "+String.format("%.10f", nextL);
    }

    public static void writeToFile(String result) {
        System.out.println(result);
        try{
            // Create file
            FileWriter outFile = new FileWriter("result.txt",false);
            BufferedWriter out = new BufferedWriter(outFile);

            out.write(result);
 

            //Close the output stream
            out.close();
            outFile.close();
        }catch (Exception e){//Catch exception if any
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        
        String observation = args[0];

        if(args.length != 1) {
            System.out.println("Usage: java compute_a_posteriori {observation string}\n");
            System.exit(0);
        }
        
        outputToFile="Observation sequence Q: "+observation;
        outputToFile+="\nLength of Q: "+observation.length();

        /**
         * Repeated for each observation
         */
        for(int i=1;i<=observation.length();i++) {
            outputToFile+="\n\nAfter Observation "+i +" = "+observation.charAt(i-1);
            
            String subString = observation.substring(0, i);
            
            getOutput(observation.charAt(i-1));
        }
        writeToFile(outputToFile);
    }

}