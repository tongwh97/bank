package org.lanqiao.dao;

import java.sql.SQLException;

import org.lanqiao.entity.Account;

public interface IAccountDao {
	//����������ѯ�û�
	public abstract Account queryAccountByName(String name)throws SQLException;
	//�޸��˻����������������
	public abstract void updateAccount(Account account)throws SQLException;

}
