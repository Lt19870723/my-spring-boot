package com.myproject.myindex.queue.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;

public interface QueueRepo extends JpaRepository<Queue, String> {

    @Query("from Queue q where q.queueType = :queueType and dataType=:dataType and status in(:statusList) and expectTime<:expectTime")
    public List<Queue> selectQueue(@Param("queueType") String queueType, @Param("dataType") String dataType, @Param("statusList") List<String> statusList, @Param("expectTime") Date expectTime);

    @Query("from Queue q where  q.status in(:statusList) and q.expectTime<:expectTime")
    public List<Queue> selectQueue(@Param("statusList") List<QueueStatusType> statusList, @Param("expectTime") Date expectTime);

}