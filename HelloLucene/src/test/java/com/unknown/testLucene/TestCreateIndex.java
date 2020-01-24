package com.unknown.testLucene;

import com.unknown.bean.User;
import com.unknown.dao.UserDao;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestCreateIndex {

    public static void main(String[] args) throws Exception {
        //采集数据
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();
        //创建文档对象
        ArrayList<Document> documents = new ArrayList<>();
        users.forEach(user -> {
            Document document = new Document();
            //创建域对象并放入文档对象中
            /*
             * 是否分词：否（因为id分词没有任何意义）
             *是否索引：是（因为需要根据id进行查询）
             * 是否存储：是，因为id字段比较重要，随时可能用到，而且占用空间较小
             * */
            document.add(new StringField("id", String.valueOf(user.getId()), Field.Store.YES));
            /*
             * 是否分词：是（因为name字段分词有意义）
             *是否索引：是（因为需要根据name进行查询）
             * 是否存储：是，因为需要展示
             * */
            document.add(new TextField("name", user.getName(), Field.Store.YES));
            /*
            * 是否分词：是（因为lucene底层规定，如果需要按照范围查询就必需分词）
            *是否索引：是（因为需要根据年龄进行范围查询）
            * 是否存储：是，因为需要展示
            * 此处使用了IntPoint达成分词和索引的目的，使用了StoredField达成存储的目的
            * */
            document.add(new IntPoint("age", user.getAge()));
            document.add(new StoredField("age",user.getAge()));
            /*
             * 是否分词：否（因为此处的gender字段本身就一个字符）
             *是否索引：是（因为需要根据gender进行查询）
             * 是否存储：是，因为需要展示
             * */
            document.add(new StringField("gender", user.getGender(), Field.Store.YES));
            /*
             * 是否分词：是（因为address字段分词有意义）
             *是否索引：是（因为需要根据address进行查询）
             * 是否存储：是，因为需要展示
             * */
            document.add(new TextField("address", user.getAddress(), Field.Store.YES));
            //放入文档集合
            documents.add(document);
        });
        //创建分词器
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //创建Directory目录对象，指定索引库存放位置
        Directory directory = FSDirectory.open(Paths.get("D:\\test\\testLucene"));
        //创建IndexWriterConfig对象，在这个对象中指定切分词用的分词器
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //创建IndexWriter输出流对象，指定输出的位置和使用的config初始化对象
        IndexWriter indexWriter = new IndexWriter(directory, config);
        //写入文档到索引库
        for (Document document : documents) {
            indexWriter.addDocument(document);
        }
        //释放资源
        indexWriter.close();
    }

}
