import java.util.*;

public class App {
    public static void main(String[] args) {
        // Paksa locale US agar desimal menggunakan titik (.)
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        // Membaca 6 bobot satu per satu ke bawah
        double bobotPA     = Double.parseDouble(scanner.nextLine().trim());
        double bobotTugas  = Double.parseDouble(scanner.nextLine().trim());
        double bobotKuis   = Double.parseDouble(scanner.nextLine().trim());
        double bobotProyek = Double.parseDouble(scanner.nextLine().trim());
        double bobotUTS    = Double.parseDouble(scanner.nextLine().trim());
        double bobotUAS    = Double.parseDouble(scanner.nextLine().trim());

        // Validasi total bobot harus 100
        double totalBobot = bobotPA + bobotTugas + bobotKuis + bobotProyek + bobotUTS + bobotUAS;
        if (Math.abs(totalBobot - 100.0) > 0.0001) {
            System.out.println("Total bobot harus 100");
            scanner.close();
            return;
        }

        // Akumulator skor dan maksimal per komponen
        int skorPA = 0, maxPA = 0;
        int skorTugas = 0, maxTugas = 0;
        int skorKuis = 0, maxKuis = 0;
        int skorProyek = 0, maxProyek = 0;
        int skorUTS = 0, maxUTS = 0;
        int skorUAS = 0, maxUAS = 0;

        // Baca item sampai "---"
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---") || line.isEmpty()) break;

            String[] parts = line.split("\\|");
            
            // Validasi 1: Format harus tepat 3 elemen
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            int nilaiMax = 0;
            int nilaiSkor = 0;

            // Validasi 2: Nilai max dan skor harus angka integer valid
            try {
                nilaiMax = Integer.parseInt(parts[1].trim());
                nilaiSkor = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            // Validasi 3: Simbol harus dikenali
            switch (simbol) {
                case "PA":  maxPA += nilaiMax;  skorPA += nilaiSkor;  break;
                case "T":   maxTugas += nilaiMax; skorTugas += nilaiSkor; break;
                case "K":   maxKuis += nilaiMax;  skorKuis += nilaiSkor;  break;
                case "P":   maxProyek += nilaiMax; skorProyek += nilaiSkor; break;
                case "UTS": maxUTS += nilaiMax; skorUTS += nilaiSkor; break;
                case "UAS": maxUAS += nilaiMax; skorUAS += nilaiSkor; break;
                default:
                    System.out.println("Simbol tidak dikenal");
                    break;
            }
        }

        // Hitung persentase (dibulatkan ke bawah)
        int persenPA     = maxPA > 0 ? (int) Math.floor(skorPA * 100.0 / maxPA) : 0;
        int persenTugas  = maxTugas > 0 ? (int) Math.floor(skorTugas * 100.0 / maxTugas) : 0;
        int persenKuis   = maxKuis > 0 ? (int) Math.floor(skorKuis * 100.0 / maxKuis) : 0;
        int persenProyek = maxProyek > 0 ? (int) Math.floor(skorProyek * 100.0 / maxProyek) : 0;
        int persenUTS    = maxUTS > 0 ? (int) Math.floor(skorUTS * 100.0 / maxUTS) : 0;
        int persenUAS    = maxUAS > 0 ? (int) Math.floor(skorUAS * 100.0 / maxUAS) : 0;

        // Batasi persen minimal 0 dan maksimal 100
        if (persenPA < 0) persenPA = 0; else if (persenPA > 100) persenPA = 100;
        if (persenTugas < 0) persenTugas = 0; else if (persenTugas > 100) persenTugas = 100;
        if (persenKuis < 0) persenKuis = 0; else if (persenKuis > 100) persenKuis = 100;
        if (persenProyek < 0) persenProyek = 0; else if (persenProyek > 100) persenProyek = 100;
        if (persenUTS < 0) persenUTS = 0; else if (persenUTS > 100) persenUTS = 100;
        if (persenUAS < 0) persenUAS = 0; else if (persenUAS > 100) persenUAS = 100;

        double kontribusiPA     = (persenPA / 100.0) * bobotPA;
        double kontribusiTugas  = (persenTugas / 100.0) * bobotTugas;
        double kontribusiKuis   = (persenKuis / 100.0) * bobotKuis;
        double kontribusiProyek = (persenProyek / 100.0) * bobotProyek;
        double kontribusiUTS    = (persenUTS / 100.0) * bobotUTS;
        double kontribusiUAS    = (persenUAS / 100.0) * bobotUAS;

        double nilaiAkhir = kontribusiPA + kontribusiTugas + kontribusiKuis +
                            kontribusiProyek + kontribusiUTS + kontribusiUAS;

        // Konversi grade
        String grade;
        if (nilaiAkhir >= 79.5) grade = "A";
        else if (nilaiAkhir >= 72.0) grade = "AB";
        else if (nilaiAkhir >= 64.5) grade = "B";
        else if (nilaiAkhir >= 56.5) grade = "BC";
        else if (nilaiAkhir >= 48.5) grade = "C";
        else if (nilaiAkhir >= 33.5) grade = "D";
        else grade = "E";

        // Output (Menggunakan titik dan kata 'Silahkan')
        System.out.println("Perolehan Nilai:");
        System.out.printf(">> Partisipatif: %d/100 (%.2f/%.0f)%n", persenPA, kontribusiPA, bobotPA);
        System.out.printf(">> Tugas: %d/100 (%.2f/%.0f)%n", persenTugas, kontribusiTugas, bobotTugas);
        System.out.printf(">> Kuis: %d/100 (%.2f/%.0f)%n", persenKuis, kontribusiKuis, bobotKuis);
        System.out.printf(">> Proyek: %d/100 (%.2f/%.0f)%n", persenProyek, kontribusiProyek, bobotProyek);
        System.out.printf(">> UTS: %d/100 (%.2f/%.0f)%n", persenUTS, kontribusiUTS, bobotUTS);
        System.out.printf(">> UAS: %d/100 (%.2f/%.0f)%n", persenUAS, kontribusiUAS, bobotUAS);

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.printf(">> Grade: %s%n", grade);

        scanner.close();
    }
}