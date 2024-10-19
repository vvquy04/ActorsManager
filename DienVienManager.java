package TH_Java;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DienVienManager {

    private ArrayList<DienVien> dsDienVien;

    public DienVienManager() {
        this.dsDienVien = new ArrayList<>();
    }

    public void menu() {
        System.out.println();
        System.out.println("\t\t\tQuan ly dien vien");
        System.out.println("1.Them moi dien vien");
        System.out.println("2.Hien thi danh sach thong tin dien vien");
        System.out.println("3.Xoa thong tin dien vien");
        System.out.println("4.Tim kiem dien vien");
        System.out.println("5. Luu vao tep van ban");
        System.out.println("6. Tai tu tep van ban");
        System.out.println("7. Luu vao tep nhi phan");
        System.out.println("8. Tai tu tep nhi phan");
        System.out.println("0.Thoat");
    }

//    
    

    public static void luuVaoFileVanBan(ArrayList<DienVien> dsDienVien) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\TH_Java\\src\\TH_Java\\dsDienVien.txt"))) {
            for (DienVien dv : dsDienVien) {
                bw.write(dv.getMaDV() + " - " + dv.getHoTen() + " - " + dv.getNamSinh() + " - " + dv.getGioiTinh() + " - " + dv.getQuocTich());
                if (dv instanceof DienVienHanhDong dvhd) {
                    bw.write(" - " + dvhd.getSoPhimHanhDongThamGia());
                } else if (dv instanceof DienVienHai dvh) {
                    bw.write(" - " + dvh.getSoPhimHaiThamGia());
                }
                bw.newLine();
            }
        }
    }

    public ArrayList<DienVien> taiTuFileVanBan() throws IOException {
        ArrayList<DienVien> dsDienVien = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\TH_Java\\src\\TH_Java\\dsDienVien.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" - ");
                DienVien dv = null;
                if (data[0].startsWith("dvhd")) { // DienVienHanhDong
                    dv = new DienVienHanhDong(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
                } else if (data[0].startsWith("dvh")) { // DienVienHai
                    dv = new DienVienHai(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
                }
                if (dv != null) {
                    dsDienVien.add(dv);
                }
            }
        } 
        return dsDienVien;
    }

    public void luuVaoFileNhiPhan(ArrayList<DienVien> dsDienVien) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\TH_Java\\src\\TH_Java\\dsDienVienNP.dat"))) {
            oos.writeObject(dsDienVien);
        }
    }
    
    public ArrayList<DienVien> taiTuFileNhiPhan() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\TH_Java\\src\\TH_Java\\dsDienVienNP.dat"))) {
            return (ArrayList<DienVien>) ois.readObject();
        }
    }

    public void themMoiDV() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBan muon nhap thong tin dien vien nao?\n1.Dien vien hanh dong\n2.Dien vien hai\n");
        int chon = sc.nextInt();
        sc.nextLine();
        if (chon == 1) {
            try {
                DienVien dvhd = new DienVienHanhDong();
                dvhd.NhapThongTin();
                dsDienVien.add(dvhd);
                System.out.println("Them dien vien thanh cong!");
            } catch (Exception e) {
                System.out.println(e.getMessage() + ". Yeu cau nhap lai!");
            }

        } else if (chon == 2) {
            try {
                DienVien dvh = new DienVienHai();
                dvh.NhapThongTin();
                dsDienVien.add(dvh);
                System.out.println("Them dien vien thanh cong!");
            } catch (Exception e) {
                System.out.println(e.getMessage() + ". Yeu cau nhap lai!");
            }
        }
    }

    public void hienThi() {
        System.out.println("\t\t\tHien thi thong tin dien vien");
        if (dsDienVien.isEmpty()) {
            System.out.println("Khong co dien vien nao trong danh sach!");
        } else {
            for (int i = 0; i < dsDienVien.size(); i++) {
                System.out.println("\nThong tin dien vien thu " + (i + 1));
                dsDienVien.get(i).HienThiThongTin();
            }
        }
    }

    public void xoaDV() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nNhap ho ten dien vien ban muon xoa: ");
        String name = sc.nextLine();
        boolean check = false;
        for (DienVien dv : dsDienVien) {
            if (dv.getHoTen().equals(name)) {
                dsDienVien.remove(dv);
                check = true;
                System.out.println("Xoa thanh cong dien vien!");
                break;
            }
        }
        if (!check) {
            System.out.println("Khong tim thay dien vien ten " + name + "!");
        }
    }

    public void timKiemDV() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nNhap ho ten dien vien ban muon tim kiem: ");
        String name = sc.nextLine();
        boolean check = false;
        for (DienVien dv : dsDienVien) {
            if (dv.getHoTen().equals(name)) {
                System.out.println("Tim thay dien vien ten " + name + "\nThong tin dien vien " + name + "!");
                dv.HienThiThongTin();
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Khong tim thay dien vien ten " + name + "!");
        }
    }
    
    public ArrayList<DienVien> getDsDienVien() {
        return dsDienVien;
    }

    void setDsDienVien(ArrayList<DienVien> taiTuFileVanBan) {
        this.dsDienVien = taiTuFileVanBan;
    }
}
