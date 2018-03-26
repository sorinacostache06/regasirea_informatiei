/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.util.Version;
/**
 *
 * @author Gogosica
 */
public class Searcher {
    //face search peste un sngur indexReader
    IndexSearcher indexSearcher;
    QueryParser queryParser;
    Query query;
    
    public Searcher(String indexDirectoryPath) throws IOException {
        Directory dir = FSDirectory.open(new File(indexDirectoryPath));
        DirectoryReader indexDirectory = DirectoryReader.open(dir);
        indexSearcher = new IndexSearcher(indexDirectory);
        queryParser = new QueryParser(Version.LUCENE_46, LuceneConstants.CONTENTS,
            new RomanianAnalyzer(Version.LUCENE_46));
    }
    
    //top docs returneaza numarul total de rezultate
    public TopDocs search(String searchQuery) throws IOException, ParseException {
        query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
    }
    
//    public Document getDocument(ScoreDoc scoreDoc)
//            throws IOException {
//        return indexSearcher.doc(scoreDoc.doc);
//    }
    
    public void close() throws IOException {
        //
    }
}





















