package TH_Java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        DienVienManager qldv = new DienVienManager();
        ActionFrame fDienVien = new ActionFrame("Quản lý diễn viên");
        fDienVien.setVisible(true);
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            qldv.menu();
            System.out.println("Nhap lua chon cua ban: ");
            chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1 -> qldv.themMoiDV();
                case 2 -> qldv.hienThi();
                case 3 -> qldv.xoaDV();
                case 4 -> qldv.timKiemDV();
                case 5 -> qldv.luuVaoFileVanBan(qldv.getDsDienVien());
                case 6 -> qldv.taiTuFileVanBan();
                case 7 -> qldv.luuVaoFileNhiPhan(qldv.getDsDienVien());
                case 8 -> qldv.taiTuFileNhiPhan();
                case 0 -> {
                }
                default -> System.out.println("Chuc nang khong hop le. Vui long nhap lai!");
            }
        } while (chon != 0);
    }
}
