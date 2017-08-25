package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Actor;
import com.example.demo.domain.entity.ActorPrefecture;

/**
 * Repository class for actor table data.
 * <p>
 * This repository class made by doma gen.
 */
@Dao
@ConfigAutowireable
public interface ActorRepository {

	/**
	 * Select actor data.
	 * <p>
	 * Select actor data by parameter's id.
	 *
	 * @param id
	 *            actor's id
	 * @return {@link Actor}
	 */
	@Select
	Optional<ActorPrefecture> selectById(Integer id);

	/**
	 * Select all actor table data.
	 *
	 * @return {@link ActorPrefecture}
	 */
	@Select
	List<ActorPrefecture> selectAll();

	/**
	 * Select all actor data based on search key word.
	 * <p>
	 * Select actor data by parameter's name.
	 *
	 * @param name
	 * @return {@link ActorPrefecture}
	 */
	@Select
	List<ActorPrefecture> selectAllByName(String name);

	/**
	 * Insert actor data.
	 * <p>
	 * Insert acotr data with parameter's entity.
	 *
	 * @param entity
	 *            {@link Actor}
	 * @return {@link Actor}
	 */
	@Insert
	@Transactional
	Result<Actor> insert(Actor entity);

	/**
	 * Delete actor data.
	 * <p>
	 * Delete acotr data with parameter's entity.
	 *
	 * @param entity
	 *            {@link Actor}
	 * @return {@link Actor}
	 */
	@Delete(sqlFile = true)
	@Transactional
	Result<Actor> delete(Actor entity);

}