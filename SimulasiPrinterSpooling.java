// Nama: Arya Nabil Hariri dan Rafif Pasha Ibrahim
// NIM: 09021282429078 dan 09021282429046
package sistemoperasi;
import java.util.Scanner;

public class SimulasiPrinterSpooling {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] antrian = new String[100]; // berarti 100 adalah kapasitas maksimal dari antrian
        int awal = 0;  // awal adalah posisi awal antrian 
        int akhir = 0;  // sedangkan akhir adalah posisi akhir antrian 
        boolean jalan = false;

        System.out.println("=== Simulasi Spooling Printer===");
        System.out.println("Berikut ini adalah beberapa perintah yang bisa kamu gunakan dalam simulasi spooling ini:");
        System.out.println("add <nama>, start, pause, status, reset, quit");

        while (true) {
            System.out.print("> ");
            String cmd = input.nextLine().trim();

            if (cmd.startsWith("add ")) {
                String dok = cmd.substring(4);
                if (akhir < antrian.length) {
                    antrian[akhir++] = dok;
                    System.out.println("Dokumen "+ dok+" berhasil ditambahkan ke dalam antrian!" );
                } else {
                    System.out.println("Antrian penuh! Tidak bisa nambahin dokumen baru.");
                }
            }
            else if (cmd.equals("start")) {
                jalan = true;
                System.out.println("Memulai proses mencetak...");
                while (jalan && awal < akhir) {
                    String dok = antrian[awal++];
                    System.out.println("Mencetak: " + dok);
                    try { Thread.sleep(2000); } catch (Exception e) {}
                    System.out.println("Selesai mencetak: " + dok);
                }
                if (awal == akhir) {
                    System.out.println("Printer dalam keadaan nganggur (tidak ada antrian)");
                    awal = 0;
                    akhir = 0; // otomatis ke-reset kalo udah selesai
                }
            }
            else if (cmd.equals("pause")) {
                jalan = false;
                System.out.println("Proses mencetak dijeda.");
            }
            else if (cmd.equals("status")) {
                if (awal == akhir) {
                    System.out.println("Antrian kosong.");
                } else {
                    System.out.print("Dokumen dalam antrian: ");
                    for (int i = awal; i < akhir; i++) {
                        System.out.print(antrian[i]);
                        if (i < akhir - 1) System.out.print(", ");
                    }
                    System.out.println();
                }
            }
            else if (cmd.equals("reset")) {
                awal = 0;
                akhir = 0;
                System.out.println("Semua antrian telah dihapus.");
            }
            else if (cmd.equals("quit")) {
                System.out.println("Keluar dari program.");
                break;
            }
            else {
                System.out.println("Perintah yang kamu gunain tidak dikenal. Coba gunain perintah-perintah ini: add, start, pause, status, reset, atau quit.");
            }
        }

        input.close();
    }
}
