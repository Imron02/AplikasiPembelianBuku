/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Buku;
import view.DaftarBuku;
import view.FormUtama;

/**
 *
 * @author aim
 */
public class BukuController {
    private Buku buku = new Buku();
    private DaftarBuku daftarBuku = new DaftarBuku(null,true);
    
    public void simpan(javax.swing.JTextField kode_buku, javax.swing.JTextField judul_buku, 
             javax.swing.JTextField penerbit){
         if (!kode_buku.getText().equals("")){
             buku.setKodeBuku(kode_buku.getText());
             buku.setJudulBuku(judul_buku.getText());
             buku.setPenerbit(penerbit.getText());
             if (buku.simpan()){
                 FormUtama.formBuku.setKodeBuku("");
                 FormUtama.formBuku.setJudulBuku("");
                 FormUtama.formBuku.setPenerbit(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"Kode Buku tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void hapus(javax.swing.JTextField kode_buku){
         if (!kode_buku.getText().equals("")){
             if (buku.hapus(kode_buku.getText())){
                 FormUtama.formBuku.setKodeBuku("");
                 FormUtama.formBuku.setJudulBuku("");
                 FormUtama.formBuku.setPenerbit(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"Kode Buku tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void cari(javax.swing.JTextField kode_buku){
         if (!kode_buku.getText().equals("")){
             if (buku.baca(kode_buku.getText())){
                 FormUtama.formBuku.setJudulBuku(buku.getJudulBuku());
                 FormUtama.formBuku.setPenerbit(buku.getPenerbit());
             } else {
                 FormUtama.formBuku.setJudulBuku("");
                 FormUtama.formBuku.setPenerbit(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"Kode Buku tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void tampilkanDaftar(){
         if (!daftarBuku.isVisible()){
             DaftarBuku.listBuku = buku.bacaDaftar();
             daftarBuku = new DaftarBuku(null, true);
             daftarBuku.setVisible(true);
             if (!DaftarBuku.kodeBukuDipilih.equals("")) {
                 if (buku.baca(DaftarBuku.kodeBukuDipilih)){
                     FormUtama.formBuku.setKodeBuku(buku.getKodeBuku());
                     FormUtama.formBuku.setJudulBuku(buku.getJudulBuku());
                     FormUtama.formBuku.setPenerbit(buku.getPenerbit());
                 }
             }
         }
     }
}
