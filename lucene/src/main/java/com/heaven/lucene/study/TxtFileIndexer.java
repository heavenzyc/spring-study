package com.heaven.lucene.study;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Date;

/**
 * Created by heaven.zyc on 2016/8/4.
 */
public class TxtFileIndexer {

    public static void main(String[] args) throws Exception{

        Directory dir = FSDirectory.open(new File("f:\\lucene\\indies"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,iwc);

        File dataDir  = new File("f:\\lucene\\data");
        File[] dataFiles  = dataDir.listFiles();

        long startTime = new Date().getTime();
        for(int i = 0; i < dataFiles.length; i++){
            if(dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")){
                System.out.println("Indexing file " + dataFiles[i].getCanonicalPath());
                Document document = new Document();
                Reader txtReader = new FileReader(dataFiles[i]);
                document.add(new StringField("path",dataFiles[i].getCanonicalPath(),Field.Store.YES));
                document.add(new TextField("contents",txtReader));
                indexWriter.addDocument(document);
            }
        }
        indexWriter.close();
        long endTime = new Date().getTime();
        System.out.println("It takes " + (endTime - startTime)
                + " milliseconds to create index for the files in directory "
                + dataDir.getPath());
    }
}
