/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author aim
 */
public class Kwitansi {
    private String no_beli;
    private Object[][] listKwitansi;
    
    public void setNoPembeli(String no_beli){
        this.no_beli = no_beli;
    }
    
    public void setListKwitansi(Object[][] listKwitansi){
        this.listKwitansi = listKwitansi;
    }
   
    public String getNoPembeli(){
        return this.no_beli;
    }
    
    public Object[][] getListKwitansi(){
        return this.listKwitansi;
    }
    
    public boolean simpan(){
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
                cn = DriverManager.getConnection(Koneksi.database+"?user="+ Koneksi.user+"&password="+Koneksi.password+""); 
            } catch (Exception ex) { 
                adaKesalahan = true;  
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
            } 
            
            if (!adaKesalahan){ 
                int jumlahSimpan = 0; 
                String SQLStatemen;
                Statement sta;
                
                try{
                    SQLStatemen = "delete from kwitansi where no_beli='"+no_beli+"'"; 
                    sta = cn.createStatement(); 
                    sta.executeUpdate(SQLStatemen);
                } catch (Exception ex){}
                
                for (int i=0; i < listKwitansi.length; i++){                  
                    try {
                        SQLStatemen = "insert into kwitansi values ('"+ no_beli +"','"+ listKwitansi[i][0] + "','" + listKwitansi[i][1] + "','" + listKwitansi[i][2] + "')"; 
                        sta = cn.createStatement(); 
                        jumlahSimpan += sta.executeUpdate(SQLStatemen);                      
                    } catch (SQLException ex){}
                }
                
                if (jumlahSimpan>0) {
                    adaKesalahan = false;
                }
            }
        } 		
        return !adaKesalahan;
    }
    
    public boolean baca(String no_beli){
	boolean adaKesalahan = false;	
	Connection cn = null; 
        
	this.no_beli = no_beli;
	listKwitansi = null;
	
	try{ 
            Class.forName(Koneksi.driver); 
	} catch (Exception ex){ 
            adaKesalahan = true; 
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);  
	} 
	
	if (!adaKesalahan){ 
            try { 
                cn = DriverManager.getConnection(Koneksi.database+"?user="+ Koneksi.user+"&password="+Koneksi.password+""); 
            } catch (Exception ex) { 
                adaKesalahan = true;  
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
            } 
            
            if (!adaKesalahan){ 
                String SQLStatemen;
                Statement sta;
                ResultSet rset;
                
                try { 
                    SQLStatemen = "select * from kwitansi where no_beli='"+no_beli+"'"; 
                    sta = cn.createStatement(); 
                    rset = sta.executeQuery(SQLStatemen);                    
                    
                    rset.next(); 
                    rset.last();
                    listKwitansi = new Object[rset.getRow()][4];
                    
                    rset.first();
                    int i=0;
                    do { 
                        if (!rset.getString("kode_buku").equals("")){
                            listKwitansi[i] = new Object[]{ rset.getString("kode_buku"), rset.getInt("jumlah"), rset.getInt("harga")}; 		    
                        }
                        i++;
                    } while (rset.next());
                    
                    sta.close(); 
                    rset.close(); 
                    
                    if (listKwitansi.length>0) {
                        adaKesalahan = false;
                    }
                } catch (Exception ex){
                    adaKesalahan = true;
                }
            }
        }
        return !adaKesalahan;
    }
    
     
    public void cetakLaporan(String pembeli, String telepon){
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
	    cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+""); 
	  } catch (Exception ex) { 
	    adaKesalahan = true;  
		JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
	  } 
	  
	  if (!adaKesalahan){ 
              String SQLStatement="";
              try {
                  Statement statement = cn.createStatement();
                  
                  SQLStatement = "SELECT buku.`kode_buku` AS buku_kode_buku, "
                          +"buku.`judul_buku` AS buku_judul_buku, "
                          +"buku.`penerbit` AS buku_penerbit, "
                          +"kwitansi.`no_beli` AS kwitansi_no_beli, "
                          +"kwitansi.`kode_buku` AS kwitansi_kode_buku, "
                          +"kwitansi.`Jumlah` AS kwitansi_Jumlah,"
                          + "kwitansi.`harga` AS kwitansi_harga, "
                          +"(kwitansi.`harga`*kwitansi.`jumlah`) AS kwitansi_totalharga, "
                          +"pembeli.`no_beli` AS pembeli_no_beli, "
                          +"pembeli.`nama` AS pembeli_nama, "
                          +"pembeli.`alamat` AS pembeli_alamat, "
                          +"pembeli.`tlp` AS pembeli_tlp "
                          +"FROM "
                          +"`buku` buku INNER JOIN `kwitansi` kwitansi ON buku.`kode_buku` = kwitansi.`kode_buku` "
                          +"INNER JOIN `pembeli` pembeli ON kwitansi.`no_beli` = pembeli.`no_beli` ";                          
                  
                  if (!pembeli.equals(" ")){
                      SQLStatement = SQLStatement + "where pembeli.`nama` like '%"+pembeli+"%'";
                      
                      if (!telepon.equals("")){
                          SQLStatement = SQLStatement + " and pembeli.`tlp` like '%"+telepon+"%'";
                      }
                  } else {
                      if (!telepon.equals("")){
                          SQLStatement = SQLStatement + " where pembeli.`tlp`='"+telepon+"'";
                      }
                  }                  
                  
                  SQLStatement = SQLStatement +" ORDER BY "
                          +"pembeli.`alamat` ASC, "
                          +"pembeli.`tlp` ASC, "
                          +"pembeli.`nama` ASC";
                  
                  JasperDesign disain = JRXmlLoader.load(new File("").getAbsolutePath()+"/src/reports/KwitansiReport.jrxml");
		  JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                  
                  ResultSet resultSet = statement.executeQuery(SQLStatement);
                  JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                  
                  JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,new HashMap(),resultSetDataSource);
		  
		  JasperViewer.viewReport(cetak,false);
	    } catch (Exception ex) {  
                JOptionPane.showMessageDialog(null,"Gagal mencetak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
          }
        }
    }
}
