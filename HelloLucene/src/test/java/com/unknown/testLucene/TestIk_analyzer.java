package com.unknown.testLucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;

public class TestIk_analyzer {

    public static void main(String[] args) throws Exception{

        Document document = new Document();

        document.add(new TextField("msg","你好，我想认识你", Field.Store.YES));

        Analyzer analyzer = new IKAnalyzer();

        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);

        FSDirectory directory = FSDirectory.open(Paths.get("D:\\test\\testLucene"));

        IndexWriter writer = new IndexWriter(directory, writerConfig);

        writer.addDocument(document);

        writer.close();

    }

}
