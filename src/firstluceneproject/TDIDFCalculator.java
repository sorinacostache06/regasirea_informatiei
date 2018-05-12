/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gogosica
 */

public class TDIDFCalculator {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf(List<String> doc, String term) {
        double result = 0;
//        System.out.println("doc: " + doc.toString());
        for (String word : doc) {
            
            if (term.equalsIgnoreCase(word))
                result++;
        }
        //System.out.println("result; " + result);
        //System.out.println("rez log: " + Math.log10(result));
        
        double tf = 1+ Math.log10(result);
        
        System.out.println("tf = " + tf);
        return tf;
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        System.out.println("idf = " + Math.log10(docs.size() / n));
        return Math.log10(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    public static File[] AspiratorFisiere(String folderPath)
    {
        return new File(folderPath).listFiles();
    }
    
    static String Folder = "C:\\Users\\Gogosica\\Documents\\Facultate\\Regasirea Informatiei\\Data";
    
    public void weightcal(String term) throws IOException {
        List<List<String>> documents = new ArrayList<List<String>>();
        Stemming stem =  new Stemming();
        String stemTerm = stem.Stemming(term);
        
        File[] files = AspiratorFisiere(Folder);
//        int[] sizes = new int[files.length];
        for(int i=0; i < files.length; i++) 
        {
            List<String> doc = get_Words(stem.Stemming(TDIDFCalculator.fileReader(files[i])));
            documents.add(doc);
        }
        
        TDIDFCalculator calculator = new TDIDFCalculator();
        for (int i = 0; i < documents.size(); i++)
        {
            //System.out.println(documents.get(i));
            double tfidf = calculator.tfIdf(documents.get(i), documents, stemTerm);
            System.out.println("TFIDF  = " + tfidf);
        }
    }
    
    public static String fileReader(File inFile) throws IOException {

    String filetext = "";
    BufferedReader reader = null;
    reader = new BufferedReader(new FileReader(inFile));
    String line = null;

    int numLine = 0;

    while ((line = reader.readLine()) != null) {
        filetext = filetext + " " + line;
    }

    reader.close();
    return filetext;
}
    
    
private static List<String> get_Words(String SInput){

    StringBuilder stringBuffer = new StringBuilder(SInput);
    ArrayList<String> all_Words_List = new ArrayList<String>();
    String SWord = "";
    for(int i=0; i < stringBuffer.length(); i++){
        Character charAt = stringBuffer.charAt(i);
        if(Character.isAlphabetic(charAt) || Character.isDigit(charAt)){
            SWord = SWord + charAt;
        }
        else{
            if(!SWord.isEmpty()) all_Words_List.add(new String(SWord));
            SWord = "";
        }
    }
    if(!SWord.isEmpty()) all_Words_List.add(new String(SWord));
        
    return all_Words_List;
}
}
