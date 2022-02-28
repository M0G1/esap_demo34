package com.example.esap_demo4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DBChange {
    public enum Operation {INSERT, UPDATE, DELETE}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Operation operation;      // type of operation
    private Long entityId;            // id in table
    private String type;              //
    private String oldEntityString;
    private String newEntityString;

    public DBChange(){};

    public DBChange(Operation operation, Object oldEntity, Object newEntity, Long entityId) { // , String type
        this.type =  oldEntity != null ? oldEntity.getClass().getSimpleName() : newEntity.getClass().getSimpleName();
//        type;  //
//        var temp =
        this.operation = operation;
        this.entityId = entityId;
        this.oldEntityString = oldEntity != null ? oldEntity.toString() : null;
        this.newEntityString = newEntity != null ? newEntity.toString() : null;
    }
}