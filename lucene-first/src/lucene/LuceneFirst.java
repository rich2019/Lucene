package lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.File;

public class LuceneFirst {


    @Test
    public void createIndex() throws Exception {
        //1.创建一个director对象,指定索引库保存的位置
        Directory directory = FSDirectory.open(new File("C:\\java\\idea\\idea2019\\workspace\\GitHub\\Lucene\\lucene-first\\index").toPath());
        //2.基于director对象创建一个indexwriter对象
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig());
        //3.读取磁盘上的文件,为每个文件创建一个文档对象
        File dir = new File("C:\\java\\idea\\idea2019\\workspace\\GitHub\\Lucene\\lucene-first\\searchsource");
        File[] files = dir.listFiles();
        for (File f : files) {
            //取文件名
            String fileName = f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件的大小
            long fileSize = FileUtils.sizeOf(f);

            //创建filed
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);

            //4.创建文档对象
            Document document = new Document();
            //向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);

            //5.把文档写入索引库
            indexWriter.addDocument(document);
        }
        //6.关闭indexwriter对象
        indexWriter.close();

    }
}
