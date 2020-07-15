package com.zby.utils;

import com.zby.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Component
public class HtmlParseUtil {

    public List<Content> parseJD(String keywords)throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keywords+"&enc=utf-8";
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");

        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element e1 : elements) {
            String img = e1.getElementsByTag("img").eq(0).attr("src");
            String price = e1.getElementsByClass("p-price").eq(0).text();
            String title = e1.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            if (!content.getPrice().isEmpty())
            {
                goodsList.add(content);
            }
            else
                continue;


        }
        return goodsList;
    }
}
