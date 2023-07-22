package DAY1;

import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım:driver i kaydet
        Class.forName("org.postgresql.Driver");  //java 7 ile birlikte bunu yazmaya gerek yok
        //2.Adım :Database e baglanma
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        //3.Adım : Statement olusturma
        Statement st=con.createStatement();
        //System.out.println("connection success");
        //4.Adım : query(sorgu) olusturma
        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.
     // boolean sql1= st.execute("create table workers(worker_id int,worker_name varchar(50),salary real)");
      // System.out.println("sql 1 : "+sql1);
        //execute DDL(Data Definition Language) veya DQL(Data Query Language)
        //DQL icin kullanilirsa ResultSet nesnesi alirsa true almazsa false doner
        //DDL için kullanılırsa false doner
        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.

       // String query2 = "Alter table workers add column city varchar(20)";
     //  st.execute(query2);

        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        String query3 = "drop table workers";
        st.execute(query3);

        //5.Adim : baglantiyi ve statement i kapatma
        st.close();
        con.close();





    }
}
