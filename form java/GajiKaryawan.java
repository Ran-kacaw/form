import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Riyadhul
 */
public class GajiKaryawan extends javax.swing.JFrame {

    ButtonGroup bgJenkel;
    ButtonGroup bgStatus;
    DefaultTableModel model;

    public GajiKaryawan() {
        initComponents();
        nilaibg();
        golongan();
        nilaikomponen();
        tampilData();
    }

    void nilaibg() {
        bgJenkel = new ButtonGroup();
        bgJenkel.add(OpPria);
        bgJenkel.add(OpWanita);

        bgStatus = new ButtonGroup();
        bgStatus.add(OpMenikah);
        bgStatus.add(OpBelumMenikah);
    }

    void nilaikomponen() {
        txtNip.setEnabled(true);
        txtNip.requestFocus();
        txtJumlahAnak.setEnabled(false);
        txtJumlahAnak.setText("0");
        txtTunjangan.setEnabled(false);
        txtPajak.setEnabled(false);
        txtPotongan.setText("0");
        txtGajiBersih.setEnabled(false);
    }

    void golongan() {
        cbGolongan.addItem("IIIa");
        cbGolongan.addItem("IIIb");
        cbGolongan.addItem("IIIc");
        cbGolongan.addItem("IIId");
    }

    // READ: Menampilkan data dari database ke JTable
    void tampilData() {
        model = new DefaultTableModel();
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Status");
        model.addColumn("Jml Anak");
        model.addColumn("Golongan");
        model.addColumn("Tunjangan");
        model.addColumn("Gaji Pokok");
        model.addColumn("Pajak (5%)");
        model.addColumn("Potongan");
        model.addColumn("Gaji Bersih");
        tbGaji.setModel(model);

        try {
            String sql = "SELECT * FROM tb_gaji";
            Connection conn = Koneksi.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("nip"),
                    res.getString("nama"),
                    res.getString("jenis_kelamin"),
                    res.getString("status"),
                    res.getInt("jumlah_anak"),
                    res.getString("golongan"),
                    res.getLong("tunjangan"),
                    res.getLong("gaji_pokok"),
                    res.getLong("pajak"),
                    res.getLong("potongan"),
                    res.getLong("gaji_bersih")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    void resetForm() {
        txtNip.setText("");
        txtNip.setEnabled(true);
        txtNama.setText("");
        txtJumlahAnak.setText("0");
        txtJumlahAnak.setEnabled(false);
        cbGolongan.setSelectedIndex(0);
        txtGapok.setText("");
        txtPajak.setText("");
        txtPotongan.setText("0");
        txtGajiBersih.setText("");
        bgJenkel.clearSelection();
        bgStatus.clearSelection();
        txtNip.requestFocus();
    }

    String getJenisKelamin() {
        if (OpPria.isSelected()) return "Pria";
        if (OpWanita.isSelected()) return "Wanita";
        return "";
    }

    String getStatus() {
        if (OpMenikah.isSelected()) return "Menikah";
        if (OpBelumMenikah.isSelected()) return "Belum Menikah";
        return "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        OpPria = new javax.swing.JRadioButton();
        OpWanita = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        OpMenikah = new javax.swing.JRadioButton();
        OpBelumMenikah = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtJumlahAnak = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbGolongan = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTunjangan = new javax.swing.JTextField();
        txtGapok = new javax.swing.JTextField();
        txtGajiBersih = new javax.swing.JTextField();
        btHitung = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        btUbah = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGaji = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtPajak = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPotongan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Form Gaji Karyawan");

        jLabel2.setText("NIP");

        jLabel3.setText("Nama");

        jLabel4.setText("Jenis Kelamin");

        OpPria.setText("Pria");

        OpWanita.setText("Wanita");

        jLabel5.setText("Status");

        OpMenikah.setText("Menikah");
        OpMenikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpMenikahActionPerformed(evt);
            }
        });

