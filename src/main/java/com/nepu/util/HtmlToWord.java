package com.nepu.util;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

/**
 * Created by Administrator on 2017/5/22.
 */
public class HtmlToWord {

    public void main(String[] args) throws Exception {
        HtmlToWord htmlToWord = new HtmlToWord();
        htmlToWord.htmlToWord();
    }



    public void htmlToWord() throws Exception {
        File file = new File("d:\\1.doc");
        if (!file.exists()){
            file.createNewFile();
        }
        InputStream bodyIs = new FileInputStream("d:\\temp.html");
        //InputStream cssIs = new FileInputStream("f:\\1.css");
        String body = this.getContent(bodyIs);
//        String css = this.getContent(cssIs);" + css + "
        //拼一个标准的HTML格式文档
        String content = "<html><head><style></style></head><body>" + body + "</body></html>";
        InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
        OutputStream os = new FileOutputStream("d:\\1.doc");
        this.inputStreamToWord(is, os);
    }

    /**
     * 把is写入到对应的word输出流os中
     * 不考虑异常的捕获，直接抛出
     * @param is
     * @param os
     * @throws IOException
     */
    private void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
    }

    /**
     * 把输入流里面的内容以UTF-8编码当文本取出。
     * 不考虑异常，直接抛出
     * @param ises
     * @return
     * @throws IOException
     */
    private String getContent(InputStream... ises) throws IOException {
        if (ises != null) {
            StringBuilder result = new StringBuilder();
            BufferedReader br;
            String line;
            for (InputStream is : ises) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line=br.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        }
        return null;
    }

}
