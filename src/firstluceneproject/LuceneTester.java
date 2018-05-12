/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 *
 * @author Gogosica
 */
public class LuceneTester {
    String indexDir = "C:\\Users\\Gogosica\\Documents\\Facultate\\Regasirea Informatiei\\Index";
    String dataDir = "C:\\Users\\Gogosica\\Documents\\Facultate\\Regasirea Informatiei\\Data";
    Indexer indexer;
    Searcher searcher;
    Stemming stemming;
    TDIDFCalculator tfidf;
    
    public static void main(String[] args) throws ParseException {
      LuceneTester tester;
      try {
         tester = new LuceneTester();
         tester.deleteIndex("C:\\Users\\Gogosica\\Documents\\Facultate\\Regasirea Informatiei\\Index");
         tester.createIndex();
         System.out.println("Introduceti termenii: ");
         Scanner sc = new Scanner(System.in);
         String terms = sc.nextLine();
         
         String[] ary = terms.split(" ");
         
         for (int i = 0; i < ary.length; i++) {
            System.out.println("Rezultatele obtinute pentru " + ary[i] + ":");
            tester.search(ary[i]);
            TDIDFCalculator calculator = new TDIDFCalculator();
            calculator.weightcal(ary[i]);
         }
        
      } catch (IOException e) {
         e.printStackTrace();
      } 
   }
    
    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        //indexer.deleteIndex(indexDir);
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        indexer.close();
        System.out.println(numIndexed + " programe indexate");
    }
    
    private void search(String searchString) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        TopDocs results = searcher.search(searchString);
        searcher.calculateTFIDF(dataDir, results);
        
        System.out.println(results.totalHits + " documente gasite ");
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
            + doc.get(LuceneConstants.FILE_PATH));
        }
    }
    
//    private void stem(String stemString) throws IOException {
//        stemming = new Stemming();
//        System.out.println(stemming.Stemming(stemString));
//    }
    
    public void deleteIndex(String dataDirPath)
            throws IOException {
        File[] files = new File(dataDirPath).listFiles();
        for(int i=0; i < files.length; i++) 
            files[i].delete();
    }
}


















