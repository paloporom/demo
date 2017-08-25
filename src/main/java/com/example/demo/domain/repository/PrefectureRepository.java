package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.Prefecture;

/**
 */
@Dao
@ConfigAutowireable
public interface PrefectureRepository {

	/**
	 * @param id
	 * @return the Prefecture entity
	 */
	@Select
	Optional<Prefecture> selectById(Short id);

	@Select
	List<Prefecture> selectAll();

	// /**
	// * @param entity
	// * @return affected rows
	// */
	// @Insert
	// int insert(Prefecture entity);
	//
	// /**
	// * @param entity
	// * @return affected rows
	// */
	// @Update
	// int update(Prefecture entity);
	//
	// /**
	// * @param entity
	// * @return affected rows
	// */
	// @Delete
	// int delete(Prefecture entity);
}