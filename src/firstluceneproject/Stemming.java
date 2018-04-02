/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import java.io.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.analysis.snowball.*;
import org.apache.lucene.util.*;
/**
 *
 * @author Gogosica
 */
public class Stemming {
    public String Stemming(String text) throws IOException{
        StringBuffer result = new StringBuffer();
        if (text != null && text.trim().length()>0){
            StringReader tReader = new StringReader(text);
            RomanianAnalyzer analyzer = new RomanianAnalyzer();
            TokenStream tStream = analyzer.tokenStream("contents", tReader);
            CharTermAttribute term = tStream.addAttribute(CharTermAttribute.class);
            tStream.reset();
            try {
                while (tStream.incrementToken()){
                    result.append(term.toString());
                    result.append(" ");
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }

        // If, for some reason, the stemming did not happen, return the original text
        if (result.length()==0)
            result.append("nimic");
        return result.toString().trim();
    }
}
