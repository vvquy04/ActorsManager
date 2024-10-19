package TH_Java;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClickListener extends MouseAdapter {
    private JTable table;
    private JTextField txtMaDV, txtHoTen, txtNamSinh, txtQuocTich;
    private JSpinner spPhimHanhDong, spPhimHai;
    private DienVienManager qldv;
    private JComboBox<String> cbLoaiDienVien, cbGioiTinh;

    public ClickListener(JTable table, JTextField txtMaDV, JTextField txtHoTen, JTextField txtNamSinh,
                         JComboBox<String> cbGioiTinh, JTextField txtQuocTich, JSpinner spPhimHanhDong, JSpinner spPhimHai, JComboBox<String> cbLoaiDienVien) {
        this.table = table;
        this.txtMaDV = txtMaDV;
        this.txtHoTen = txtHoTen;
        this.txtNamSinh = txtNamSinh;
        this.cbGioiTinh = cbGioiTinh;
        this.txtQuocTich = txtQuocTich;
        this.spPhimHanhDong = spPhimHanhDong;
        this.spPhimHai = spPhimHai;
        this.qldv = new DienVienManager();
        this.cbLoaiDienVien = cbLoaiDienVien;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            
            switch (buttonText) {
                case "Thêm":
                    themDienVien();
                    break;
                case "Xóa":
                    xoaDienVien();
                    break;
                case "Tìm Kiếm":
                    timKiemDienVien();
                    break;
                case "Tải lại":
                    taiLaiDuLieu();
                    break;
                case "Lưu file văn bản":
                    luuFileVanBan();
                    break;
                case "Mở file văn bản":
                    moFileVanBan();
                    break;
                case "Lưu file nhị phân": // Xử lý lưu file nhị phân
                    luuFileNhiPhan();
                    break;
                case "Mở file nhị phân": // Xử lý mở file nhị phân
                    moFileNhiPhan();
                    break;
            }
        } //else if (e.getSource() instanceof JMenuItem) {
//            JMenuItem menuItem = (JMenuItem) e.getSource();
//            String menuItemText = menuItem.getText();
//            
//            switch (menuItemText) {
//            case "Lưu file văn bản":
//                luuFileVanBan();
//                break;
//            case "Mở file văn bản":
//                moFileVanBan();
//                break;
//            case "Lưu file nhị phân": // Xử lý lưu file nhị phân
//                luuFileNhiPhan();
//                break;
//            case "Mở file nhị phân": // Xử lý mở file nhị phân
//                moFileNhiPhan();
//                break;
//        }
//        }
    }

    private void themDienVien() {
        String sql = "insert into DienVien (maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimH) values (";
//TH4
//        String maDV = txtMaDV.getText();
//        String hoTen = txtHoTen.getText();
//        String namSinh = txtNamSinh.getText();
//        String gioiTinh = (String) cbGioiTinh.getSelectedItem();
//        String quocTich = txtQuocTich.getText();
//        String loaiDienVien = (String) cbLoaiDienVien.getSelectedItem();
//        DienVien dv;
//        
//        if (maDV.isEmpty() || hoTen.isEmpty() || namSinh.isEmpty() || quocTich.isEmpty() || gioiTinh == null || loaiDienVien == null) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return; // Thoát nếu thông tin không hợp lệ
//        }
//        
//        if ("Diễn viên hành động".equals(loaiDienVien)) {
//            int soPhimHanhDong = (int) spPhimHanhDong.getValue();
//            dv = new DienVienHanhDong("dvhd" + maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimHanhDong);
//        } else {
//            int soPhimHai = (int) spPhimHai.getValue();
//            dv = new DienVienHai("dvh" + maDV, hoTen, namSinh, gioiTinh, quocTich, soPhimHai);
//        }
//
//        qldv.getDsDienVien().add(dv);
        capNhatBangDuLieu();
        JOptionPane.showMessageDialog(null, "Đã thêm diễn viên thành công!");
        xoaFormNhapLieu();
    }

    private void xoaDienVien() {
        int selectedRow = table.getSelectedRow(); 

        
        if (selectedRow != -1) {
            
            String maDV = (String) table.getValueAt(selectedRow, 0);

            
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa diễn viên mã: " + maDV + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                
                qldv.getDsDienVien().removeIf(dv -> dv.getMaDV().equals(maDV));
                capNhatBangDuLieu(); 
                JOptionPane.showMessageDialog(null, "Đã xóa diễn viên thành công!");
            }
        } else {
            
            String maDV = JOptionPane.showInputDialog(null, "Nhập mã diễn viên cần xóa:");

            if (maDV != null && !maDV.isEmpty()) {
               
                boolean found = qldv.getDsDienVien().removeIf(dv -> dv.getMaDV().equals(maDV));

                // Thông báo kết quả
                if (found) {
                    JOptionPane.showMessageDialog(null, "Đã xóa diễn viên có mã: " + maDV);
                    capNhatBangDuLieu(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy diễn viên có mã: " + maDV);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã diễn viên để xóa.");
            }
        }
    }


    private void timKiemDienVien() {
        
        String hoTen = JOptionPane.showInputDialog(null, "Nhập họ tên diễn viên cần tìm:");

        
        if (hoTen != null && !hoTen.isEmpty()) {
            
            boolean check = false;

            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            
            for (DienVien dv : qldv.getDsDienVien()) {
                
                if (dv.getHoTen().equalsIgnoreCase(hoTen)) {
                    
                    if (dv instanceof DienVienHanhDong dvhd) {
                        model.addRow(new Object[]{dvhd.getMaDV(), dvhd.getHoTen(), dvhd.getNamSinh(), dvhd.getGioiTinh(),
                                dvhd.getQuocTich(), dvhd.getSoPhimHanhDongThamGia(), "-"});
                    } else if (dv instanceof DienVienHai dvh) {
                        model.addRow(new Object[]{dvh.getMaDV(), dvh.getHoTen(), dvh.getNamSinh(), dvh.getGioiTinh(),
                                dvh.getQuocTich(), "-", dvh.getSoPhimHaiThamGia()});
                    }
                    check = true; 
                }
            }

            
            if (!check) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy diễn viên tên: " + hoTen);
            } else {
                JOptionPane.showMessageDialog(null, "Đã tìm thấy diễn viên!");
            }
        } else {
            
            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên diễn viên cần tìm.");
        }
    }


    private void taiLaiDuLieu() {
        capNhatBangDuLieu();
        xoaFormNhapLieu();
    }
    private void luuFileVanBan() {
        try {
            qldv.luuVaoFileVanBan(qldv.getDsDienVien()); 
            JOptionPane.showMessageDialog(null, "Lưu file văn bản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file văn bản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moFileVanBan() {
        try {
            qldv.setDsDienVien(qldv.taiTuFileVanBan()); 
            capNhatBangDuLieu();
            JOptionPane.showMessageDialog(null, "Mở file văn bản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở file văn bản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void luuFileNhiPhan() {
        try {
            qldv.luuVaoFileNhiPhan(qldv.getDsDienVien()); // Gọi phương thức lưu
            JOptionPane.showMessageDialog(null, "Lưu file nhị phân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file nhị phân: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moFileNhiPhan() {
        try {
            qldv.setDsDienVien(qldv.taiTuFileNhiPhan()); // Cập nhật danh sách diễn viên
            qldv.hienThi();
            capNhatBangDuLieu(); // Cập nhật lại bảng
            JOptionPane.showMessageDialog(null, "Mở file nhị phân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở file nhị phân: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void capNhatBangDuLieu() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 
        for (DienVien dv : qldv.getDsDienVien()) {
            model.addRow(new Object[]{
                dv.getMaDV(), dv.getHoTen(), dv.getNamSinh(), dv.getGioiTinh(), dv.getQuocTich(),
                dv instanceof DienVienHanhDong ? ((DienVienHanhDong) dv).getSoPhimHanhDongThamGia() : "",
                dv instanceof DienVienHai ? ((DienVienHai) dv).getSoPhimHaiThamGia() : ""
            });
        }
    }

    private void xoaFormNhapLieu() {
        txtMaDV.setText("");
        txtHoTen.setText("");
        txtNamSinh.setText("");
        cbGioiTinh.setSelectedIndex(0);
        txtQuocTich.setText("");
        spPhimHanhDong.setValue(0);
        spPhimHai.setValue(0);
        cbLoaiDienVien.setSelectedIndex(0);
        spPhimHanhDong.setEnabled(true);
        spPhimHai.setEnabled(false);
    }
}