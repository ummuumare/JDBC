package DAY1;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        //Database e baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        //St`atement olusturma // db ye iletim yapmak ve query calistirmasi icin olusturulur
        Statement st = con.createStatement();

        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String query1 = " select country_name from countries where id  between 5 and 10";
        boolean sql1 = st.execute(query1);
        System.out.println("sql1 : " + sql1);

        //kayitlari gormek icin executeQuery() kullanmaliyizz
        ResultSet resultset = st.executeQuery(query1);
        //       resultset.next();
        //        System.out.println(resultset.getString(1));
        //Resultset in first(),next() gibi methodlari vardir
        //Resultset in geriye donusu yoktur
        while (resultset.next()) {
            System.out.println("ulke ismi : " + resultset.getString("country_name"));
        }
        System.out.println("-----------------Ornek2----------------------");
        //ÖRNEK 2: phone_code'u 550 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini
        String query2 = "select phone_code,country_name from countries WHERE phone_code>550";
        ResultSet resultSet2 = st.executeQuery(query2);
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("phone_code") + "-" + resultSet2.getString("country_name"));
        }
        System.out.println("------------Ornek3-------------------");
        //ÖRNEK 3:developers tablosunda "salary" değeri en düşük salary olan developerların tüm bilgilerini gösteriniz

        String query3 = " select * from developers where salary in (select min(salary) from developers)";
        ResultSet resultSet3 = st.executeQuery(query3);
        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt("id") + " - " + resultSet3.getString("name") + " - " +
                    resultSet3.getInt("salary") + " - " + resultSet3.getString("prog_lang"));
        }
        System.out.println("------------ÖRNEK4-------------");
        //ÖRNEK 4:Puanı bölümlerin taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz

        String query4 = " select isim, puan from ogrenciler where puan > (select avg(taban_puani) from bolumler )";
        ResultSet resultSet4 = st.executeQuery(query4);
        while (resultSet4.next()) {
            System.out.println(resultSet4.getString("isim") + " - " + resultSet4.getInt("puan"));
        }

//        String query4="SELECT isim, puan FROM ogrenciler WHERE bolum \n" +
//                "IN(SELECT bolum FROM ogrenciler WHERE puan>\n" +
//                "(SELECT AVG(taban_puani) FROM bolumler))";
//        ResultSet resultSet4= st.executeQuery(query4);
//        while (resultSet4.next()){
//            System.out.println(resultSet4.getString("isim")+ " ** " +resultSet4.getInt("puan"));
//        }

        // st.close();;
        // con.close();


    }
}