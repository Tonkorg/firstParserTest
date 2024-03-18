package ru.tyaga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AvitoParser {
    public static void main(String[] args) {
        String url = "https://www.avito.ru/moskva/avtomobili/audi-ASgBAgICAUTgtg3elyg?cd=1&radius=0&searchRadius=0";
        try {
            // Получаем HTML-документ с помощью Jsoup
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                    .timeout(10000)
                    .get();


            // Извлекаем все ссылки на товары
            Elements links = doc.select("a.iva-item-sliderLink-uLz1v"); // Выбираем все элементы <a> с классом iva-item-sliderLink-uLz1v
            for (Element link : links) {
                String href = link.attr("href"); // Извлекаем значение атрибута href (ссылку)
                String title = link.attr("title"); // Извлекаем значение атрибута title (название товара)

                // Находим все элементы <span> внутри ссылки
                Elements spans = link.select("span");
                for (Element span : spans) {
                    // Проверяем, содержит ли текст символ валюты ₽
                    if (span.text().contains("₽")) {
                        String price = span.text(); // Извлекаем текст цены
                        System.out.println("Название: " + title);
                        System.out.println("Ссылка: " + href);
                        System.out.println("Цена: " + price);
                        System.out.println();
                        break; // Прерываем цикл, если найдено значение стоимости
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
