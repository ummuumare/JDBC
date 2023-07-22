package DAY2;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:developers tablosunda maaşı ortalama maaştan az olanların maaşını ortalama maaş ile güncelleyiniz,

        String sql = "update developers set salary =(select avg(salary) from developers) where salary<(select avg(salary) from developers)";
        int updated = st.executeUpdate(sql); // update edilen sayiyi gosterir
        System.out.println("Guncellenen kayit sayisi : " + updated);

        ResultSet resultset = st.executeQuery("select id,name,salary from developers");
        while (resultset.next()) {
            System.out.println(resultset.getInt("id") + "--" + resultset.getString("name") + " --" + resultset.getInt("salary"));
        }

        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
        String sql2 = "insert into developers(name,salary,prog_lang) values ('ummu umare', 5000 , 'java')";
        int inserted = st.executeUpdate(sql2);
        System.out.println("Eklenen developer :" + inserted);

        //ÖRNEK3:developers tablosundan id'si 11 olanı siliniz.
        String sql3 = "delete from developers where id =12";
        int deleted = st.executeUpdate(sql3);
        System.out.println("terfi eden developer sayisi : " + deleted);


        //ÖRNEK3:developers tablosundan prog_lang css olanı siliniz.
        String sql4 = "delete from developers where prog_lang  ilike 'css'";
        int deleted2 = st.executeUpdate(sql3);
        System.out.println("deleted rows :" + deleted2);
st.close();
con.close();
    }

}
