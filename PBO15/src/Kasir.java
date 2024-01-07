import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Kasir {

        String username = "Agif";
        String password = "UpinIrfan";
        String cap  = "mralghfr_A";
        Boolean loginBenar = false;
        Boolean inputcapBenar = false;
        String usernameBenar, passwordBenar,capBenar;

        public String getUsernameBenar() {
            return usernameBenar;
        }

    Date date = new Date();
    SimpleDateFormat tanggal = new SimpleDateFormat("E dd/MM/yyyy");
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm:ss zzzz");


    public void tampilkanKeterangan() {
        System.out.println("");
        System.out.println("A STORE");
        System.out.println("Tanggal : " + tanggal.format(date));
        System.out.println("Jam : " + jam.format(date));
    }

    public void Login() {
        System.out.println("SELAMAT DATANG DI ASTORE");
        System.out.println("===========================");
        System.out.println("Log In");
 
        Scanner scanner = new Scanner(System.in); {

            while (!loginBenar) {
                System.out.print("username  : ");
                usernameBenar = scanner.nextLine();
                System.out.print("password  : ");
                passwordBenar = scanner.nextLine();

                if (usernameBenar.equals(username) && passwordBenar.equals(password)) {
                    break;
                } else {
                    System.out.println("username atau password Salah!");
                }
            }    

            while (!inputcapBenar){
                System.out.println("Kode cap : " + cap);
                System.out.print("Entry cap : ");
                capBenar = scanner.nextLine();

                if (capBenar.equals(cap)) {
                    System.out.println("\nLogin berhasil!\n");
                    break;
                } else {
                    System.out.println("cap Salah!");
                }
            }  
        }  
        System.out.println("---------------------------");  
    }  
}  