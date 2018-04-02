/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.util.AttributeFactory;
import org.apache.lucene.util.AttributeSource;

/**
 *
 * @author Gogosica
 */
public class StopWords {
    
    public String removeStopWords(String textFile) throws IOException {
    AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
    //store string in a hash
    CharArraySet stopWords = RomanianAnalyzer.getDefaultStopSet();
    
    //enumerates the sequence of tokens
    StandardTokenizer tokenStream = new StandardTokenizer(factory);
    tokenStream.setReader(new StringReader(textFile));
    
        //removes stop works from a string
    StopFilter stopFilter = new StopFilter(tokenStream, stopWords);
    tokenStream.reset();
    StringBuilder sb = new StringBuilder();
    CharTermAttribute charTermAttribute = stopFilter.addAttribute(CharTermAttribute.class);
    while (stopFilter.incrementToken()) {
        String term = charTermAttribute.toString();
        sb.append(term + " ");
    }
    stopFilter.reset();
    return sb.toString();
}
}
