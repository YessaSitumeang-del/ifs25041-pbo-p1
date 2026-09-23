import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int ukuranMatriks = input.nextInt();
        int[][] matriks = new int[ukuranMatriks][ukuranMatriks];
        for (int i = 0; i < ukuranMatriks; i++) {
            for (int j = 0; j < ukuranMatriks; j++) {
                matriks[i][j] = input.nextInt();
            }
        }

        // Kasus khusus ukuranMatriks = 1
        if (ukuranMatriks == 1) {
            int nilaiTengah = matriks[0][0];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
            input.close();
            return;
        }

        // Kasus khusus ukuranMatriks = 2
        if (ukuranMatriks == 2) {
            int total = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    total += matriks[i][j];
                }
            }
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + total);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + total);
            input.close();
            return;
        }

        // N >= 3
        // Hitung Nilai L: kolom pertama + baris terakhir (tanpa pojok kanan bawah)
        int jumlahL = 0;
        for (int i = 0; i < ukuranMatriks; i++) jumlahL += matriks[i][0];
        for (int j = 1; j <= ukuranMatriks - 2; j++) jumlahL += matriks[ukuranMatriks - 1][j];

        // Hitung Nilai Kebalikan L: kolom terakhir + baris pertama (tanpa pojok kiri atas)
        int jumlahKebalikanL = 0;
        for (int i = 0; i < ukuranMatriks; i++) jumlahKebalikanL += matriks[i][ukuranMatriks - 1];
        for (int j = 1; j <= ukuranMatriks - 2; j++) jumlahKebalikanL += matriks[0][j];

        // Hitung Nilai Tengah
        int nilaiTengah;
        if (ukuranMatriks % 2 == 1) {
            nilaiTengah = matriks[ukuranMatriks / 2][ukuranMatriks / 2];
        } else {
            int mid1 = ukuranMatriks / 2 - 1;
            int mid2 = ukuranMatriks / 2;
            nilaiTengah = matriks[mid1][mid1] + matriks[mid1][mid2] + matriks[mid2][mid1] + matriks[mid2][mid2];
        }

        int selisih = Math.abs(jumlahL - jumlahKebalikanL);
        int nilaiDominan = (selisih == 0) ? nilaiTengah : Math.max(jumlahL, jumlahKebalikanL);

        System.out.println("Nilai L: " + jumlahL);
        System.out.println("Nilai Kebalikan L: " + jumlahKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + selisih);
        System.out.println("Dominan: " + nilaiDominan);

        input.close();
    }
}