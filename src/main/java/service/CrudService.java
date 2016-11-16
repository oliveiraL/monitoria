package service;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.CrudDao;
import exception.NegocioException;

public class CrudService<T> {
	@Inject
	private CrudDao<T> crudDao;
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(T obj) throws NegocioException {
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
	public List<T> listar(Class<T> typeClass) {
		return crudDao.listar(typeClass);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T finByID(int id, Class<T> typeClass) {
		return crudDao.finByID(id,typeClass);
	}
}