        OpBelumMenikah.setText("Belum Menikah");
        OpBelumMenikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpBelumMenikahActionPerformed(evt);
            }
        });

        jLabel6.setText("Jumlah Anak");

        jLabel7.setText("Golongan");

        jLabel8.setText("Tunjangan");

        jLabel9.setText("Gaji Pokok");

        cbGolongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGolonganActionPerformed(evt);
            }
        });

        jLabel10.setText("Gaji Bersih");

        btHitung.setText("Hitung");
        btHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHitungActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        btUbah.setText("Ubah");
        btUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahActionPerformed(evt);
            }
        });

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        tbGaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIP", "Nama", "Jenis Kelamin", "Status", "Jml Anak", "Golongan", "Tunjangan", "Gaji Pokok", "Pajak (5%)", "Potongan", "Gaji Bersih"
            }
        ));
        tbGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGajiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGaji);

        jLabel11.setText("Pajak (5%)");

        jLabel12.setText("Potongan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(OpPria)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(OpWanita))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(OpMenikah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(OpBelumMenikah))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtGajiBersih, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(txtPotongan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPajak, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGapok, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTunjangan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtJumlahAnak, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNip, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(cbGolongan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btHitung, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHitung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSimpan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(OpPria)
                    .addComponent(OpWanita)
                    .addComponent(btUbah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(OpMenikah)
                    .addComponent(OpBelumMenikah)
                    .addComponent(btHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtJumlahAnak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbGolongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGapok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGajiBersih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void OpMenikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpMenikahActionPerformed
        if (OpMenikah.isSelected()) {
            txtJumlahAnak.setEnabled(true);
            txtJumlahAnak.setText("");
            txtJumlahAnak.requestFocus();
        }
    }//GEN-LAST:event_OpMenikahActionPerformed

    private void OpBelumMenikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpBelumMenikahActionPerformed
        if (OpBelumMenikah.isSelected()) {
            txtJumlahAnak.setEnabled(false);
            txtJumlahAnak.setText("0");
        }
    }//GEN-LAST:event_OpBelumMenikahActionPerformed

    private void cbGolonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGolonganActionPerformed
        String gol = (String) cbGolongan.getSelectedItem();
        if ("IIIa".equals(gol)) {
            txtTunjangan.setText("100000");
        } else if ("IIIb".equals(gol)) {
            txtTunjangan.setText("200000");
        } else if ("IIIc".equals(gol)) {
            txtTunjangan.setText("300000");
        } else if ("IIId".equals(gol)) {
            txtTunjangan.setText("400000");
        }
    }//GEN-LAST:event_cbGolonganActionPerformed

    private void btHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHitungActionPerformed
        try {
            long jumlahAnak = txtJumlahAnak.getText().isEmpty() ? 0 : Long.parseLong(txtJumlahAnak.getText());
            long tunjangan = Long.parseLong(txtTunjangan.getText());
            long gapok = Long.parseLong(txtGapok.getText());
            long potongan = txtPotongan.getText().isEmpty() ? 0 : Long.parseLong(txtPotongan.getText());

            long totalTunjangan = jumlahAnak * tunjangan;
            long gajiKotor = gapok + totalTunjangan;
            long pajak = (long) (0.05 * gajiKotor); // Pajak otomatis 5% dari gaji kotor
            long gaber = gajiKotor - pajak - potongan;

            txtPajak.setText(String.valueOf(pajak));
            txtGajiBersih.setText(String.valueOf(gaber));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pastikan Jumlah Anak, Gaji Pokok, dan Potongan diisi angka yang valid!");
        }
    }//GEN-LAST:event_btHitungActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    // CREATE: Simpan data ke database
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if (txtNip.getText().isEmpty() || txtNama.getText().isEmpty() || getJenisKelamin().isEmpty() 
                || getStatus().isEmpty() || txtGajiBersih.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi semua data dan klik Hitung terlebih dahulu!");
            return;
        }

        try {
            String sql = "INSERT INTO tb_gaji (nip, nama, jenis_kelamin, status, jumlah_anak, golongan, tunjangan, gaji_pokok, pajak, potongan, gaji_bersih) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = Koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNip.getText());
            pst.setString(2, txtNama.getText());
            pst.setString(3, getJenisKelamin());
            pst.setString(4, getStatus());
            pst.setInt(5, Integer.parseInt(txtJumlahAnak.getText()));
            pst.setString(6, cbGolongan.getSelectedItem().toString());
            pst.setLong(7, Long.parseLong(txtTunjangan.getText()));
            pst.setLong(8, Long.parseLong(txtGapok.getText()));
            pst.setLong(9, Long.parseLong(txtPajak.getText()));
            pst.setLong(10, txtPotongan.getText().isEmpty() ? 0 : Long.parseLong(txtPotongan.getText()));
            pst.setLong(11, Long.parseLong(txtGajiBersih.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Menyimpan Data: " + e.getMessage());
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    // UPDATE: Ubah data yang dipilih berdasarkan NIP
    private void btUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahActionPerformed
        if (txtNip.getText().isEmpty() || txtGajiBersih.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dan klik Hitung sebelum mengubah!");
            return;
        }

        try {
            String sql = "UPDATE tb_gaji SET nama=?, jenis_kelamin=?, status=?, jumlah_anak=?, golongan=?, tunjangan=?, gaji_pokok=?, pajak=?, potongan=?, gaji_bersih=? WHERE nip=?";
            Connection conn = Koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, getJenisKelamin());
            pst.setString(3, getStatus());
            pst.setInt(4, Integer.parseInt(txtJumlahAnak.getText()));
            pst.setString(5, cbGolongan.getSelectedItem().toString());
            pst.setLong(6, Long.parseLong(txtTunjangan.getText()));
            pst.setLong(7, Long.parseLong(txtGapok.getText()));
            pst.setLong(8, Long.parseLong(txtPajak.getText()));
            pst.setLong(9, txtPotongan.getText().isEmpty() ? 0 : Long.parseLong(txtPotongan.getText()));
            pst.setLong(10, Long.parseLong(txtGajiBersih.getText()));
            pst.setString(11, txtNip.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Mengubah Data: " + e.getMessage());
        }
    }//GEN-LAST:event_btUbahActionPerformed

    // DELETE: Hapus data berdasarkan NIP
    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        if (txtNip.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data NIP: " + txtNip.getText() + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM tb_gaji WHERE nip=?";
                Connection conn = Koneksi.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNip.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus!");
                tampilData();
                resetForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Menghapus Data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btHapusActionPerformed

    // Mengisi form saat baris pada JTable diklik
    private void tbGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGajiMouseClicked
        int baris = tbGaji.getSelectedRow();
        if (baris != -1) {
            txtNip.setText(tbGaji.getValueAt(baris, 0).toString());
            txtNip.setEnabled(false);
            txtNama.setText(tbGaji.getValueAt(baris, 1).toString());

            String jenkel = tbGaji.getValueAt(baris, 2).toString();
            if ("Pria".equalsIgnoreCase(jenkel)) {
                OpPria.setSelected(true);
            } else {
                OpWanita.setSelected(true);
            }

            String status = tbGaji.getValueAt(baris, 3).toString();
            if ("Menikah".equalsIgnoreCase(status)) {
                OpMenikah.setSelected(true);
                txtJumlahAnak.setEnabled(true);
            } else {
                OpBelumMenikah.setSelected(true);
                txtJumlahAnak.setEnabled(false);
            }

            txtJumlahAnak.setText(tbGaji.getValueAt(baris, 4).toString());
            cbGolongan.setSelectedItem(tbGaji.getValueAt(baris, 5).toString());
            txtTunjangan.setText(tbGaji.getValueAt(baris, 6).toString());
            txtGapok.setText(tbGaji.getValueAt(baris, 7).toString());
            txtPajak.setText(tbGaji.getValueAt(baris, 8).toString());
            txtPotongan.setText(tbGaji.getValueAt(baris, 9).toString());
            txtGajiBersih.setText(tbGaji.getValueAt(baris, 10).toString());
        }
    }//GEN-LAST:event_tbGajiMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GajiKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GajiKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton OpBelumMenikah;
    private javax.swing.JRadioButton OpMenikah;
    private javax.swing.JRadioButton OpPria;
    private javax.swing.JRadioButton OpWanita;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btHitung;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btUbah;
    private javax.swing.JComboBox<String> cbGolongan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbGaji;
    private javax.swing.JTextField txtGajiBersih;
    private javax.swing.JTextField txtGapok;
    private javax.swing.JTextField txtJumlahAnak;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtPajak;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtTunjangan;
    // End of variables declaration//GEN-END:variables
}
