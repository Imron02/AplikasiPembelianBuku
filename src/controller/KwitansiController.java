/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Pembeli;
import model.Buku;
import model.Kwitansi;
import view.DaftarPembeli;
import view.FormUtama;

/**
 *
 * @author aim
 */
public class KwitansiController {
    private Pembeli pembeli = new Pembeli();
    private Buku buku = new Buku();
    private Kwitansi kwitansi = new Kwitansi();
    private DaftarPembeli daftarPembeli = new DaftarPembeli(null,true);    
    
    public void cariPembeli(javax.swing.JTextField no_beli){
        if (!no_beli.getText().equals("")){
            if (pembeli.baca(no_beli.getText())){
                FormUtama.formKwitansi.setNama(pembeli.getNama());
                FormUtama.formKwitansi.setAlamat(pembeli.getAlamat());
                FormUtama.formKwitansi.setTelepon(pembeli.getTlp());
                FormUtama.formKwitansi.clearKwitansiTable();
                
                int jumlahKwitansi=0;
                if (kwitansi.baca(no_beli.getText())){
                    Object[][] listKwitansi = kwitansi.getListKwitansi();
                    FormUtama.formKwitansi.clearKwitansiTable();
                    
                    if (listKwitansi.length>0){
                        for (int i=0; i<listKwitansi.length;i++){
                            if (!((String)listKwitansi[i][0]).equals("")){
                                String judulBuku="";
                                if (buku.baca((String) listKwitansi[i][0])){
                                    judulBuku = buku.getJudulBuku();                      
                                }
                                FormUtama.formKwitansi.setTambahKwitansi(new Object[]{listKwitansi[i][0],judulBuku,listKwitansi[i][1],listKwitansi[i][2]});
                                jumlahKwitansi++;
                            }
                        }
                    }
                } 
                
                if (jumlahKwitansi==0) {
                    FormUtama.formKwitansi.setTambahKwitansi(new Object[]{});
                }
            } else {
                FormUtama.formKwitansi.setNama("");
                FormUtama.formKwitansi.setAlamat("");
                FormUtama.formKwitansi.setTelepon("");
                FormUtama.formKwitansi.clearKwitansiTable();
                FormUtama.formKwitansi.setTambahKwitansi(new Object[]{});
            }
        } else {
            JOptionPane.showMessageDialog(null,"no beli tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void tampilkanDaftarPembeli(){
	if (!daftarPembeli.isVisible()){
            DaftarPembeli.listPembeli = pembeli.bacaDaftar();
            daftarPembeli = new DaftarPembeli(null, true);
            daftarPembeli.setVisible(true);	
            
            if (!DaftarPembeli.no_beliDipilih.equals("")) {
                if (pembeli.baca(DaftarPembeli.no_beliDipilih)){
                    FormUtama.formKwitansi.setNoPembeli(pembeli.getNo_beli());
                    FormUtama.formKwitansi.setNama(pembeli.getNama());
                    FormUtama.formKwitansi.setAlamat(pembeli.getAlamat());
                    FormUtama.formKwitansi.setTelepon(pembeli.getTlp());
                    FormUtama.formKwitansi.clearKwitansiTable();
                    
                    int jumlahKwitansi=0;
                    if (kwitansi.baca(DaftarPembeli.no_beliDipilih)){
                        Object[][] listKwitansi = kwitansi.getListKwitansi();
                        FormUtama.formKwitansi.clearKwitansiTable();
                        
                        if (listKwitansi.length>0){
                            for (int i=0; i<listKwitansi.length;i++){
                                if (!((String)listKwitansi[i][0]).equals("")){
                                    String judulBuku="";
                                    if (buku.baca((String)listKwitansi[i][0])){
                                        judulBuku = buku.getJudulBuku();                      
                                    }
                                    FormUtama.formKwitansi.setTambahKwitansi(new Object[]{listKwitansi[i][0],judulBuku,listKwitansi[i][1],listKwitansi[i][2],listKwitansi[i][3]});
                                    jumlahKwitansi++;
                                }
                            }
                        }
                    } 
                    if (jumlahKwitansi==0) {
                        FormUtama.formKwitansi.setTambahKwitansi(new Object[]{});
                    }
                }
            }
        }
    }
    
    public void cariBuku(String kode_buku){
        if (buku.baca(kode_buku)){
            FormUtama.formKwitansi.setJudulBuku(buku.getJudulBuku());                      
        } else {
            FormUtama.formKwitansi.setJudulBuku("");            
        }    
    }
    
    public void simpan(javax.swing.JTextField no_beli, javax.swing.JTable kwitansiTable){
	if (!no_beli.getText().equals("")){
            kwitansi.setNoPembeli(no_beli.getText());
            Object[][] listKwitansi = new Object[kwitansiTable.getRowCount()][3];
            
            for (int i=0; i <kwitansiTable.getRowCount();i++){
                listKwitansi[i][0] = kwitansiTable.getValueAt(i, 0);
                listKwitansi[i][1] = kwitansiTable.getValueAt(i, 2);
                listKwitansi[i][2] = kwitansiTable.getValueAt(i, 3);
            }
            
            kwitansi.setListKwitansi(listKwitansi);            
            
            if (kwitansi.simpan()){
                FormUtama.formKwitansi.setNoPembeli("");
                FormUtama.formKwitansi.setNama("");
                FormUtama.formKwitansi.setAlamat("");
                FormUtama.formKwitansi.setTelepon("");
                FormUtama.formKwitansi.clearKwitansiTable();
                FormUtama.formKwitansi.setTambahKwitansi(new Object[]{});
            }
        } else {
            JOptionPane.showMessageDialog(null,"no beli tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cetakLaporan(javax.swing.JTextField pembeliTextField,
            javax.swing.JTextField teleponTextField){
        String namaPembeli;
        String telepon;
        if(pembeliTextField.equals(" ")){
            namaPembeli=" ";
        } else {
            namaPembeli = pembeliTextField.getText().toString();
        }
        if (teleponTextField.equals(" ")){
            telepon="";
        } else {
            telepon = teleponTextField.getText().toString();
        }

        kwitansi.cetakLaporan(namaPembeli, telepon);
    }
}
