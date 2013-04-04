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
public class Pembeli {
    private String no_beli,nama,alamat,tlp;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_beli() {
        return no_beli;
    }

    public void setNo_beli(String no_beli) {
        this.no_beli = no_beli;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
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
                    String SQLStatemen = "select * from pembeli where no_beli='"+no_beli+"'"; 
                    java.sql.Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    
                    rset.next(); 
                    if (rset.getRow()>0){
                        sta.close();
                        rset.close(); 
                        Object[] arrOpsi = {"Ya","Tidak"}; 
                        int pilih=JOptionPane.showOptionDialog(null,"No Pembeli sudah ada\nApakah data diupdate?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,arrOpsi,arrOpsi[0]); 
                        if (pilih==0){ 
                            simpan = true; 
                            SQLStatemen = "update pembeli set nama='"+nama+"', alamat='"+alamat+"', tlp='"+tlp+ "' where no_beli='"+no_beli+"'"; 
                            sta = cn.createStatement();
                            jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                        }
                    } else {
                        sta.close();
                        rset.close(); 

                        simpan = true;
                        SQLStatemen = "insert into pembeli(no_beli, nama, alamat, tlp) values ('"+ no_beli +"','"+ nama +"','"+ alamat +"','"+ tlp +"')"; 
                        sta = cn.createStatement(); 
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
                    
                    if (simpan) {
                        if (jumlahSimpan > 0){
                            JOptionPane.showMessageDialog(null,"Data Pembeli sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            adaKesalahan = true; 
                            JOptionPane.showMessageDialog(null,"Gagal menyimpan data pembeli","Kesalahan",JOptionPane.ERROR_MESSAGE); 
                        } 
                    }
                } catch (Exception ex){
                    adaKesalahan = true; 
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return !adaKesalahan;
    }
    
    public boolean baca(String no_beli){
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
                    String SQLStatemen = "select * from pembeli where no_beli='"+no_beli+"'";
                    Statement sta = (Statement) cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0){
                        this.no_beli = rset.getString("no_beli");
                        this.nama = rset.getString("nama");
                        this.alamat = rset.getString("alamat");
                        this.tlp = rset.getString("tlp");
                        sta.close();
                        rset.close();
                    } else {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"No Beli\""+no_beli+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel tbmahasiswa\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
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
                    SQLStatemen = "select no_beli,nama from pembeli";
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
                                    Object[]{rset.getString("no_beli"), rset.getString("nama")};
                            i++;
                        } while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return daftarPembeli;
    }
    public boolean hapus(String no_beli){
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
                    String SQLStatemen = "delete from pembeli where no_beli='"+no_beli+"'";
                    Statement sta = (Statement) cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0){
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data pembeli dengan No Beli "+no_beli+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data Pembeli dengan No Beli "+no_beli+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                        adaKesalahan = true;
                    }
                } catch (Exception ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return !adaKesalahan;
    }
    
}
