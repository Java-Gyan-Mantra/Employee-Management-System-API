package com.spring.boot.curd.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.boot.curd.app.pojo.Employee;

@RepositoryRestResource
public interface EMSDao extends CrudRepository<Employee, Integer> {
	/**
	 * @author BASANTA This is the easy way to connect with our own MYSQL DB ,
	 *         But using this approach we can only perform standard CURD
	 *         operation <SAVE>,<DELETE>,<RETRIVE> and all specific to ID which
	 *         i passed as generics in above.Suppose i want to fetch all
	 *         employee whose department is XYZ then this we can't achieve here
	 *         
	 *         See below,
	 *                        Entity   Primery_Key
	 *                           |        |
	 *         CrudRepository<Employee, Integer>
	 *         
	 *         Every operation Related to Primery_key only possible
	 *         
	 *
	 */
}
