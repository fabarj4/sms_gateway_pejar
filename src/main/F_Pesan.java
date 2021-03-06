/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author F
 */
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import lib.Pesan;
import lib.TableListener;
import lib.koneksi;

public class F_Pesan extends javax.swing.JFrame {

    /**
     * Creates new form F_Pesan
     */
    private koneksi konek = new koneksi();
    private Pesan  pesan = new Pesan();
    private F_ProgressBar pgBar = new F_ProgressBar();
    
    public F_Pesan() {
        setResizable(false);
        initComponents();
        try {
            getKontak();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //digunakan untuk mengambil respon klik user di header table
        JTableHeader header = table_kontak.getTableHeader();
        header.addMouseListener(new TableListener(table_kontak));
        
        //digunakan untuk mengambil respon klik di baris
        table_kontak.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = table_kontak.rowAtPoint(evt.getPoint());
                int col = table_kontak.columnAtPoint(evt.getPoint());
                //JOptionPane.showMessageDialog(null, "baris ke - "+row);
                if(col!=0){
                    Boolean check = Boolean.valueOf(table_kontak.getValueAt(row, 0).toString());
                    if(check){
                        table_kontak.setValueAt(false, row, 0);
                    }else{
                        table_kontak.setValueAt(true, row, 0);
                    }
                }
            }
        });
        
        cmbBox_Tipe.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipe = 0;
                if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("semua")){
                    tipe = 3;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("guru")){
                    tipe = 0;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("siswa")){
                    tipe = 1;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("orang tua siswa")){
                    tipe = 2;
                }
                try {
                    if(tipe!=3){
                        getKontak(tipe);
                    }else{
                        getKontak();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                }
                //JOptionPane.showMessageDialog(null, "data : "+cmbBox_Tipe.getSelectedItem().toString()+" tipe = "+tipe);
            }
        });
        
        in_search.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println(in_search.getText());
                int tipe = 0;
                if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("semua")){
                    tipe = 3;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("guru")){
                    tipe = 0;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("siswa")){
                    tipe = 1;
                }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("orang tua siswa")){
                    tipe = 2;
                }
                try {
                    getKontak(in_search.getText(),tipe);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    int tipe = 0;
                    if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("semua")){
                        tipe = 3;
                    }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("guru")){
                        tipe = 0;
                    }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("siswa")){
                        tipe = 1;
                    }else if(cmbBox_Tipe.getSelectedItem().toString().toLowerCase().equals("orang tua siswa")){
                        tipe = 2;
                    }
                    getKontak(in_search.getText(),tipe);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println(in_search.getText());
            }
        });
        
        /*
        in_search.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent key){
                try {
                    getKontak(in_search.getText());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(in_search.getText());
            }
        });*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbBox_Tipe = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        in_search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_pesan = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_kontak = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setForeground(new java.awt.Color(0, 51, 51));

        cmbBox_Tipe.setBackground(new java.awt.Color(102, 102, 102));
        cmbBox_Tipe.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cmbBox_Tipe.setForeground(new java.awt.Color(0, 51, 51));
        cmbBox_Tipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Guru", "Siswa", "Orang Tua Siswa" }));
        cmbBox_Tipe.setBorder(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Type :");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Find:");

        in_search.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        in_search.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                in_searchInputMethodTextChanged(evt);
            }
        });
        in_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_searchActionPerformed(evt);
            }
        });

        txt_pesan.setColumns(20);
        txt_pesan.setRows(5);
        txt_pesan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txt_pesan);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Messages :");

        table_kontak.setBackground(new java.awt.Color(153, 255, 255));
        table_kontak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table_kontak.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        table_kontak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_kontak);

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SEND");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BACK");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setContentAreaFilled(false);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SMS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(5, 5, 5)
                                .addComponent(cmbBox_Tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(in_search, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(in_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbBox_Tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void in_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int count_check = 0;
        ArrayList hp = new ArrayList();
        for(int i = 0;i<table_kontak.getRowCount();i++){
            Boolean check = Boolean.valueOf(table_kontak.getValueAt(i,0).toString());
            String no_hp = table_kontak.getValueAt(i, 3).toString();
            if(check){
                count_check +=1;
                hp.add(no_hp);
            }
        }
        for(int i=0;i<hp.size();i++){
            System.out.println(hp.get(i).toString());
        }
        try {
            //pgBar.setVisible(true);
            pesan.sendMessage(hp, txt_pesan.getText());
            //pgBar.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(F_Pesan.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null, hp);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void in_searchInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_in_searchInputMethodTextChanged
        
    }//GEN-LAST:event_in_searchInputMethodTextChanged

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jButton1.setBackground(new java.awt.Color(0, 51, 102));
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        jButton1.setBackground(new java.awt.Color(0, 102, 102));
    }//GEN-LAST:event_jButton1MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_Pesan().setVisible(true);
            }
        });
    }
    
    private void getKontak() throws ClassNotFoundException, SQLException, IOException{
        String query = "SELECT * FROM kontak";
        Statement st = konek.Connect().createStatement();
        ResultSet sql = st.executeQuery(query);
        int baris=1;
        int i=0;
        String tipe = null;
        
        customTableModel c_model = new customTableModel();
        
        while(sql.next()){
            baris++;
        }
        String isi[][] = new String[baris][5];
        sql.beforeFirst();
        while(sql.next()){
            switch(sql.getInt("tipe")){
                case 0:
                    tipe= "Guru";
                    break;
                case 1  :
                    tipe= "Siswa";
                    break;
                case 2:
                    tipe= "Orang Tua Siswa";
                    break;
            }
            c_model.addRow(new Object[]{
                false,
                Integer.toString(i+1),
                sql.getString("nama"),
                sql.getString("hp"),
                tipe,
            });
            /*
            isi[i][0]=Integer.toString(i+1);
            isi[i][1]=sql.getString("nama");
            isi[i][2]=sql.getString("hp");
            switch(sql.getInt("tipe")){
                case 0:
                    isi[i][3]= "Guru";
                    break;
                case 1  :
                    isi[i][3]= "Siswa";
                    break;
                case 2:
                    isi[i][3]= "Orang Tua Siswa";
                    break;
            }
            isi[i][4]="false";*/
            i++;
        }
        String namakolom[] = {"","NO","Nama","HP","Ket"};
        //DefaultTableModel model = new DefaultTableModel(isi,namakolom);
        
        table_kontak.setModel(c_model);
    }
    
    private void getKontak(int tipeKontak) throws ClassNotFoundException, SQLException, IOException{
        String query = "SELECT * FROM kontak WHERE tipe = "+tipeKontak;
        Statement st = konek.Connect().createStatement();
        ResultSet sql = st.executeQuery(query);
        int baris=1;
        int i=0;
        String tipe = null;
        
        customTableModel c_model = new customTableModel();
        
        while(sql.next()){
            baris++;
        }
        String isi[][] = new String[baris][5];
        sql.beforeFirst();
        while(sql.next()){
            switch(sql.getInt("tipe")){
                case 0:
                    tipe= "Guru";
                    break;
                case 1  :
                    tipe= "Siswa";
                    break;
                case 2:
                    tipe= "Orang Tua Siswa";
                    break;
            }
            c_model.addRow(new Object[]{
                false,
                Integer.toString(i+1),
                sql.getString("nama"),
                sql.getString("hp"),
                tipe,
            });
            /*
            isi[i][0]=Integer.toString(i+1);
            isi[i][1]=sql.getString("nama");
            isi[i][2]=sql.getString("hp");
            switch(sql.getInt("tipe")){
                case 0:
                    isi[i][3]= "Guru";
                    break;
                case 1  :
                    isi[i][3]= "Siswa";
                    break;
                case 2:
                    isi[i][3]= "Orang Tua Siswa";
                    break;
            }
            isi[i][4]="false";*/
            i++;
        }
        String namakolom[] = {"","NO","Nama","HP","Ket"};
        //DefaultTableModel model = new DefaultTableModel(isi,namakolom);
        
        table_kontak.setModel(c_model);
    }
    
    private void getKontak(String cari, int tipeKontak) throws ClassNotFoundException, SQLException, IOException{
        String query = null;
        if(tipeKontak == 3){
            query = "SELECT * FROM kontak WHERE nama like '%"+cari+"%' || hp like '%"+cari+"%'";
        }else{
            query = "SELECT * FROM kontak WHERE (nama like '%"+cari+"%' || hp like '%"+cari+"%') && tipe ='"+tipeKontak+"'";
        }
        Statement st = konek.Connect().createStatement();
        ResultSet sql = st.executeQuery(query);
        int baris=1;
        int i=0;
        String tipe = null;
        
        customTableModel c_model = new customTableModel();
        
        while(sql.next()){
            baris++;
        }
        String isi[][] = new String[baris][5];
        sql.beforeFirst();
        while(sql.next()){
            switch(sql.getInt("tipe")){
                case 0:
                    tipe= "Guru";
                    break;
                case 1  :
                    tipe= "Siswa";
                    break;
                case 2:
                    tipe= "Orang Tua Siswa";
                    break;
            }
            c_model.addRow(new Object[]{
                false,
                Integer.toString(i+1),
                sql.getString("nama"),
                sql.getString("hp"),
                tipe,
            });
            /*
            isi[i][0]=Integer.toString(i+1);
            isi[i][1]=sql.getString("nama");
            isi[i][2]=sql.getString("hp");
            switch(sql.getInt("tipe")){
                case 0:
                    isi[i][3]= "Guru";
                    break;
                case 1  :
                    isi[i][3]= "Siswa";
                    break;
                case 2:
                    isi[i][3]= "Orang Tua Siswa";
                    break;
            }
            isi[i][4]="false";*/
            i++;
        }
        String namakolom[] = {"","NO","Nama","HP","Ket"};
        //DefaultTableModel model = new DefaultTableModel(isi,namakolom);
        
        table_kontak.setModel(c_model);
    }
    
    private class customTableModel extends DefaultTableModel{

        public customTableModel() {
            super(new String[]{"Ceklis","NO","Nama","HP","Ket"},0);
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch(columnIndex){
                case 0 :
                    return Boolean.class;
                case 1 :
                    return String.class;
                case 2 :
                    return String.class;
                case 3 :
                    return String.class;
                case 4 :
                    return String.class;
                default :
                    return String.class;
            }
        }
        
        
        @Override
        public boolean isCellEditable(int row, int column) {
          return column == 0;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBox_Tipe;
    private javax.swing.JTextField in_search;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_kontak;
    private javax.swing.JTextArea txt_pesan;
    // End of variables declaration//GEN-END:variables
    
    
    
    
}
