//package Projeto2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Frame extends javax.swing.JFrame {

    public Frame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JF = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selecionar Arquivo CEP Ordenado");

        JF.setDialogTitle("");
        JF.setToolTipText("");
        JF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JFActionPerformed
        File CO_Path = JF.getSelectedFile();
        if(CO_Path==null){
            System.exit(-1);
        }
        if(!CO_Path.toString().endsWith("cep_ordenado.dat")){
            syso("O arquivo selecionado não é válido.");
            String Resultado = "O Arquivo selecionado não é válido.";
            JOptionPane.showMessageDialog(null, Resultado, "Resultado da busca", 1);
            System.exit(-1);
        }
        this.dispose();
        String Sea = JOptionPane.showInputDialog(null, "Digite o CEP a ser procurado: ", "Digite o CEP", 3);
        if (Sea.length() != 8) {
            syso("O Cep inserido é inválido.");
            String Resultado = "O Cep inserido é inválido.";
            JOptionPane.showMessageDialog(null, Resultado, "Resultado da busca", 1);
            System.exit(-1);
        }
        RandomAccessFile Cep_Ord = null;
        try {
            Cep_Ord = new RandomAccessFile(CO_Path, "r");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Endereco End = new Endereco();
        long Esq = 0;
        long Dir = 0;
        try {
            Dir = (Cep_Ord.length() / 300) - 1;
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        long Mei = 0;
        int ContaLoop = 0;
        boolean valorEncontrado = false;
        while (Esq <= Dir) {
            Mei = (Esq + Dir) / 2;
            try {
                //syso("M: " + Long.toString(Mei));
                Cep_Ord.seek(Mei * 300);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                End.leEndereco(Cep_Ord);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (End.getCep().equals(Sea)) {
                End.escreveEndereco();
                valorEncontrado = true;
                syso("Dados finais: Esq: " + Esq + " Dir " + Dir + " Mei: " + Mei + " Iterações: " + ContaLoop);
                String Resultado = "Exibindo resultado da busca pelo cep: " + End.getCep() + "\n"
                        + "Logradouro: " + End.getLogradouro() + "\n"
                        + "Bairro: " + End.getBairro() + "\n"
                        + "Cidade: " + End.getCidade() + "\n"
                        + "Estado: " + End.getEstado();
                JOptionPane.showMessageDialog(null, Resultado, "Resultado da busca", 1);
                break;
            } else {
                if (Integer.parseInt(End.getCep()) < Integer.parseInt(Sea)) {
                    Esq = Mei + 1;
                    //syso("E: " + Long.toString(Esq));
                } else {
                    Dir = Mei - 1;
                    //syso("D: " + Long.toString(Dir));
                }
            }
            ContaLoop++;
        }
        if (!valorEncontrado) {
            syso("CEP inexistente");
            String Resultado = "CEP inexistente";
            JOptionPane.showMessageDialog(null, Resultado, "Resultado da busca", 1);
        }

    }//GEN-LAST:event_JFActionPerformed
public void syso(Object texto) {
        System.out.println(texto);
    }

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
            java.util.logging.Logger.getLogger(Frame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser JF;
    // End of variables declaration//GEN-END:variables
}
