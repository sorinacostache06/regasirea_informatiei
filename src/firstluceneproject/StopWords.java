/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.TokenFilter ;
import org.apache.lucene.analysis.tokenattributes.*;
import java.io.StringReader;

/**
 *
 * @author Gogosica
 */
public class StopWords {
    
    public String removeStopWords(String textFile) throws IOException {
    //store string in a hash
    CharArraySet stopWords = RomanianAnalyzer.getDefaultStopSet();
    
    //enumerates the sequence of tokens
    TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46, new StringReader(textFile.trim()));
    
    //removes stop works from a string
    tokenStream = new StopFilter(Version.LUCENE_46, tokenStream, stopWords);
    
    StringBuilder sb = new StringBuilder();
    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
    tokenStream.reset();
    while (tokenStream.incrementToken()) {
        String term = charTermAttribute.toString();
        sb.append(term + " ");
    }
    return sb.toString();
}
}
