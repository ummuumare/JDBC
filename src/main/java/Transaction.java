import java.sql.*;
/*
Transaction: Db deki atomik(parcalanamaz) en kucuk islem
islemler basarili bir sekilde gerceklesirse transaction commit edilir
en az bir asamasinda problem olursa rollback ile islemler iptal edilir
 */

public class Transaction {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();
        con.setAutoCommit(false);

        try {
            //hesap 1234 ten hesap no:5678 e 1000 para transfer olsun
            String sql = "update hesaplar set bakiye = bakiye + ? where hesap_no =?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setDouble(1, -1000);
            prst.setInt(2, 1234);
            prst.executeUpdate();
//            if (true) {
//                throw new Exception();
//            }
            prst.setDouble(1, 1000);
            prst.setInt(2, 5678);
            prst.executeUpdate();
            prst.close();
            con.commit();//
            con.close();
        } catch(Exception e) {
            con.rollback(); //bir hata olursa izlemleri iptal et basa don
        }
    }
}