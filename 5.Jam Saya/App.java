import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) {
            System.out.println("Jam tidak valid");
            return;
        }

        String initialTimeString = scanner.nextLine().trim();
        String[] parts = initialTimeString.split(":");

        if (parts.length != 2) {
            System.out.println("Jam tidak valid");
            return;
        }

        int startHour, startMinute;
        try {
            startHour = Integer.parseInt(parts[0]);
            startMinute = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return;
        }

        if (startHour < 0 || startHour > 23 || startMinute < 0 || startMinute > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        int currentMinutes = startHour * 60 + startMinute;
        int totalShiftMinutes = 0;
        int dayChanges = 0;
        List<String> errors = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            char sign = line.charAt(0);
            if (sign != '+' && sign != '-') {
                errors.add("Perintah tidak valid");
                continue;
            }

            int shiftValue;
            try {
                shiftValue = Integer.parseInt(line.substring(1));
            } catch (NumberFormatException e) {
                errors.add("Perintah tidak valid");
                continue;
            }

            int actualShift = (sign == '-') ? -shiftValue : shiftValue;
            totalShiftMinutes += actualShift;
            currentMinutes += actualShift;

            while (currentMinutes >= 1440) {
                currentMinutes -= 1440;
                dayChanges++;
            }
            while (currentMinutes < 0) {
                currentMinutes += 1440;
                dayChanges++;
            }
        }

        // Cetak semua eror dari perintah invalid terlebih dahulu
        for (String err : errors) {
            System.out.println(err);
        }

        int finalHour = currentMinutes / 60;
        int finalMinute = currentMinutes % 60;

        String formattedTotalMinutes;
        if (totalShiftMinutes > 0) {
            formattedTotalMinutes = "+" + totalShiftMinutes;
        } else {
            formattedTotalMinutes = String.valueOf(totalShiftMinutes);
        }

        System.out.printf("Jam Awal: %02d:%02d\n", startHour, startMinute);
        System.out.printf("Jam Akhir: %02d:%02d\n", finalHour, finalMinute);
        System.out.println("Total Menit: " + formattedTotalMinutes);
        System.out.println("Pergantian Hari: " + dayChanges);

        scanner.close();
    }
}