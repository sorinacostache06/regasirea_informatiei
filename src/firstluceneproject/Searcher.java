/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.index.PostingsEnum;
/**
 *
 * @author Gogosica
 */
public class Searcher {
    //face search peste un singur indexReader
    IndexSearcher indexSearcher;
    QueryParser queryParser;
    Query query;
    Directory dir;
    
    public Searcher(String indexDirectoryPath) throws IOException {
        Path path = Paths.get(indexDirectoryPath);
        dir = FSDirectory.open(path);
        DirectoryReader indexDirectory = DirectoryReader.open(dir);
        indexSearcher = new IndexSearcher(indexDirectory);
        queryParser = new QueryParser(LuceneConstants.CONTENTS, new RomanianASCIIAnalyzer());
    }
    
    //top docs returneaza numarul total de rezultate
    public TopDocs search(String searchQuery) throws IOException, ParseException {
        query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
    }
    
    public Document getDocument(ScoreDoc scoreDoc)
            throws IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }
    
    public void calculateTFIDF(String dataDirPath, TopDocs results) throws IOException {
        IndexReader re =  indexSearcher.getIndexReader();
//        System.out.println("here");
//        File[] files = new File(dataDirPath).listFiles();
//        for (int k = 0; k< files.length; k++)
//          Terms terms = re.getTermVector(results.scoreDocs[1].doc, LuceneConstants.FILE_NAME); 
//          if (terms != null && terms.size() > 0) {
//                
//
//                TermsEnum termsEnum = terms.iterator(); 
//                BytesRef term = null;
//
//                while ((term = termsEnum.next()) != null) {
//        // enumerate through documents, in this case only one
//                term = termsEnum.term();
//                PostingsEnum docsEnum = termsEnum.postings(null);
//                int docIdEnum;
//                while ((docIdEnum = docsEnum.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
//                    // get the term frequency in the document 
//                    System.out.println(term.utf8ToString()+ " " + docIdEnum + " " + docsEnum.freq()); 
//                }
//            }
//        }

        for (int i=0; i<results.scoreDocs.length; i++){
     Terms termVector = indexSearcher.getIndexReader().getTermVector(results.scoreDocs[i].doc, "barcuta");
     Document doc = indexSearcher.doc(results.scoreDocs[i].doc);
//     documentsList.put(doc, termVector);
}
    }
    
    public void close() throws IOException {
        //
    }
}





















