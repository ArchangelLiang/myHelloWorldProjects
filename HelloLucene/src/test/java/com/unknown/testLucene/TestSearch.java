package com.unknown.testLucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.HashMap;

public class TestSearch {
    //分词器
    private static Analyzer analyzer;
    //查询对象
    private static Query query;

    @Before//此处抽取每种查询之前要执行的代码
    public void testSearchBefore(){
        //创建分词器（对搜索的关键字进行分词使用）注意：该分词器必需与创建索引时使用的分词器一致
        analyzer = new StandardAnalyzer();
    }

    @Test//组合查询
    public void testBooleanSearchBefore() throws Exception{
        QueryParser parser = new QueryParser("name", analyzer);
        Query query1 = parser.parse("address:上海");
        Query query2 = IntPoint.newRangeQuery("age", 10, 18);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        // BooleanClause.Occur.MUST 相当于AND，就是必需满足的条件
        // BooleanClause.Occur.SHOULD 相当于OR，就是可以满足的条件
        // BooleanClause.Occur.MUST_NOT，就是必需不能满足的条件
        //注意：如果所有查询条件都是MUST_NOT则查询不出任何数据
        builder.add(query1, BooleanClause.Occur.MUST);
        builder.add(query2,BooleanClause.Occur.MUST);
        query = builder.build();
    }

    @Test//范围查询
    public void testRangeSearchBefore(){
        //创建范围搜索对象，因为age字段创建索引时使用的是Int类型，所以此处也是有IntPoint来创建搜索对象
        query = IntPoint.newRangeQuery("age", 10, 18);
    }

    @Test//文本查询
    public void testTextSearchBefore() throws Exception{
        //创建文本查询对象,第一个参数是默认查询域(如果搜索的关键字中指定了查询域名，就从指定的域中查，否则从该处设置的默认域查询)，第二个参数是使用的分词器
        QueryParser queryParser = new QueryParser("name", analyzer);
        //用来增加某个字段的权重，使该字段占据的相关度影响力更高，从而排名更靠前
//        HashMap<String, Float> boost = new HashMap<>();
//        weight.put("name",8888f);
        //多域查询对象，可在多个域中进行查询
//        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(new String[]{"name", "address"}, analyzer,boost);
        //设置搜索关键字,返回一个查询对象
        query = queryParser.parse("address:上海 AND id:2");

    }
    @After//此处抽取每种查询之后要执行的代码
    public void testSearchAfter() throws Exception{
        //创建Directory目录对象，指定索引库的位置
        FSDirectory directory = FSDirectory.open(Paths.get("D:\\test\\testLucene"));
        //创建输入流对象
        IndexReader reader = DirectoryReader.open(directory);
        //创建搜索对象
        IndexSearcher searcher = new IndexSearcher(reader);
        //搜索，拿到结果集，此处指定了返回10条结果集
        TopDocs topDocs = searcher.search(query, 10);
        //获取到查询到的结果集对总数
        long totalHits = topDocs.totalHits;
        System.out.println("查询到的记录数：" + totalHits);
        //获取结果集
        ScoreDoc[] docs = topDocs.scoreDocs;
        //遍历结果集
        if (docs != null && docs.length > 0){
            for (ScoreDoc doc : docs) {
                //获取查询到的文档的唯一标识，文档ID，这个ID是Lucene在创建文档对象是自动分配的
                int docId = doc.doc;
                //通过文档ID读取文档
                Document document = searcher.doc(docId);
                System.out.println("********************************************************");
                //通过域名从文档中获取值
                System.out.println("=====id=====" + document.get("id"));
                System.out.println("=====name=====" + document.get("name"));
                System.out.println("=====age=====" + document.get("age"));
                System.out.println("=====gender=====" + document.get("gender"));
                System.out.println("=====address=====" + document.get("address"));
            }
        }
    }

}
