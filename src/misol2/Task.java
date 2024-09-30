package misol2;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        AtomicInteger workDays = new AtomicInteger(); 
        AtomicInteger leapYears = new AtomicInteger(); 

        Thread workDaysThread = new Thread(() -> {
            LocalDate date = startDate;
            while (!date.isAfter(endDate)) {
                if (date.getDayOfWeek().getValue() >= 1 && date.getDayOfWeek().getValue() <= 5) {
                    workDays.incrementAndGet();
                }
                date = date.plusDays(1); 
            }
        });

        Thread leapYearsThread = new Thread(() -> {
            for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
                if (isLeapYear(year)) {
                    leapYears.incrementAndGet();
                }
            }
        });

        workDaysThread.start();
        leapYearsThread.start();

        workDaysThread.join();
        leapYearsThread.join();

        System.out.println("Ishlangan kunlar: " + workDays.get());
        System.out.println("Kabisa yillari soni: " + leapYears.get());
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
