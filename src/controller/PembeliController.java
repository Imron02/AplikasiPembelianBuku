/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Pembeli;
import view.FormUtama;
import view.DaftarPembeli;

/**
 *
 * @author aim
 */
public class PembeliController {
     private Pembeli pembeli = new  Pembeli();
     private DaftarPembeli daftarPembeli = new DaftarPembeli(null,true);
     
     public void simpan(javax.swing.JTextField no_beli, javax.swing.JTextField nama, 
             javax.swing.JTextField alamat, javax.swing.JTextField tlp){
         if (!no_beli.getText().equals("")){
             pembeli.setNo_beli(no_beli.getText());
             pembeli.setNama(nama.getText());
             pembeli.setAlamat(alamat.getText());
             pembeli.setTlp(tlp.getText());
             if (pembeli.simpan()){
                 FormUtama.formPembeli.setNoPembelian("");
                 FormUtama.formPembeli.setNama("");
                 FormUtama.formPembeli.setAlamat(" ");
                 FormUtama.formPembeli.setTelepon(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"No Beli tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void hapus(javax.swing.JTextField no_beli){
         if (!no_beli.getText().equals("")){
             if (pembeli.hapus(no_beli.getText())){
                 FormUtama.formPembeli.setNoPembelian("");
                 FormUtama.formPembeli.setNama("");
                 FormUtama.formPembeli.setAlamat(" ");
                 FormUtama.formPembeli.setTelepon(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"No Beli tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void cari(javax.swing.JTextField no_beli){
         if (!no_beli.getText().equals("")){
             if (pembeli.baca(no_beli.getText())){
                 FormUtama.formPembeli.setNama(pembeli.getNama());
                 FormUtama.formPembeli.setAlamat(pembeli.getAlamat());
                 FormUtama.formPembeli.setTelepon(pembeli.getTlp());
             } else {
                 FormUtama.formPembeli.setNama("");
                 FormUtama.formPembeli.setAlamat(" ");
                 FormUtama.formPembeli.setTelepon(" ");
             }
         } else {
             JOptionPane.showMessageDialog(null,"No Beli tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
         }
     }
     public void tampilkanDaftar(){
         if (!daftarPembeli.isVisible()){
             DaftarPembeli.listPembeli = pembeli.bacaDaftar();
             daftarPembeli = new DaftarPembeli(null, true);
             daftarPembeli.setVisible(true);
             if (!DaftarPembeli.no_beliDipilih.equals("")) {
                 if (pembeli.baca(DaftarPembeli.no_beliDipilih)){
                     FormUtama.formPembeli.setNoPembelian(pembeli.getNo_beli());
                     FormUtama.formPembeli.setNama(pembeli.getNama());
                     FormUtama.formPembeli.setAlamat(pembeli.getAlamat());
                     FormUtama.formPembeli.setTelepon(pembeli.getTlp());
                 }
             }
         }
     }
     
}
