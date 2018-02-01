package com.itmaoo.scenic.robot.repository;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itmaoo.scenic.robot.entity.po.Chengyu;
@Repository
@Table(name="zd_chengyu")
@Qualifier("chengyuRepository")
public interface ChengyuRepository extends CrudRepository<Chengyu, Integer > {

	public Chengyu findOne(Integer id);

    public Chengyu save(Chengyu u);

    @Query("select t from Chengyu t where t.chengyu=:chengyu")
    public Chengyu findUserByChengyu(@Param("chengyu") String chengyu);
    
    @Query("select t from Chengyu t where t.chengyu like %:chengyukey%")
    public List<Chengyu> findByChengyuLike(@Param("chengyukey") String chengyukey);

}
