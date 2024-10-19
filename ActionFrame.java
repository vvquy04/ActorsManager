package TH_Java;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jshell.spi.ExecutionControl;

public class ActionFrame extends JFrame {
    
    
    
    public ActionFrame(String title) {
        super(title);
        initGui();
    }
    
    private void initGui() {
        this.setSize(800, 450);  // Điều chỉnh kích thước cửa sổ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Thêm MenuBar với các mục "Lưu file" và "Mở file"
//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem saveMenuItem = new JMenuItem("Lưu file văn bản");
//        JMenuItem openMenuItem = new JMenuItem("Mở file văn bản");
//        JMenuItem saveBinaryMenuItem = new JMenuItem("Lưu file nhị phân");
//        JMenuItem openBinaryMenuItem = new JMenuItem("Mở file nhị phân");
//        
//        fileMenu.add(saveMenuItem);
//        fileMenu.add(saveBinaryMenuItem);
//        fileMenu.add(openMenuItem);
//        fileMenu.add(openBinaryMenuItem);
//        menuBar.add(fileMenu);
//        this.setJMenuBar(menuBar);
        
        JPanel pnBorder = new JPanel(new BorderLayout());

        // Vùng NORTH (Tiêu đề)
        JPanel pnNorth = new JPanel();
        Font fTitle = new Font("Tahoma", Font.BOLD, 16);
        JLabel lblTitle = new JLabel("QUẢN LÝ DIỄN VIÊN");  
        lblTitle.setFont(fTitle);
        pnNorth.add(lblTitle);
        pnBorder.add(pnNorth, BorderLayout.NORTH);
        
        JPanel pnCenter = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Mã DV", "Họ Tên", "Năm Sinh", "Giới Tính", "Quốc Tịch", "Số Phim Hành Động Tham Gia", "Số Phim Hài Tham Gia"};
        // Khởi tạo mô hình bảng
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Tạo JTable với mô hình
        JTable tbDienVien = new JTable(model);
        tbDienVien.setPreferredScrollableViewportSize(new Dimension(500, 150)); // Kích thước bảng
        tbDienVien.setFillsViewportHeight(true);
        JScrollPane spTBDV = new JScrollPane(tbDienVien);
        
        Border borderTable = BorderFactory.createLineBorder(Color.GRAY, 1);
        TitledBorder titleBorderTable = new TitledBorder(borderTable, "DANH SÁCH DIỄN VIÊN");
        titleBorderTable.setTitleJustification(TitledBorder.CENTER);
        spTBDV.setBorder(titleBorderTable);
        pnCenter.add(spTBDV, BorderLayout.CENTER);
        
        
        JPanel pnInput = new JPanel(new GridLayout(4, 4, 10, 10)); 
        
        Border borderInput = BorderFactory.createLineBorder(Color.GRAY, 1);
        TitledBorder titleBorderInput = new TitledBorder(borderInput, "NHẬP THÔNG TIN DIỄN VIÊN");
        titleBorderInput.setTitleJustification(TitledBorder.CENTER);
        
        pnInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblLoaiDienVien = new JLabel("Loại Diễn Viên: ");
        JComboBox<String> cbLoaiDienVien = new JComboBox<>(new String[]{"Diễn viên hành động", "Diễn viên hài"});
        pnInput.add(lblLoaiDienVien);
        pnInput.add(cbLoaiDienVien);
        
        
        JLabel lblMaDV = new JLabel("Mã DV: ");
        JTextField txtMaDV = new JTextField(10);
        pnInput.add(lblMaDV);
        pnInput.add(txtMaDV);
        
        
        JLabel lblHoTen = new JLabel("Họ Tên: ");
        JTextField txtHoTen= new JTextField(10);
        pnInput.add(lblHoTen);
        pnInput.add(txtHoTen);
        
        
        JLabel lblNamSinh = new JLabel("Năm Sinh: ");
        JTextField txtNamSinh= new JTextField(10);
        pnInput.add(lblNamSinh);
        pnInput.add(txtNamSinh);
        
        
        JLabel lblGioiTinh = new JLabel("Giới Tính: ");
        JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        pnInput.add(lblGioiTinh);
        pnInput.add(cbGioiTinh);
        
        
        JLabel lblQuocTich = new JLabel("Quốc Tịch: ");
        JTextField txtQuocTich = new JTextField(10);
        pnInput.add(lblQuocTich);
        pnInput.add(txtQuocTich);
        
        // Đầu vào số phim hành động tham gia
        JSpinner spPhimHanhDong = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Min 0, Max 100, Bước 1
        JLabel lblSPHDTG = new JLabel("Số Phim Hành Động Tham Gia: ");
        spPhimHanhDong.setPreferredSize(new Dimension(100, 30));
        pnInput.add(lblSPHDTG);
        pnInput.add(spPhimHanhDong);

        // Đầu vào số phim hài tham gia
        JSpinner spPhimHai = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Min 0, Max 100, Bước 1
        JLabel lblSPHTG = new JLabel("Số Phim Hài Tham Gia: ");
        spPhimHai.setPreferredSize(new Dimension(100, 30));
        pnInput.add(lblSPHTG);
        pnInput.add(spPhimHai);
        
        spPhimHai.setEnabled(false);
        
        // Xử lý sự kiện khi chọn loại diễn viên
        cbLoaiDienVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) cbLoaiDienVien.getSelectedItem();
                if ("Diễn viên hành động".equals(selectedType)) {
                        spPhimHai.setEnabled(false);
                        spPhimHanhDong.setEnabled(true);
                } else {
                    spPhimHanhDong.setEnabled(false);
                    spPhimHai.setEnabled(true);
                }
            }
        });
        
        pnCenter.add(pnInput, BorderLayout.SOUTH);
        
        pnBorder.add(pnCenter, BorderLayout.CENTER);
        
        // Vùng SOUTH (Các nút hành động)
        JPanel pnSouth = new JPanel(new GridLayout(2, 8, 10, 10));
        pnSouth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton btnAdd= new JButton("Thêm");
        pnSouth.add(btnAdd);
        JButton btnRemove = new JButton("Xóa");
        pnSouth.add(btnRemove);
        JButton btnUpdate = new JButton("Sửa");
        pnSouth.add(btnUpdate);
        JButton btnRefresh = new JButton("Tải lại");
        pnSouth.add(btnRefresh);
        
        JButton btnSave= new JButton("Lưu file văn bản");
        pnSouth.add(btnSave);
        JButton btnLoad = new JButton("Mở file văn bản");
        pnSouth.add(btnLoad);
        JButton btnSaveBinary= new JButton("Lưu file nhị phân");
        pnSouth.add(btnSaveBinary);
        JButton btnLoadBinary = new JButton("Mở file nhị phân");
        pnSouth.add(btnLoadBinary);
        pnBorder.add(pnSouth, BorderLayout.SOUTH);
        
        // Gắn sự kiện chuột cho các nút
        ClickListener clickListener = new ClickListener(tbDienVien, txtMaDV, txtHoTen, txtNamSinh,
                                        cbGioiTinh, txtQuocTich, spPhimHanhDong, spPhimHai, cbLoaiDienVien);
