import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.

        String sql = " select isim, taban_puan from bolumler order by taban_puani desc offset 1 limit 1 ";
        ResultSet resultset = st.executeQuery(sql);
        while (resultset.next()) {
            System.out.println(resultset.getString("isim") + "--" + resultset.getInt("taban_puani"));
        }
        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        String sql2 = "select bolum,kampus (select max(puan) from ogrenciler o where o.bolum = b.bolum) max_puan from bolumler	b";
        ResultSet resultset2 = st.executeQuery(sql2);
        while (resultset2.next()) {
            System.out.println(resultset2.getString("bolum") + "--" + resultset2.getString("kampus") + "--" + resultset2.getInt("max_puan"));
        }
        st.close();
        con.close();

        /*
        String sql2 = "select bolum,kampus,(select max(puan) from ogrenciler o where o.bolum=b.bolum) max_puan " +
        "from bolumler b";
ResultSet rs2 = st.executeQuery(sql2);
while (rs2.next()) {
    System.out.println(rs2.getString("bolum") + "--" + rs2.getString("kampus") + "--" + rs2.getInt("max_puan"));
}

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();
        System.out.println("----------------ÖRNEK 1--------------------------");
        //ÖRNEK1:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.
        String sql = "select bolum,taban_puani from bolumler order by taban_puani desc  offset 1 limit 1";
        ResultSet resultset = st.executeQuery(sql);
        while (resultset.next()) {
            System.out.println(resultset.getString("bolum") + "--" + resultset.getInt("taban_puani"));
        }
        //subquery ile cozulmuz olsa
        String sql1 = "SELECT bolum,taban_puanı FROM bolumler WHERE taban_puani=" +
                "(SELECT max(taban_puani) FROM bolumler WHERE taban_puani<(SELECT max(taban_puani) FROM bolumler))";
        System.out.println("----------------ÖRNEK 2--------------------------");
        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve
        //her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        String sql2 = "select bolum,kampus,(select max(puan) from ogrenciler o where o.bolum=b.bolum) max_puan " +
                "from bolumler b";
        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString("bolum") + "--" + rs2.getString("kampus") + "--" + rs2.getInt("max_puan"));
        }
        st.close();
        con.close();
    }
}
         */
    }


}
