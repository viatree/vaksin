/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaksin;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manbi
 */
public class onKush {
    public static String id_ortu, id_anak, nIbu, nAyah;
    
    public static void createAnak(String nama, String ttl, String gender, String tBadan, String bBadan){
        try {
            String sql = "INSERT INTO `anak` (`nama`, `ttlahir`, `gender`, `id_ortu`, `tbadan`, `bbadan`) "
                    + "VALUES ('"+nama+"', '"+ttl+"', '"+gender+"', '"+id_ortu+"', '"+tBadan+"', '"+bBadan+"')";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static boolean register(String email, String pwd, String nIbu, String nAyah, String tlp, String alamat){
        boolean bool=false;
        try {
            String sql = "INSERT INTO `ortu` (`email`, `password`, `nIbu`, `nAyah`, `telepon`, `alamat`) "
                    + "VALUES ('"+email+"', '"+pwd+"', '"+nIbu+"', '"+nAyah+"', '"+tlp+"', '"+alamat+"')";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.Statement stm=conn.createStatement();
           if (stm.executeUpdate(sql)==1){
                bool=true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return bool;
    }
    
    //List anak berdasarkan Ortu
    public static void getAnakByOrtu(JComboBox box, String id_ortu){
        try {
            String sql = "SELECT `id_anak` from anak WHERE id_ortu = "+id_ortu;
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()){
                box.addItem(res.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //Tabel anak berdasarkan ortu
    public static void read(JTable table, int id){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Anak");
        model.addColumn("Nama");
        model.addColumn("TTL");
        model.addColumn("Kelamin");
        model.addColumn("TB");
        model.addColumn("BB");

        try {
            int no=1;
            String sql = "select * from anak where id_ortu = '"+id+"'";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(6),res.getString(7)});
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }
    
    public static void addAsking(String id_anak, String pVaksin){
        try {
            String sql = "INSERT INTO `asking` (`id_anak`, `pernah_vaksin`) VALUES ('"+id_anak+"', '"+pVaksin+"')";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void addJadwal(String bulan, String tipe){
        try {
            String sql = "INSERT INTO `jadwal_vaksin` (`id_anak`, `bulan`, `jenis_vaksin`) VALUES ('"+id_anak+"', '"+bulan+"' , '"+tipe+"')";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static boolean login(String email, String password){
        boolean cok=false;
        try {
            String sql = "SELECT * from `ortu` WHERE email = '"+email+"' AND password = '"+password+"' ";
            java.sql.Connection conn=(Connection)db.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            if (res.next()){
                id_ortu=res.getString("id_ortu");
                nAyah=res.getString("nAyah");
                nIbu=res.getString("nIbu");
                cok=true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cok;
    }
}
