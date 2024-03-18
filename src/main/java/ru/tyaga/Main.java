package ru.tyaga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

            String url = "https://www.krylatskoye.ru/content/ratings/2021/09/0928.html";
            try {
                // Получаем HTML-документ с помощью Jsoup
                Document doc = Jsoup.connect(url).get();

                // Извлекаем таблицу по ее классу
                Element table = doc.select("table.table.table-bordered").first();

                // Проверяем, что таблица была найдена
                if (table != null) {
                    // Извлекаем строки таблицы
                    Elements rows = table.select("tr");
                    for (Element row : rows) {
                        // Извлекаем ячейки из строки
                        Elements cells = row.select("td");
                        for (Element cell : cells) {
                            // Выводим содержимое ячейки
                            System.out.print(cell.text() + "\t");
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