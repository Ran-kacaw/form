import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class form {

    private JFrame window;
    private JTextField txtNip;
    private JTextField txtNama;
    private JRadioButton OpPria;
    private JRadioButton OpWanita;
    private JRadioButton OpMenikah;
    private JRadioButton OpBelumMenikah;
    private JTextField txtJumlahAnak;
    private JComboBox<String> cbGolongan;
    private JTextField txtTunjangan;
    private JTextField txtGapok;
    private JTextField txtPajak;
    private JTextField txtPotongan;
    private JTextField txtGajiBersih;
    private ButtonGroup bgJenkel;
    private ButtonGroup bgStatus;
    private JTable tbGaji;
    private DefaultTableModel model;
    private JPanel fieldsPanel;

    // Membuat jendela dan menyiapkan seluruh bagian form.
    private form() {
        window = new JFrame("FORM GAJI PEGAWAI");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        initButtonGroups();
        initGolongan();
        initEvents();
        tampilData();

        window.pack();
        window.setMinimumSize(new Dimension(950, 700));
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Membuat dan menata komponen-komponen form.
    private void initComponents() {
        fieldsPanel = new JPanel(new GridLayout(0, 2, 35, 14));
        JLabel titleLabel = new JLabel("Form Gaji Pegawai", JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 16f));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(titleLabel);

        fieldsPanel.add(new JLabel("NIP"));
        txtNip = new JTextField(20);
        fieldsPanel.add(txtNip);

        fieldsPanel.add(new JLabel("Nama Pegawai"));
        txtNama = new JTextField(20);
        fieldsPanel.add(txtNama);

        fieldsPanel.add(new JLabel("Jenis Kelamin"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        OpPria = new JRadioButton("Pria");
        OpWanita = new JRadioButton("Wanita");
        genderPanel.add(OpPria);
        genderPanel.add(OpWanita);
        fieldsPanel.add(genderPanel);

        fieldsPanel.add(new JLabel("Status"));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        OpMenikah = new JRadioButton("Menikah");
        OpBelumMenikah = new JRadioButton("Belum Menikah");
        statusPanel.add(OpMenikah);
        statusPanel.add(OpBelumMenikah);
        fieldsPanel.add(statusPanel);

        fieldsPanel.add(new JLabel("Jumlah Anak"));
        txtJumlahAnak = new JTextField(20);
        txtJumlahAnak.setEditable(false);
        fieldsPanel.add(txtJumlahAnak);

        fieldsPanel.add(new JLabel("Golongan"));
        cbGolongan = new JComboBox<>();
        fieldsPanel.add(cbGolongan);

        fieldsPanel.add(new JLabel("Tunjangan"));
        txtTunjangan = new JTextField(20);
        txtTunjangan.setEditable(false);
        fieldsPanel.add(txtTunjangan);

        fieldsPanel.add(new JLabel("Gaji Pokok"));
        txtGapok = new JTextField(20);
        fieldsPanel.add(txtGapok);

        fieldsPanel.add(new JLabel("Pajak (12%)"));
        txtPajak = new JTextField(20);
        txtPajak.setEditable(false);
        fieldsPanel.add(txtPajak);

        fieldsPanel.add(new JLabel("Potongan"));
        txtPotongan = new JTextField(20);
        fieldsPanel.add(txtPotongan);

        fieldsPanel.add(new JLabel("Gaji Bersih"));
        txtGajiBersih = new JTextField(20);
        txtGajiBersih.setEditable(false);
        fieldsPanel.add(txtGajiBersih);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        JButton btHitung = new JButton("Hitung");
        JButton btSimpan = new JButton("Simpan");
        JButton btUbah = new JButton("Ubah");
        JButton btHapus = new JButton("Hapus");
        JButton btReset = new JButton("Reset");
        JButton[] actionButtons = {btHitung, btSimpan, btUbah, btHapus, btReset};
        Dimension buttonSize = new Dimension(120, 38);
        for (int index = 0; index < actionButtons.length; index++) {
            JButton button = actionButtons[index];
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            buttonsPanel.add(button);
            if (index < actionButtons.length - 1) {
                buttonsPanel.add(javax.swing.Box.createVerticalStrut(16));
            }
        }

        btHitung.addActionListener(event -> hitungGaji());
        btReset.addActionListener(event -> resetForm());
        btSimpan.addActionListener(event -> simpanData());
        btUbah.addActionListener(event -> ubahData());
        btHapus.addActionListener(event -> hapusData());

        tbGaji = new JTable();
        tbGaji.setFillsViewportHeight(true);
        tbGaji.setRowHeight(24);
        tbGaji.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) { isiFormDariTabel(); }
        });
        JScrollPane tableScrollPane = new JScrollPane(tbGaji);
        tableScrollPane.setPreferredSize(new Dimension(780, 175));

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        fieldsPanel.setMaximumSize(new Dimension(520, fieldsPanel.getPreferredSize().height));
        fieldsPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        formContainer.add(fieldsPanel);

        JPanel formAndButtonsPanel = new JPanel(new BorderLayout(60, 0));
        formAndButtonsPanel.add(formContainer, BorderLayout.CENTER);
        formAndButtonsPanel.add(buttonsPanel, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        topPanel.add(formAndButtonsPanel);

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.add(tableScrollPane);

        JPanel contentPanel = new JPanel(new BorderLayout(16, 12));
        contentPanel.add(topPanel, BorderLayout.CENTER);
        contentPanel.add(tablePanel, BorderLayout.SOUTH);
        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        window.setContentPane(contentPanel);
    }

    // Mengelompokkan pilihan radio agar tiap bagian hanya memiliki satu pilihan.
    private void initButtonGroups() {
        bgJenkel = new ButtonGroup();
        bgJenkel.add(OpPria);
        bgJenkel.add(OpWanita);

        bgStatus = new ButtonGroup();
        bgStatus.add(OpMenikah);
        bgStatus.add(OpBelumMenikah);
    }

    // Mengisi pilihan golongan dan memastikan belum ada golongan yang dipilih.
    private void initGolongan() {
        cbGolongan.addItem("IIIa");
        cbGolongan.addItem("IIIb");
        cbGolongan.addItem("IIIc");
        cbGolongan.addItem("IIId");
        cbGolongan.setSelectedIndex(-1);
        txtNip.requestFocusInWindow();
    }

    // Menghubungkan pilihan status dan golongan dengan handler masing-masing.
    private void initEvents() {
        OpMenikah.addActionListener(event -> handleMenikah());
        OpBelumMenikah.addActionListener(event -> handleBelumMenikah());
        cbGolongan.addActionListener(event -> updateTunjangan());
        txtGapok.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                updatePajak();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                updatePajak();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                updatePajak();
            }
        });
    }

    // Mengaktifkan input jumlah anak saat status menikah dipilih.
    private void handleMenikah() {
        if (OpMenikah.isSelected()) {
            txtJumlahAnak.setEditable(true);
        }
    }

    // Menonaktifkan input jumlah anak saat status belum menikah dipilih.
    private void handleBelumMenikah() {
        if (OpBelumMenikah.isSelected()) {
            txtJumlahAnak.setEditable(false);
        }
    }

    // Menampilkan tunjangan sesuai golongan yang dipilih atau mengosongkannya.
    private void updateTunjangan() {
        long[] TunjanganByGolongan = {100000L, 200000L, 300000L, 400000L};
        int selectedIndex = cbGolongan.getSelectedIndex();
        if (selectedIndex < 0) {
            txtTunjangan.setText("");
            return;
        }

        txtTunjangan.setText(String.valueOf(TunjanganByGolongan[selectedIndex]));
    }

    // Menghitung pajak sebesar 12% dari gaji pokok.
    private void updatePajak() {
        String GapokText = txtGapok.getText().trim();
        if (GapokText.isEmpty()) {
            txtPajak.setText("");
            return;
        }

        try {
            Long Gapok = Long.parseLong(GapokText);
            Long Pajak = (Gapok * 12) / 100;
            txtPajak.setText(String.valueOf(Pajak));
        } catch (NumberFormatException exception) {
            txtPajak.setText("");
        }
    }

    // Menghitung gaji bersih setelah pajak dan potongan.
    private void hitungGaji() {
        if (cbGolongan.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(
                window,
                "Silakan pilih golongan terlebih dahulu.",
                "Golongan belum dipilih",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            Long JumlahAnak = OpMenikah.isSelected()
                ? Long.parseLong(txtJumlahAnak.getText().trim())
                : 0L;
            Long Tunjangan = Long.parseLong(txtTunjangan.getText());
            Long Gapok = Long.parseLong(txtGapok.getText().trim());
            Long Pajak = Long.parseLong(txtPajak.getText());
            Long Potongan = Long.parseLong(txtPotongan.getText().trim());
            Long Gaber = ((JumlahAnak * Tunjangan) + (Gapok - Pajak)) - Potongan;
            txtGajiBersih.setText(String.valueOf(Gaber));
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                window,
                "Masukkan jumlah anak (jika menikah), gaji pokok, dan potongan dalam angka yang valid.",
                "Input tidak valid",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Mengosongkan input, mengembalikan pilihan awal, dan mengatur fokus ke NIP.
    private void resetForm() {
        txtNip.setText("");
        // NIP dikunci saat memilih baris untuk menjaga kunci update.
        txtNip.setEditable(true);
        txtNama.setText("");
        bgJenkel.clearSelection();
        bgStatus.clearSelection();
        txtJumlahAnak.setText("");
        txtJumlahAnak.setEditable(false);
        cbGolongan.setSelectedIndex(-1);
        txtTunjangan.setText("");
        txtGapok.setText("");
        txtPajak.setText("");
        txtPotongan.setText("");
        txtGajiBersih.setText("");
        txtNip.requestFocusInWindow();
    }

    // READ: Memuat seluruh data gaji ke tabel.
    private void tampilData() {
        model = new DefaultTableModel(
            new Object[]{"NIP", "Nama", "Jenis Kelamin", "Status", "Jml Anak", "Golongan", "Tunjangan", "Gaji Pokok", "Pajak", "Potongan", "Gaji Bersih"},
            0
        );
        tbGaji.setModel(model);

        // === DATABASE CONNECTION START: memuat data gaji ===
        try {
            String sql = "SELECT nip, nama, jenis_kelamin, status, jumlah_anak, golongan, tunjangan, gaji_pokok, pajak, potongan, gaji_bersih FROM tb_gaji";
            try (Connection conn = Koneksi.configDB();
                 Statement statement = conn.createStatement();
                 ResultSet result = statement.executeQuery(sql)) {
                while (result.next()) {
                    model.addRow(new Object[]{
                        result.getString("nip"),
                        result.getString("nama"),
                        result.getString("jenis_kelamin"),
                        result.getString("status"),
                        result.getInt("jumlah_anak"),
                        result.getString("golongan"),
                        result.getLong("tunjangan"),
                        result.getLong("gaji_pokok"),
                        result.getLong("pajak"),
                        result.getLong("potongan"),
                        result.getLong("gaji_bersih")
                    });
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                window,
                "Gagal memuat data: " + exception.getMessage(),
                "Kesalahan Database",
                JOptionPane.ERROR_MESSAGE
            );
        }
        // === DATABASE CONNECTION END ===
    }

    // CREATE: Menyimpan data form ke database.
    private void simpanData() {
        if (txtNip.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()
                || (!OpPria.isSelected() && !OpWanita.isSelected())
                || (!OpMenikah.isSelected() && !OpBelumMenikah.isSelected())
                || cbGolongan.getSelectedIndex() < 0 || txtGajiBersih.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(window, "Lengkapi semua data dan klik Hitung terlebih dahulu!");
            return;
        }

        // === DATABASE CONNECTION START: menyimpan data gaji ===
        try {
            String sql = "INSERT INTO tb_gaji (nip, nama, jenis_kelamin, status, jumlah_anak, golongan, tunjangan, gaji_pokok, pajak, potongan, gaji_bersih) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = Koneksi.configDB();
                 PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, txtNip.getText().trim());
                statement.setString(2, txtNama.getText().trim());
                statement.setString(3, OpPria.isSelected() ? "Pria" : "Wanita");
                statement.setString(4, OpMenikah.isSelected() ? "Menikah" : "Belum Menikah");
                statement.setInt(5, OpMenikah.isSelected() ? Integer.parseInt(txtJumlahAnak.getText().trim()) : 0);
                statement.setString(6, cbGolongan.getSelectedItem().toString());
                statement.setLong(7, Long.parseLong(txtTunjangan.getText().trim()));
                statement.setLong(8, Long.parseLong(txtGapok.getText().trim()));
                statement.setLong(9, Long.parseLong(txtPajak.getText().trim()));
                statement.setLong(10, txtPotongan.getText().trim().isEmpty() ? 0 : Long.parseLong(txtPotongan.getText().trim()));
                statement.setLong(11, Long.parseLong(txtGajiBersih.getText().trim()));

                if (statement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(window, "Data berhasil disimpan!");
                    tampilData();
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(window, "Data tidak berhasil disimpan.", "Simpan Gagal", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(window, "Gagal menyimpan data: " + exception.getMessage(), "Simpan Gagal", JOptionPane.ERROR_MESSAGE);
        }
        // === DATABASE CONNECTION END ===
    }

    // UPDATE: Mengubah data berdasarkan NIP.
    private void ubahData() {
        if (txtNip.getText().trim().isEmpty() || txtGajiBersih.getText().trim().isEmpty()
                || cbGolongan.getSelectedIndex() < 0
                || (!OpPria.isSelected() && !OpWanita.isSelected())
                || (!OpMenikah.isSelected() && !OpBelumMenikah.isSelected())) {
            JOptionPane.showMessageDialog(window, "Pilih data dari tabel, lengkapi data, dan klik Hitung sebelum mengubah!");
            return;
        }

        // === DATABASE CONNECTION START: mengubah data gaji ===
        try {
            String sql = "UPDATE tb_gaji SET nama=?, jenis_kelamin=?, status=?, jumlah_anak=?, golongan=?, tunjangan=?, gaji_pokok=?, pajak=?, potongan=?, gaji_bersih=? WHERE nip=?";
            try (Connection conn = Koneksi.configDB();
                 PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, txtNama.getText().trim());
                statement.setString(2, OpPria.isSelected() ? "Pria" : "Wanita");
                statement.setString(3, OpMenikah.isSelected() ? "Menikah" : "Belum Menikah");
                statement.setInt(4, OpMenikah.isSelected() ? Integer.parseInt(txtJumlahAnak.getText().trim()) : 0);
                statement.setString(5, cbGolongan.getSelectedItem().toString());
                statement.setLong(6, Long.parseLong(txtTunjangan.getText().trim()));
                statement.setLong(7, Long.parseLong(txtGapok.getText().trim()));
                statement.setLong(8, Long.parseLong(txtPajak.getText().trim()));
                statement.setLong(9, txtPotongan.getText().trim().isEmpty() ? 0 : Long.parseLong(txtPotongan.getText().trim()));
                statement.setLong(10, Long.parseLong(txtGajiBersih.getText().trim()));
                statement.setString(11, txtNip.getText().trim());

                if (statement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(window, "Data berhasil diubah!");
                    tampilData();
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(window, "Data dengan NIP tersebut tidak ditemukan.", "Ubah Gagal", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(window, "Gagal mengubah data: " + exception.getMessage(), "Ubah Gagal", JOptionPane.ERROR_MESSAGE);
        }
        // === DATABASE CONNECTION END ===
    }

    // DELETE: Menghapus data setelah konfirmasi.
    private void hapusData() {
        if (txtNip.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(window, "Pilih data yang ingin dihapus dari tabel!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
            window,
            "Yakin ingin menghapus data NIP: " + txtNip.getText().trim() + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );
        if (konfirmasi != JOptionPane.YES_OPTION) {
            return;
        }

        // === DATABASE CONNECTION START: menghapus data gaji ===
        try {
            String sql = "DELETE FROM tb_gaji WHERE nip=?";
            try (Connection conn = Koneksi.configDB();
                 PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, txtNip.getText().trim());

                if (statement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(window, "Data berhasil dihapus!");
                    tampilData();
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(window, "Data dengan NIP tersebut tidak ditemukan.", "Hapus Gagal", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(window, "Gagal menghapus data: " + exception.getMessage(), "Hapus Gagal", JOptionPane.ERROR_MESSAGE);
        }
        // === DATABASE CONNECTION END ===
    }

    // Mengisi form dari baris tabel yang dipilih.
    private void isiFormDariTabel() {
        int baris = tbGaji.getSelectedRow();
        if (baris < 0) {
            return;
        }

        txtNip.setText(tbGaji.getValueAt(baris, 0).toString());
        txtNip.setEditable(false);
        txtNama.setText(tbGaji.getValueAt(baris, 1).toString());

        String jenisKelamin = tbGaji.getValueAt(baris, 2).toString();
        OpPria.setSelected("Pria".equalsIgnoreCase(jenisKelamin));
        OpWanita.setSelected("Wanita".equalsIgnoreCase(jenisKelamin));

        String status = tbGaji.getValueAt(baris, 3).toString();
        boolean menikah = "Menikah".equalsIgnoreCase(status);
        OpMenikah.setSelected(menikah);
        OpBelumMenikah.setSelected(!menikah);
        txtJumlahAnak.setEditable(menikah);
        txtJumlahAnak.setText(tbGaji.getValueAt(baris, 4).toString());
        cbGolongan.setSelectedItem(tbGaji.getValueAt(baris, 5).toString());
        txtTunjangan.setText(tbGaji.getValueAt(baris, 6).toString());
        txtGapok.setText(tbGaji.getValueAt(baris, 7).toString());
        txtPajak.setText(tbGaji.getValueAt(baris, 8).toString());
        txtPotongan.setText(tbGaji.getValueAt(baris, 9).toString());
        txtGajiBersih.setText(tbGaji.getValueAt(baris, 10).toString());
    }

    // Menjalankan form pada event dispatch thread Swing.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            form app = new form();
            app.window.setVisible(true);
        });
    }
}
