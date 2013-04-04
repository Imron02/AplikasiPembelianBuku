/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author aim
 */
public class Buku {
    private String kodeBuku, judulBuku, Penerbit;


    public String getPenerbit() {
        return Penerbit;
    }

    public void setPenerbit(String Penerbit) {
        this.Penerbit = Penerbit;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }
    
    public boolean simpan(){
        boolean adaKesalahan = false;
        java.sql.Connection cn = null; 
        
        try{ 
            Class.forName(Koneksi.driver); 
        } catch (Exception ex){ 
            adaKesalahan = true; 
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);  
        } 
        
        if (!adaKesalahan){ 
            try { 
                cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+""); 
            } catch (Exception ex) { 
                adaKesalahan = true;  
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
            } 
            
            if (!adaKesalahan){ 
                int jumlahSimpan=0; 
                boolean simpan = false; 
                
                try { 
                    String SQLStatemen = "select * from buku where kode_buku='"+kodeBuku+"'"; 
                    java.sql.Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    
                    rset.next(); 
                    if (rset.getRow()>0){
                        sta.close();
                        rset.close(); 
                        Object[] arrOpsi = {"Ya","Tidak"}; 
                        int pilih=JOptionPane.showOptionDialog(null,"Kode Buku sudah ada\nApakah data diupdate?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,arrOpsi,arrOpsi[0]); 
                        if (pilih==0){ 
                            simpan = true; 
                            SQLStatemen = "update buku set judul_buku='"+judulBuku+"', penerbit='"+Penerbit+"' where kode_buku='"+kodeBuku+"'"; 
                            sta = cn.createStatement();
                            jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                        }
                    } else {
                        sta.close();
                        rset.close(); 

                        simpan = true;
                        SQLStatemen = "insert into buku(kode_buku, judul_buku, penerbit) values ('"+ kodeBuku +"','"+ judulBuku +"','"+ Penerbit +"')"; 
                        sta = cn.createStatement(); 
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
                    
                    if (simpan) {
                        if (jumlahSimpan > 0){
                            JOptionPane.showMessageDialog(null,"Data buku sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            adaKesalahan = true; 
                            JOptionPane.showMessageDialog(null,"Gagal menyimpan data buku","Kesalahan",JOptionPane.ERROR_MESSAGE); 
                        } 
                    }
                } catch (Exception ex){
                    adaKesalahan = true; 
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel buku\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return !adaKesalahan;
    }
    
    public boolean baca(String kode_buku){
        boolean adaKesalahan = false;
        Connection cn = null;
        try{
            Class.forName(Koneksi.driver);
        } catch (Exception ex){
            adaKesalahan = true;
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
        if (!adaKesalahan){
            try {
                cn = (Connection) DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke"+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
            if (!adaKesalahan){
                try {
                    String SQLStatemen = "select * from buku where kode_buku='"+kode_buku+"'";
                    Statement sta = (Statement) cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0){
                        this.kodeBuku = rset.getString("kode_buku");
                        this.judulBuku = rset.getString("judul_buku");
                        this.Penerbit = rset.getString("penerbit");
                        sta.close();
                        rset.close();
                    } else {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Kode Buku\""+kode_buku+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel buku\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return !adaKesalahan;
    }
    public Object[][] bacaDaftar(){
        boolean adaKesalahan = false;
        Connection cn = null;
        Object[][] daftarPembeli = new Object[0][0];
        try{
            Class.forName(Koneksi.driver);
        } catch (Exception ex){
            adaKesalahan = true;
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
        if (!adaKesalahan){
            try {
                cn = (Connection) DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke"+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
            if (!adaKesalahan){
                String SQLStatemen;
                Statement sta;
                ResultSet rset;
                try {
                    SQLStatemen = "select kode_buku,judul_buku from buku";
                    sta = (Statement) cn.createStatement();
                    rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    rset.last();
                    daftarPembeli = new Object[rset.getRow()][2];
                    if (rset.getRow()>0){
                        rset.first();
                        int i=0;
                        do {
                            daftarPembeli[i] = new
                                    Object[]{rset.getString("kode_buku"), rset.getString("judul_buku")
                            };
                            i++;
                        } while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel buku\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return daftarPembeli;
    }
    public boolean hapus(String kode_buku){
        boolean adaKesalahan = false;
        Connection cn = null;
        try{
            Class.forName(Koneksi.driver);
        } catch (Exception ex){
            adaKesalahan = true;
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
        if (!adaKesalahan){
            try {
                cn = (Connection) DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke database"+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
            if (!adaKesalahan){
                int jumlahHapus;
                try {
                    String SQLStatemen = "delete from buku where kode_buku='"+kode_buku+"'";
                    Statement sta = (Statement) cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0){
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data buku dengan kode_buku "+kode_buku+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data buku dengan kode_buku "+kode_buku+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                        adaKesalahan = true;
                    }
                } catch (Exception ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel buku\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return !adaKesalahan;
    }
}
