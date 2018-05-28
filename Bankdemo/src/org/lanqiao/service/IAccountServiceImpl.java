package org.lanqiao.service;

import java.sql.SQLException;

import org.lanqiao.dao.AccountDaoImpl;
import org.lanqiao.dao.IAccountDao;
import org.lanqiao.dbutil.Dbutils;
import org.lanqiao.entity.Account;

public class IAccountServiceImpl  implements IAccountService{

	@Override
	public void transfer(String fromName, String toName, double transferMoney){
		try {
			//��������
		Dbutils.beginTransaction();
		IAccountDao dao=new AccountDaoImpl();
		//�տ
			Account toAccount = dao.queryAccountByName(toName);
			//���
			Account fromAccount=dao.queryAccountByName(fromName);
			//ת��
			if(transferMoney<fromAccount.getBalance()) {
				//���������
				double fromBalance=fromAccount.getBalance()-transferMoney;
				fromAccount.setBalance(fromBalance);
				//�տ�������
				double toBalance=toAccount.getBalance()+transferMoney;
				toAccount.setBalance(toBalance);
				//�����˻�
				dao.updateAccount(fromAccount);
				dao.updateAccount(toAccount);
				System.out.println("ת�˳ɹ�");
				//�ύ����
				Dbutils.commitTransaction();
				System.out.println("�ύ�ɹ�");
				
				
			}else {
				System.out.println("���㣬ת��ʧ��");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("�ύʧ�ܣ��ع�...");
			Dbutils.rollbackTransaction();
			e.printStackTrace();
		}finally {
			Dbutils.close();
		}
	
		
		
	}
	

}
