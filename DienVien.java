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
public class DienVien implements IDienVien, Serializable{
    private String maDV;
    private String hoTen;
    private String namSinh;
    private String gioiTinh;
    private String quocTich;
    
    public DienVien(){
        this.maDV = "";
        this.hoTen = "";
        this.gioiTinh = "";
        this.namSinh = "";
        this.quocTich = "";
    }
    
    public DienVien(String maDV, String hoTen, String namSinh, String quocTich, String gioiTinh){
        this.maDV = maDV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.quocTich = quocTich;
    }
    
    public void setMaDV(String maDV){
        this.maDV = maDV;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh = gioiTinh;
    }
    public void setQuocTich(String quocTich){
        this.quocTich = quocTich;
    }
    
    public String getMaDV(){
        return this.maDV;
    }
    public String getHoTen(){
        return this.hoTen;
    }
    public String getNamSinh(){
        return this.namSinh;
    }
    public String getGioiTinh(){
        return this.gioiTinh;
    }
    public String getQuocTich(){
        return this.quocTich;
    }
    
    @Override
    public void NhapThongTin() throws Exception{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Nhap ma dien vien ");
        this.maDV= sc.nextLine();
        if(this.maDV.isEmpty()){
            throw new Exception("Khong duoc de trong ma dien vien");
        }
        System.out.println("Nhap ho ten: ");
        this.hoTen = sc.nextLine();
        if(this.hoTen.isEmpty()){
            throw new Exception("Khong duoc de trong ten");
        }
        System.out.println("Nhap nam sinh:");
        this.namSinh = sc.nextLine();
        if(this.namSinh.isEmpty()){
            throw new Exception("Khong duoc de trong nam sinh");
        }
        System.out.println("Nhap gioi tinh(Nam/Nu): ");
        this.gioiTinh = sc.next();sc.nextLine();
        if(this.gioiTinh.isEmpty()){
            throw new Exception("Khong duoc de trong gioi tinh");
        }
        System.out.println("Nhap quoc tich: ");
        this.quocTich = sc.nextLine();
        if(this.quocTich.isEmpty()){
            throw new Exception("Khong duoc de trong quoc tich");
        }
    }
    
    @Override
    public void HienThiThongTin(){
        System.out.print("Ma dien vien: " + this.maDV + " - ");
        System.out.print("Ho va ten: " + this.hoTen + " - ");
        System.out.print("Nam sinh: " + this.namSinh + " - ");
        System.out.print("Gioi tinh: " + this.gioiTinh + " - ");
        System.out.print("Quoc tich: " + this.quocTich + " - ");
    }
    
    @Override
    public String toString() {
        return
            "maDV='" + maDV + '\'' +
            ", hoTen='" + hoTen + '\'' +
            ", namSinh='" + namSinh + '\'' +
            ", gioiTinh='" + gioiTinh + '\'' +
            ", quocTich='" + quocTich + '\''
            ;
    }
   

    
    
}
