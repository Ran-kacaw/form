import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

    // Membuat jendela dan menyiapkan seluruh bagian form.
    private form() {
        window = new JFrame("FORM GAJI PEGAWAI");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        initButtonGroups();
        initGolongan();
        initEvents();

        window.pack();
        window.setLocationRelativeTo(null);
    }

    // Membuat dan menata komponen-komponen form.
    private void initComponents() {
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));

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

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btHitung = new JButton("Hitung");
        JButton btReset = new JButton("Reset");
        buttonsPanel.add(btHitung);
        buttonsPanel.add(btReset);

        btHitung.addActionListener(event -> hitungGaji());
        btReset.addActionListener(event -> resetForm());

        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.add(fieldsPanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.EAST);
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

    // Menjalankan form pada event dispatch thread Swing.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            form app = new form();
            app.window.setVisible(true);
        });
    }
}
