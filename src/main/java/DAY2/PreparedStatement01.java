package DAY2;/*
PreparedStatement Statement interface ini extend eder
PreparedStatement interface, birden çok kez çalıştırılabilen önceden derlenmiş bir SQL kodunu temsil eder.
Paremetrelendirilmiş SQL sorguları(query) ile çalışır.
 Bur sorguyu 0 veya daha fazla parametre ile kullanabiliriz.
 */

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();


        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puanı'nı
// 475 olarak güncelleyiniz.
//        String sql = "update bolumler set taban_puani = 475 where bolum ilike 'Matematik'";
//        st.executeUpdate(sql);

        //preparedstatement icin parametreli query yazalim
        String sql = "update bolumler set taban_puani =? where bolum ilike ?";
        //preparedstatement olustur
        PreparedStatement prst = con.prepareStatement(sql);
        //parametre degerlerini gir
        prst.setInt(1, 475);
        prst.setString(2, "matematik");
        //preparedstatement ile query i calistir
        int updated = prst.executeUpdate();
      //  eger tabloyu tekrar yazdirmak istersek
        ResultSet rs = st.executeQuery("select * from bolumler");
        while (rs.next()) {
            System.out.printf("%-6s %-20s %-6d\n", rs.getInt("bolum_id"), rs.getString("bolum"), rs.getInt("taban_puani"));
        }
        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün
        // taban_puanı'nı 455 olarak güncelleyiniz.
       // tekrar olusturmama gerek yok sadece parametreleri girmek yeterli

        prst.setInt(1, 455);
        prst.setString(2, "edebiyat");
        int updated2 = prst.executeUpdate();
        System.out.println("updated2 : " + updated2);

        prst.close();
        st.close();
        con.close();
    }
}