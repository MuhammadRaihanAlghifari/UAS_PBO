import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/uas_pbo";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static Statement stmt2;
    static ResultSet rs;

    public static void main(String[] args) throws Exception {
        Kasir kasir = new Kasir();
        kasir.Login(); 

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

    
            kasir.tampilkanKeterangan();
            
            while (!conn.isClosed()) {
               showMenu();
            }

            stmt.close();
            stmt2.close();
            conn.close();

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Kasir : " + kasir.getUsernameBenar());
        }
        
    } 
    
    static Scanner scanner = new Scanner(System.in); 
    static Boolean tes;
        
    static LinkedHashMap<String, String> beli = new LinkedHashMap<String, String>();
    
    static void showMenu() {
        System.out.println("\n========= CRUD =========");
        System.out.println("1. Input Barang");
        System.out.println("2. Show Barang");
        System.out.println("3. Edit Barang");
        System.out.println("4. Hapus Barang");
        System.out.println("5. Beli Barang");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            Integer pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    inputData();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
                case 5:
                    beliBarang();
                    break;
                default:
                    System.out.println("------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM partner";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("\n\n");
            System.out.println("      DATA BARANG ASTORE      ");
            System.out.println("==================================");

            while (rs.next()) {
                String ID_Barang = rs.getString("ID_Barang");
                String Nama_Barang = rs.getString("Nama_Barang");
                String Nama_Game = rs.getString("Nama_Game");
                String ID_Perusahaan = rs.getString("ID_Perusahaan");
                String Nama_Perusahaan = rs.getString("Nama_Perusahaan");
                String Harga = rs.getString("Harga");
                
                System.out.println(String.format(" ID Barang : %s\n Nama Barang : %s\n Nama Game : %s\n ID_Perusahaan : %s\n Nama_Perusahaan : %s\n Harga Barang : %s\n ---------------------- ", ID_Barang, Nama_Barang, Nama_Game, ID_Perusahaan, Nama_Perusahaan, Harga));
            }

            if(beli.isEmpty()){
            System.out.println("Barang Kosong");
            }
            else {
            System.out.println("Sekian");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void inputData(){
        try{
            System.out.print("Masukkan ID Barang: ");
            String ID_Barang = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            String Nama_Barang = scanner.nextLine();

            System.out.print("Masukkan Harga: ");
            Integer Harga = Integer.parseInt(scanner.nextLine());

            System.out.print("Masukkan ID Perusahaan: ");
            String ID_Perusahaan = scanner.nextLine();

            System.out.print("Masukkan Nama Perusahaan: ");
            String Nama_Perusahaan = scanner.nextLine();

            System.out.print("Masukkan Nama Game: ");
            String Nama_Game = scanner.nextLine();

            System.out.println("Masukkan key(untuk treemap) :");
            beli.put(ID_Barang, Nama_Barang);

            String sql = "INSERT INTO partner (ID_Perusahaan, Nama_Perusahaan, Nama_Game, ID_Barang, Nama_Barang, Harga) VALUE ('%s', '%s', '%s', '%s', '%s' ,'%s')";
            sql = String.format(sql, ID_Perusahaan, Nama_Perusahaan, Nama_Game, ID_Barang, Nama_Barang, Harga);
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateData() {
        if(beli.isEmpty()){
            System.out.println("Tidak ada barang yang bisa diupdate");
           }
           else {
            try {
            System.out.print("ID_Barang yang mau diedit : ");
            String ID_Barang = scanner.nextLine();

            tes = beli.containsKey(ID_Barang);
            if(tes){

            System.out.print("Nama Barang (Baru): ");
            String Nama_Barang = scanner.nextLine().trim();
            System.out.print("Harga Barang (Baru): ");
            Integer Harga = Integer.parseInt(scanner.nextLine().trim());
            beli.replace(ID_Barang, Nama_Barang);


            String sql = "UPDATE partner SET Nama_Barang='%s', Harga='%s' WHERE ID_Barang='%s'";
            sql = String.format(sql, Nama_Barang, Harga, ID_Barang);
            stmt.execute(sql);
            }
            else{
                System.out.println("ID Barang tidak ditemukan!");
            }

            
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }

    static void deleteData() {
        if(beli.isEmpty()){
            System.out.println("Tidak ada barang yang bisa dihapus");
           }
           else {
            try {
            
            // ambil input dari user
            System.out.print("Masukkan ID Barang yang mau dihapus : ");
            String ID_Barang = (scanner.nextLine());

            tes = beli.containsKey(ID_Barang);
            if(tes){
            beli.remove(ID_Barang);
            }
            else{
                System.out.println("ID Barang tidak ditemukan!");
            }
            
            // buat query hapus
            String sql = String.format("DELETE FROM partner WHERE ID_Barang='%s'", ID_Barang);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah dihapus");
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }
    static void beliBarang() {
        if(beli.isEmpty()){
            System.out.println("Tidak ada barang yang bisa dibeli");
           }
           else {
            System.out.println("List Barang:");
            for (Map.Entry nama : beli.entrySet()) {
                System.out.println("ID : " + nama.getKey() + " -- Nama Barang : " + nama.getValue());
                }


            try {
            System.out.print("Masukkan ID Barang yang mau dibeli : ");
            String ID_Barang = (scanner.nextLine());

            tes = beli.containsKey(ID_Barang);
            if(tes){
            String sql1 = String.format("SELECT * FROM partner WHERE ID_Barang='%s'", ID_Barang);

            rs = stmt.executeQuery(sql1);
                while (rs.next()) {

                    System.out.print("Masukkan jumlah yang ingin dibeli: ");
                    Integer Jumlah = Integer.parseInt(scanner.nextLine());
                    if (Jumlah > 100) {
                    throw new IllegalArgumentException("Jumlah beli tidak boleh lebih dari 100");
                    }

                    String Nama_Barang = rs.getString("Nama_Barang");
                    String Nama_Game = rs.getString("Nama_Game");
                    Integer Harga = rs.getInt("Harga");

                    faktur faktur;
                    faktur = new faktur(ID_Barang, Nama_Barang, Nama_Game, Harga, Jumlah);
                    faktur.tampilkanDetailFaktur();
                    
                    String sql = "INSERT INTO data_pembelian (ID_Barang, Nama_Barang, Nama_Game, Jumlah, Harga, Total) VALUE ('%s', '%s', '%s', '%s', '%s' ,'%s')";
                    sql = String.format(sql, ID_Barang, Nama_Barang, Nama_Game, Jumlah, Harga, faktur.hitungTotalBayar(Harga, Jumlah));
                    stmt2.execute(sql);
                    
                }
            }
            else{
                System.out.println("ID Barang tidak ditemukan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
        }          
    }
}