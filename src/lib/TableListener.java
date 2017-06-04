/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author F
 */
public class TableListener extends MouseAdapter{
    private JTable table;
     
    public TableListener(JTable table) {
        this.table = table;
    }
     
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int column = table.columnAtPoint(point);
         if(column == 0){
             int jml_check = 0;
             for(int i=0;i<table.getRowCount();i++){
                 Boolean check = Boolean.valueOf(table.getValueAt(i, 0).toString());
                 if(check){
                     jml_check +=1;
                 }
             }
             if(jml_check == table.getRowCount()){ 
                 for(int i=0;i<table.getRowCount();i++){
                     table.setValueAt(false, i, 0);
                 }
             }else{
                 for(int i=0;i<table.getRowCount();i++){
                     table.setValueAt(true, i, 0);
                 }
             }
         }
        // do your real thing here...
        //JOptionPane.showMessageDialog(table, "ke ceklis semua :D");
    }
}
