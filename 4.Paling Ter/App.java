import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Struktur data yang dipakai
        HashMap<Integer, Integer> frekuensiNilai = new HashMap<>();
        ArrayList<Integer> daftarInputNilai = new ArrayList<>();

        // Input data
        while (true) {
            String data = input.nextLine();
            if (data.equals("---")) break;

            int nilai = Integer.parseInt(data);
            int jumlahMuncul = frekuensiNilai.getOrDefault(nilai, 0) + 1;
            frekuensiNilai.put(nilai, jumlahMuncul);
            daftarInputNilai.add(nilai);
        }

        // Konversi ke array
        int[] arrayNilai = daftarInputNilai.stream().mapToInt(Integer::intValue).toArray();

        // Cari nilai tertinggi & terendah
        int maxNilai = arrayNilai[0];
        int minNilai = arrayNilai[0];
        for (int nilai : arrayNilai) {
            if (nilai > maxNilai) maxNilai = nilai;
            if (nilai < minNilai) minNilai = nilai;
        }

        // Cari nilai paling sering muncul (jika seri, pilih nilai TERBESAR)
        int frekuensiMaksimum = 0;
        int nilaiPalingSering = 0;
        for (var entry : frekuensiNilai.entrySet()) {
            int nilai = entry.getKey();
            int frek = entry.getValue();
            if (frek > frekuensiMaksimum ||
               (frek == frekuensiMaksimum && nilai > nilaiPalingSering)) {
                frekuensiMaksimum = frek;
                nilaiPalingSering = nilai;
            }
        }

        // Cari nilai paling jarang muncul (jika seri, pilih nilai TERKECIL)
        int frekuensiMinimum = Integer.MAX_VALUE;
        int nilaiPalingJarang = Integer.MAX_VALUE;
        for (var entry : frekuensiNilai.entrySet()) {
            int nilai = entry.getKey();
            int frek = entry.getValue();
            if (frek < frekuensiMinimum ||
               (frek == frekuensiMinimum && nilai < nilaiPalingJarang)) {
                frekuensiMinimum = frek;
                nilaiPalingJarang = nilai;
            }
        }

        // Cari (nilai * frekuensi) tertinggi (jika seri, pilih nilai TERBESAR)
        int totalTertinggi = Integer.MIN_VALUE;
        int nilaiDenganTotalTertinggi = 0;
        int frekuensiNilaiDenganTotalTertinggi = 0;
        for (var entry : frekuensiNilai.entrySet()) {
            int nilai = entry.getKey();
            int frek = entry.getValue();
            int total = nilai * frek;
            if (total > totalTertinggi ||
               (total == totalTertinggi && nilai > nilaiDenganTotalTertinggi)) {
                totalTertinggi = total;
                nilaiDenganTotalTertinggi = nilai;
                frekuensiNilaiDenganTotalTertinggi = frek;
            }
        }

        // Cari (nilai * frekuensi) terendah (jika seri, pilih nilai TERKECIL)
        int totalTerendah = Integer.MAX_VALUE;
        int nilaiDenganTotalTerendah = 0;
        int frekuensiNilaiDenganTotalTerendah = 0;
        for (var entry : frekuensiNilai.entrySet()) {
            int nilai = entry.getKey();
            int frek = entry.getValue();
            int total = nilai * frek;
            if (total < totalTerendah ||
               (total == totalTerendah && nilai < nilaiDenganTotalTerendah)) {
                totalTerendah = total;
                nilaiDenganTotalTerendah = nilai;
                frekuensiNilaiDenganTotalTerendah = frek;
            }
        }

        // Output hasil
        System.out.println("Tertinggi: " + maxNilai);
        System.out.println("Terendah: " + minNilai);
        System.out.println("Terbanyak: " + nilaiPalingSering + " (" + frekuensiMaksimum + "x)");
        System.out.println("Tersedikit: " + nilaiPalingJarang + " (" + frekuensiMinimum + "x)");
        System.out.println("Jumlah Tertinggi: " + nilaiDenganTotalTertinggi + " * "
                           + frekuensiNilaiDenganTotalTertinggi + " = " + totalTertinggi);
        System.out.println("Jumlah Terendah: " + nilaiDenganTotalTerendah + " * "
                           + frekuensiNilaiDenganTotalTerendah + " = " + totalTerendah);

        input.close();
    }
}