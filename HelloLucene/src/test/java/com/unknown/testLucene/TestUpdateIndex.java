package com.unknown.testLucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class TestUpdateIndex {

    public static void main(String[] args) throws Exception{

        testDelete();

    }

    public static void testDelete() throws Exception{

        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        FSDirectory directory = FSDirectory.open(Paths.get("D:\\test\\testLucene"));
        IndexWriter writer = new IndexWriter(directory, config);
        writer.deleteDocuments(new Term("id","1"));
        writer.close();

    }

    public static void testUpdate() throws IOException {
        Document document = new Document();
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("name", "臭小慧", Field.Store.YES));
        document.add(new IntPoint("age", 11));
        document.add(new StringField("gender", "女", Field.Store.YES));
        document.add(new TextField("address","安徽", Field.Store.YES));
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get("D:\\test\\testLucene"));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        //参数一：修改条件，参数二：修改成的内容
        indexWriter.updateDocument(new Term("id","1"),document);
        indexWriter.close();
    }
}
