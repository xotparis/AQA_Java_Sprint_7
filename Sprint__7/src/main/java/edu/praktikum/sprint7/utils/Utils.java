package edu.praktikum.sprint7.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static final Random RANDOM = new Random();

    public static String randomString(int length) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);

        for(int i = 0; i < length; ++i) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (float)(rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(randomLimitedInt));
        }

        return buffer.toString();
    }

    public static String randomFirstNames() {
        String[] firstNames = {
                "Александр",
                "Дмитрий",
                "Михаил",
                "Иван",
                "Андрей",
                "Сергей",
                "Юрий",
                "Николай",
                "Владимир",
                "Алексей",
                "Артём",
                "Максим",
                "Игорь",
                "Виктор",
                "Олег",
                "Павел",
                "Константин",
                "Станислав",
                "Григорий",
                "Денис"
        };

        // Генерируем случайный индекс массива
        int randomIndex = RANDOM.nextInt(firstNames.length);

        // Возвращаем случайную фамилию
        return firstNames[randomIndex];
    }

    public static String randomSurname() {
        String[] surname = {
                "Иванов",
                "Смирнов",
                "Кузнецов",
                "Попов",
                "Соколов",
                "Лебедев",
                "Козлов",
                "Новиков",
                "Морозов",
                "Петров",
                "Волков",
                "Соловьёв",
                "Васильев",
                "Зайцев",
                "Павлов",
                "Семёнов",
                "Голубев",
                "Виноградов",
                "Богданов",
                "Воробьёв"
        };

        // Генерируем случайный индекс массива
        int randomIndex = RANDOM.nextInt(surname.length);

        // Возвращаем случайную фамилию
        return surname[randomIndex];

    }

    public static String phoneNumber() {

        // Генерируем случайное 10-значное число
        long randomNumber = 9;
        for (int i = 0; i < 9; i++) {
            randomNumber = randomNumber * 10 + RANDOM.nextInt(10);
        }
        return String.valueOf("+7" + randomNumber);
    }

    public static String getRandomDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Текущая дата
        LocalDate now = LocalDate.now();

        // Интервал в днях
        int daysRange = 365;

        // Генерируем случайное количество дней от текущей даты
        long randomDays = ThreadLocalRandom.current().nextLong(-daysRange, daysRange + 1);
        LocalDate randomDate = now.plusDays(randomDays);

        // Форматируем дату в строку
        return randomDate.format(formatter);
    }

    public static void date(String[] args) {
        // Пример использования
        String randomDate = getRandomDate();
        System.out.println("Random Date: " + randomDate);
    }

    public static int getRandomNumber() {
        return RANDOM.nextInt(20) + 1;
    }

}