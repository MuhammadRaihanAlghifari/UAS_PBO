
public class faktur implements hitungTotalBayar {
    private String Nama_Game; 
    private String ID_Barang; 
    private String Nama_Barang;
    private Integer Harga;
    private Integer Jumlah;


    public faktur(String ID_Barang, String Nama_Barang, String Nama_Game, Integer Harga, Integer Jumlah) {
        this.ID_Barang = ID_Barang;
        this.Nama_Barang = Nama_Barang;
        this.Nama_Game = Nama_Game;
        this.Harga = Harga;
        this.Jumlah = Jumlah;
    }



    public String getID_Barang() {
        return ID_Barang;
    }

    public String getNama_Barang() {
        return Nama_Barang;
    }

    public String getNama_Game() {
        return Nama_Game;
    }

    public Integer getHarga() {
        return Harga;
    }

    public void tampilkanDetailFaktur() {
        System.out.println("\n=======================");
        System.out.println("PEMBELIAN BERHASIL");
        System.out.println("Nomor ID BARANG: " + ID_Barang.toUpperCase());
        System.out.println("=======================");
        System.out.println("\n  DATA PEMBELIAN");
        System.out.println("Nama Barang : " + Nama_Barang );
        System.out.println("Nama Game : " + Nama_Game);
        System.out.println("Harga Barang: " + Harga);
        System.out.println("Jumlah Beli: " + Jumlah);
        System.out.println("Total Bayar: " + hitungTotalBayar(Harga, Jumlah));
        System.out.println("------------------------");
    }

    @Override
    public Integer hitungTotalBayar(Integer harga, Integer jumlah) {
        return harga * jumlah;
    }
}
