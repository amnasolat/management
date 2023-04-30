package com.springboot.management.repository;

import com.springboot.management.entity.Address;
import com.springboot.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
//    Address findByName(String city);
//facing error. use correct name like findByCity because city is our attribute and name must be same
    Address findByCity(String city);
    //it won't work
//    List<Address> findByName(String name);
    @Query("select a from Address a where (a.isDeleted =false or a.isDeleted is null) and a.addressId=:addressId")
    Address findByIdNotDeleted(@Param("addressId") Integer addressId);
    @Query("select a from Address a where a.isDeleted=false or a.isDeleted is null ")
    List<Address> findAllNotDeleted();
}
