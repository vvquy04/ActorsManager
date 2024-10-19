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
public class DienVienHai extends DienVien implements IDienVien, Serializable{
    private int soPhimHaiThamGia;
    
    public DienVienHai(){
        super();
        this.soPhimHaiThamGia = 0;
    }
    public DienVienHai(String maDV, String hoTen, String namSinh, String quocTich, String gioiTinh, int soPhimHai){
        super(maDV, hoTen, namSinh, quocTich, gioiTinh);
        this.soPhimHaiThamGia = soPhimHai;
    }
    
    public void setSoPhimHaiThamGia(int soPhimHai){
        this.soPhimHaiThamGia = soPhimHai;
    }
    
    public int getSoPhimHaiThamGia(){
        return this.soPhimHaiThamGia;
    }
    
    @Override
    public void NhapThongTin() throws Exception{
        Scanner sc = new Scanner(System.in);
        super.NhapThongTin();
        while(true){
            try{
                System.out.println("Nhap so phim hai tham gia: ");
                this.soPhimHaiThamGia = sc.nextInt();
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
        System.out.println("So phim hai tham gia: " + this.soPhimHaiThamGia);
    }

    @Override
    public String toString() {
        return super.toString() + ", soPhimHaiThamGia='" + soPhimHaiThamGia + '\''; 
        
    }
    
}
