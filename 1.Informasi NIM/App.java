import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nim = input.nextLine().trim();

        // Validasi 1: NIM harus 8 karakter
        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            input.close();
            return;
        }

        String prodi = nim.substring(0, 3);
        String akt = nim.substring(3, 5);
        String urutan = nim.substring(5);

        String namaProdi = "";

        switch (prodi) {
            case "11S":
                namaProdi = "Sarjana Informatika";
                break;
            case "12S":
                namaProdi = "Sarjana Sistem Informasi";
                break;
            case "13S":
                namaProdi = "Sarjana Teknik Elektro";
                break;
            case "21S":
                namaProdi = "Sarjana Manajemen Rekayasa";
                break;
            case "22S":
                namaProdi = "Sarjana Teknik Metalurgi";
                break;
            case "31S":
                namaProdi = "Sarjana Teknik Bioproses";
                break;
            case "32S":
                namaProdi = "Sarjana Bioteknologi";
                break;
            case "114":
                namaProdi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
                break;
            case "113":
                namaProdi = "Diploma 3 Teknologi Informasi";
                break;
            case "133":
                namaProdi = "Diploma 3 Teknologi Komputer";
                break;
            default:
                // Validasi 2: Jika prodi tidak terdaftar
                System.out.println("Kode tidak tersedia");
                input.close();
                return;
        }

        // Output jika semua validasi terpenuhi
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + namaProdi);
        System.out.println(">> Angkatan: 20" + akt);
        System.out.println(">> Urutan: " + Integer.parseInt(urutan));

        input.close();
    }
}