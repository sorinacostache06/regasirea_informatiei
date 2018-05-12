/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.analysis.Analyzer;
import java.util.Scanner;
import org.apache.lucene.document.TextField;
import org.apache.lucene.util.Version;
/**
 *
 * @author Gogosica
 */
public class Indexer{
    //este un tip de obiect care creeaza si pastreaza indexul
    private IndexWriter writer;
    
    public Indexer(String indexDirectoryPath) throws IOException{
        Path path = Paths.get(indexDirectoryPath);
        Directory indexDirectory = FSDirectory.open(path);
        Analyzer analyzer = new RomanianASCIIAnalyzer();
        analyzer.setVersion(Version.LUCENE_7_2_0);
        writer = new IndexWriter(indexDirectory, new IndexWriterConfig(analyzer));      
    }
    
    public void close() throws CorruptIndexException, IOException {
        writer.close();
    }
    
    
    private Document getDocument(File file) throws IOException {
        //pe obiectele de tip documnete de fac indexarea si searchul
        Document document = new Document();
        String content = new Scanner(new File(file.getPath())).useDelimiter("\\Z").next();
       String new_content;
        //System.out.println(content);
        StopWords stopwords = new StopWords();
        new_content = stopwords.removeStopWords(content);
        //System.out.println(new_content);
        
        String stem;
        Stemming stemming = new Stemming();
        stem = stemming.Stemming(new_content);
        
        //System.out.println(stem);
        
        //Field este o sectiune a Documentului -nume, valoare, tip
        //index file contents
        Field contentField = new Field(LuceneConstants.CONTENTS, stem, TextField.TYPE_NOT_STORED);
        
        //index file name
        Field fileNameField = new Field(LuceneConstants.FILE_NAME, file.getName(), TextField.TYPE_STORED);
        
        //index file path
        
        Field filePathField = new Field(LuceneConstants.FILE_PATH,
                file.getCanonicalPath(), TextField.TYPE_STORED);
        
        document.add(contentField);
        document.add(fileNameField);
        document.add(filePathField);
        
        return document;
    }
    
    private void indexFile(File file) throws IOException {
        //System.out.println("Indexeaza "+ file.getCanonicalPath());
        Document document = getDocument(file);
        
        writer.addDocument(document);
    }
    
    public int createIndex(String dataDirPath, FileFilter filter)
            throws IOException {
        File[] files = new File(dataDirPath).listFiles();
        
        for (File file : files) {
            if(!file.isDirectory() && !file.isHidden() && file.exists()
                    && file.canRead() && filter.accept(file)) {
                indexFile(file);
            }
        }
        return writer.numDocs();
    }
}

