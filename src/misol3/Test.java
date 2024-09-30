package misol3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String folderPath = "C:/path/to/folder"; // Berilgan katalog yo'li
        String fileName = "sample.txt"; // Qidirilayotgan fayl nomi

        File folder = new File(folderPath);
        File file = findFileInFolder(folder, fileName); // Faylni qidirish

        if (file != null) {
            System.out.println("Fayl topildi: " + file.getAbsolutePath());
            try {
                readFileAndPrintMultiDigitNumbers(file); // Faylni o'qib, raqamlarni chiqarish
            } catch (FileNotFoundException e) {
                System.out.println("Faylni o'qishda xatolik yuz berdi: " + e.getMessage());
            }
        } else {
            System.out.println("Fayl topilmadi!");
        }
    }

    // Faylni berilgan katalog ichidan qidiradigan metod
    public static File findFileInFolder(File folder, String fileName) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                File found = findFileInFolder(file, fileName); // Rekursiya orqali kataloglarni qidirish
                if (found != null) {
                    return found;
                }
            } else if (file.getName().equals(fileName)) {
                return file; // Fayl topilganda uni qaytarish
            }
        }
        return null; // Fayl topilmasa
    }

    // Faylni o'qib, ichidan bir tadan ko'p raqamlarni chiqaradigan metod
    public static void readFileAndPrintMultiDigitNumbers(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Pattern pattern = Pattern.compile("\\d{2,}"); // Ikki va undan ko'p raqamlar uchun regex

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                System.out.println("Topilgan raqam: " + matcher.group()); // Bir tadan ko'p raqamlarni chiqarish
            }
        }
        scanner.close();
    }
}

