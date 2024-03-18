package ru.tyaga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String url = "https://www.krylatskoye.ru/content/ratings/2021/09/0928.html";
        try {
            // Получаем HTML-документ с помощью Jsoup
            Document doc = Jsoup.connect(url).get();

            // Извлекаем таблицу по ее классу
            Elements table = doc.getElementsByClass("table_free");

            // Проверяем, что таблица была найдена
            if (table != null && !table.isEmpty()) {
                // Извлекаем строки таблицы
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    // Извлекаем ячейки из строки
                    Elements cells = row.select("td");

                    // Проверяем, что у нас есть как минимум две ячейки (столбца) в текущей строке
                    if (cells.size() >= 2) {
                        // Выводим содержимое второго столбца (индекс 1)
                        System.out.print(cells.get(1).text() + "\t");
                    }

                    System.out.println(); // Переход на новую строку после каждой строки таблицы
                }
            } else {
                System.out.println("Таблица не найдена");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
