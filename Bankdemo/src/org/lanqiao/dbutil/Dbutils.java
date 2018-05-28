package org.lanqiao.dbutil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Dbutils {
	//����ThreadLocal�������ڴ�� Connection����
	private static ThreadLocal<Connection> threadlocal=new ThreadLocal<Connection>();
	//��������Դ����
	private static ComboPooledDataSource ds =new ComboPooledDataSource("lanqiao");
	
	public static DataSource getDataSource(){
		
		  return ds ; 
		 }
	//��c3p0���ӳ��л�ȡConnection����
	public static Connection getConnection() {
		Connection conn =threadlocal.get();
		if(conn ==null) {
			try {
				conn=ds.getConnection();
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				threadlocal.set(conn);
			}
		}
		
		return conn;
	}
	//��������
	public static void beginTransaction() {
		Connection conn=getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ύ����
	public static void commitTransaction() {
		Connection conn=threadlocal.get();
		if(conn !=null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�ع�����]
	public static void rollbackTransaction() {
		Connection conn =threadlocal.get();
		if(conn !=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�ر�����
	public static void close() {
		Connection conn =threadlocal.get();
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				threadlocal.remove();
			}
		}
	}
	//

}
