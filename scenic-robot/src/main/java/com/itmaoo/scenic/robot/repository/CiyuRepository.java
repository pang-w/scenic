package com.itmaoo.scenic.robot.repository;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itmaoo.scenic.robot.entity.po.Ciyu;
@Repository
@Table(name="zd_ciyu")
@Qualifier("ciyuRepository")
public interface CiyuRepository extends CrudRepository<Ciyu, Integer > {

	public Ciyu findOne(Integer id);

    public Ciyu save(Ciyu u);

    @Query("select t from Ciyu t where t.ciyu=:ciyu")
    public Ciyu findUserByCiyu(@Param("ciyu") String ciyu);
    
    @Query("select t from Ciyu t where t.ciyu like %:ciyukey%")
    public List<Ciyu> findByCiyuLike(@Param("ciyukey") String ciyukey);

}
