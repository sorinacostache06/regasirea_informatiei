/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.IOException;
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
    
    public static void main(String[] args) {
      LuceneTester tester;
      try {
         tester = new LuceneTester();
         tester.createIndex();
         tester.search("mama");
         StopWords stopwords = new StopWords();
         String text = "Aceasta este o propozitie cu multe cuvinte de legatura!";
         System.out.println(stopwords.removeStopWords(text));
//         tester.stem("mam");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException p){
         p.printStackTrace();
      }
   }
    
    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        indexer.close();
        System.out.println(numIndexed + " programe indexate");
    }
    
    private void search(String searchString) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        TopDocs results = searcher.search(searchString);
        
        System.out.println(results.totalHits + " documente gasite ");
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
            + doc.get(LuceneConstants.FILE_PATH));
        }
    }
    
    private void stem(String stemString) throws IOException {
        stemming = new Stemming();
        System.out.println(stemming.Stemming(stemString));  
    }
}


