//        btnAdd.addMouseListener(clickListener);
//        btnRemove.addMouseListener(clickListener);
//        btnSearch.addMouseListener(clickListener);
//        btnRefresh.addMouseListener(clickListener);
        
        btnSave.addMouseListener(clickListener);
        btnLoad.addMouseListener(clickListener);
        btnSaveBinary.addMouseListener(clickListener);
        btnLoadBinary.addMouseListener(clickListener);


        tbDienVien.getSelectionModel().addListSelectionListener(e -> {
            // Kiểm tra xem có dòng nào được chọn không
            int selectedRow = tbDienVien.getSelectedRow();
            if (selectedRow != -1) { // Nếu có dòng được chọn
                // Lấy dữ liệu từ dòng được chọn
                String madv = (String) model.getValueAt(selectedRow, 0);
                String hoten = (String) model.getValueAt(selectedRow, 1);
                String namsinh = (String) model.getValueAt(selectedRow, 2);
                String gioitinh = (String) model.getValueAt(selectedRow, 3);
                String quoctich = (String) model.getValueAt(selectedRow, 4);
                Integer sophimhd = model.getValueAt(selectedRow, 5) != "" ? (Integer) model.getValueAt(selectedRow, 5) : null;
                Integer sophimht = model.getValueAt(selectedRow, 6) != "" ? (Integer) model.getValueAt(selectedRow, 6) : null;

                // Hiển thị dữ liệu lên các trường nhập
                txtMaDV.setText(madv);
                txtHoTen.setText(hoten);
                txtNamSinh.setText(namsinh);
                cbGioiTinh.setSelectedItem(gioitinh);
                txtQuocTich.setText(quoctich);

                // Kiểm tra loại diễn viên và hiển thị đúng dữ liệu phim hành động hoặc phim hài
                if (sophimhd != null) {
                    cbLoaiDienVien.setSelectedItem("Diễn viên hành động");
                    spPhimHanhDong.setValue(sophimhd);
                    spPhimHai.setValue(0);
                    spPhimHanhDong.setEnabled(true);
                    spPhimHai.setEnabled(false);
                } else if (sophimht != null) {
                    cbLoaiDienVien.setSelectedItem("Diễn viên hài");
                    spPhimHai.setValue(sophimht);
                    spPhimHanhDong.setValue(0);
                    spPhimHanhDong.setEnabled(false);
                    spPhimHai.setEnabled(true);
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String madv = txtMaDV.getText();
                String hoten = txtHoTen.getText();
                String namsinh = txtNamSinh.getText();
                String gioitinh = (String) cbGioiTinh.getSelectedItem();
                String quoctich = txtQuocTich.getText();
                int sophimhd = (int) spPhimHanhDong.getValue();
                int sophimhai = (int) spPhimHai.getValue();
                String loaiDV = (String) cbLoaiDienVien.getSelectedItem();

                // Kiểm tra xem dữ liệu nhập có hợp lệ không (có thể bổ sung thêm kiểm tra khác)
                if (madv.isEmpty() || hoten.isEmpty() || namsinh.isEmpty() || quoctich.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Kết nối đến cơ sở dữ liệu và thực hiện thêm dữ liệu
                Connection conn = ConnectDB.getConnection();
                try {
                    String query = "";
                    if ("Diễn viên hành động".equals(loaiDV)) {
                        // Thêm vào bảng diễn viên hành động
                        query = "INSERT INTO dienvien.dvhd (maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimHDTG) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, madv);
                        ps.setString(2, hoten);
                        ps.setString(3, namsinh);
                        ps.setString(4, gioitinh);
                        ps.setString(5, quoctich);
                        ps.setInt(6, sophimhd);
                        ps.executeUpdate();
                        model.addRow(new Object[] {madv, hoten, namsinh, gioitinh, quoctich, sophimhd, null});
                    } else if ("Diễn viên hài".equals(loaiDV)) {
                        // Thêm vào bảng diễn viên hài
                        query = "INSERT INTO dienvien.dvh (maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimHTG) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, madv);
                        ps.setString(2, hoten);
                        ps.setString(3, namsinh);
                        ps.setString(4, gioitinh);
                        ps.setString(5, quoctich);
                        ps.setInt(6, sophimhai);
                        ps.executeUpdate();
                        model.addRow(new Object[] {madv, hoten, namsinh, gioitinh, quoctich, null, sophimhai});
                                
                    }
                    
                    
                    // Thông báo thêm thành công
                    JOptionPane.showMessageDialog(null, "Thêm diễn viên thành công!");
                    
                    

                    // Sau khi thêm thành công, làm trống các trường nhập liệu
                    txtMaDV.setText("");
                    txtHoTen.setText("");
                    txtNamSinh.setText("");
                    txtQuocTich.setText("");
                    spPhimHanhDong.setValue(0);
                    spPhimHai.setValue(0);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Thêm diễn viên thất bại!");
                }
            }
        });
        
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem có dòng nào được chọn không
                int selectedRow = tbDienVien.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
                    return;
                }

                // Lấy mã diễn viên từ dòng được chọn
                String maDV = (String) model.getValueAt(selectedRow, 0);

                // Xác nhận trước khi xóa
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa diễn viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Xóa dữ liệu khỏi cơ sở dữ liệu
                    Connection conn = ConnectDB.getConnection();
                    try {
                        String deleteQueryHanhDong = "DELETE FROM dienvien.dvhd WHERE maDV = ?";
                        String deleteQueryHai = "DELETE FROM dienvien.dvh WHERE maDV = ?";

                        PreparedStatement pstmtHanhDong = conn.prepareStatement(deleteQueryHanhDong);
                        pstmtHanhDong.setString(1, maDV);
                        int rowsAffectedHanhDong = pstmtHanhDong.executeUpdate();

                        PreparedStatement pstmtHai = conn.prepareStatement(deleteQueryHai);
                        pstmtHai.setString(1, maDV);
                        int rowsAffectedHai = pstmtHai.executeUpdate();

                        // Nếu bản ghi được xóa thành công trong bất kỳ bảng nào
                        if (rowsAffectedHanhDong > 0 || rowsAffectedHai > 0) {
                            // Xóa dòng khỏi bảng JTable
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Xóa thành công.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa thất bại.");
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem có dòng nào được chọn không
                int selectedRow = tbDienVien.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.");
                    return;
                }

                // Lấy dữ liệu từ các trường nhập liệu
                String madv = txtMaDV.getText();
                String hoten = txtHoTen.getText();
                String namsinh = txtNamSinh.getText();
                String gioitinh = (String) cbGioiTinh.getSelectedItem();
                String quoctich = txtQuocTich.getText();
                int sophimhd = (int) spPhimHanhDong.getValue();
                int sophimhai = (int) spPhimHai.getValue();
                String loaiDV = (String) cbLoaiDienVien.getSelectedItem();

                // Kiểm tra xem dữ liệu nhập có hợp lệ không
                if (madv.isEmpty() || hoten.isEmpty() || namsinh.isEmpty() || quoctich.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Cập nhật cơ sở dữ liệu
                Connection conn = ConnectDB.getConnection();
                try {
                    String query = "";
                    if ("Diễn viên hành động".equals(loaiDV)) {
                        // Cập nhật diễn viên hành động
                        query = "UPDATE dienvien.dvhd SET hoTen = ?, namSinh = ?, gioiTinh = ?, quocTich = ?, soPhimHDTG = ? WHERE maDV = ?";
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, hoten);
                        ps.setString(2, namsinh);
                        ps.setString(3, gioitinh);
                        ps.setString(4, quoctich);
                        ps.setInt(5, sophimhd);
                        ps.setString(6, madv);
                        ps.executeUpdate();
                    } else if ("Diễn viên hài".equals(loaiDV)) {
                        // Cập nhật diễn viên hài
                        query = "UPDATE dienvien.dvh SET hoTen = ?, namSinh = ?, gioiTinh = ?, quocTich = ?, soPhimHTG = ? WHERE maDV = ?";
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, hoten);
                        ps.setString(2, namsinh);
                        ps.setString(3, gioitinh);
                        ps.setString(4, quoctich);
                        ps.setInt(5, sophimhai);
                        ps.setString(6, madv);
                        ps.executeUpdate();
                    }

                    // Thông báo cập nhật thành công
                    JOptionPane.showMessageDialog(null, "Sửa diễn viên thành công!");

                    // Cập nhật lại dữ liệu trong bảng
                    model.setValueAt(hoten, selectedRow, 1);
                    model.setValueAt(namsinh, selectedRow, 2);
                    model.setValueAt(gioitinh, selectedRow, 3);
                    model.setValueAt(quoctich, selectedRow, 4);
                    if ("Diễn viên hành động".equals(loaiDV)) {
                        model.setValueAt(sophimhd, selectedRow, 5);
                        model.setValueAt("", selectedRow, 6);
                    } else if ("Diễn viên hài".equals(loaiDV)) {
                        model.setValueAt("", selectedRow, 5);
                        model.setValueAt(sophimhai, selectedRow, 6);
                    }
                    
                    txtMaDV.setText("");
                    txtHoTen.setText("");
                    txtNamSinh.setText("");
                    txtQuocTich.setText("");
                    spPhimHanhDong.setValue(0);
                    spPhimHai.setValue(0);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Sửa diễn viên thất bại!");
                }
            }
        });




        
        
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ArrayList<DienVien> actors = new ArrayList<>();
                Connection conn = ConnectDB.getConnection();
                model.setRowCount(0);
                try {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimHTG, NULL AS soPhimHDTG\n" +
                    "FROM dienvien.dvh\n" +
                    "UNION ALL\n" +
                    "SELECT maDV, hoTen, namSinh, gioiTinh, quocTich, NULL AS soPhimHTG, soPhimHDTG\n" +
                    "FROM dienvien.dvhd";
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        String madv = rs.getString("maDV");
                        String hoten = rs.getString("hoTen");
                        String namsinh = rs.getString("namSinh");
                        String gioitinh = rs.getString("gioiTinh");
                        String quoctich = rs.getString("quocTich");

                        Integer sophimhd = rs.getString("soPhimHDTG") != null ? rs.getInt("soPhimHDTG") : null;
                        Integer sophimht = rs.getString("soPhimHTG") != null ? rs.getInt("soPhimHTG") : null;

//                        // Thêm điều kiện để quyết định giá trị
//                        if (sophimhd != null) {
//                            actors.add(new DienVienHanhDong(madv, hoten, namsinh, gioitinh, quoctich, sophimhd));
//                        } else if (sophimht != null) {
//                            actors.add(new DienVienHai(madv, hoten, namsinh, gioitinh, quoctich, sophimht));
//                        }
//                        
                        model.addRow(new Object[]{madv, hoten, namsinh, gioitinh, quoctich,
                        sophimhd != null ? sophimhd : "",
                        sophimht != null ? sophimht : ""
                });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
            }
        });
        
        
        this.add(pnBorder);
        
    }
    
    
    
}
