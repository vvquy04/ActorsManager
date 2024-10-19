
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_Java;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DienVienHanhDong extends DienVien implements IDienVien, Serializable {
    private int soPhimHanhDongThamGia;
    
    public DienVienHanhDong(){
        super();
        this.soPhimHanhDongThamGia = 0;
    }
    public DienVienHanhDong(String maDV, String hoTen, String namSinh, String gioiTinh, String quocTich, int soPhimHanhDong){
        super(maDV, hoTen, namSinh, gioiTinh, quocTich);
        this.soPhimHanhDongThamGia = soPhimHanhDong;
    }
    
    public void setSoPhimHanhDongThamGia(int soPhimHD){
        this.soPhimHanhDongThamGia = soPhimHD;
    }
    
    public int getSoPhimHanhDongThamGia(){
        return this.soPhimHanhDongThamGia;
    }
    
  
    @Override
    public void NhapThongTin() throws Exception{
        Scanner sc = new Scanner(System.in);
        super.NhapThongTin();
        while(true){
            try{
                System.out.println("Nhap so phim hanh dong tham gia: ");
                this.soPhimHanhDongThamGia = sc.nextInt();
                break;
            }catch(Exception e){
                System.out.println(e.getMessage() + ". Vui long nhap lai!");
                sc.nextLine();
            }
        } 
        
        
    }
    @Override
    public void HienThiThongTin(){
        super.HienThiThongTin();
        System.out.println("So phim hanh dong tham gia: " + this.soPhimHanhDongThamGia);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", soPhimHanhDongThamGia='" + soPhimHanhDongThamGia + '\'';
    }

}
