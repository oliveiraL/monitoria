package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.CrudDao;

@Stateless
public class GenericService<T> {
	@Inject
	private CrudDao<T> crudDao;
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(T obj) {
		crudDao.salvar(obj);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(T obj) {
		crudDao.atualizar(obj);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(T obj) {
		crudDao.remover(obj);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return crudDao.listar();
	}
}
