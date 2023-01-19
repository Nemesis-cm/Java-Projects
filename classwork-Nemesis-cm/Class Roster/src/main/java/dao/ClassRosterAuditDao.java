package dao;


package com.sg.classroster.dao;

public interface ClassRosterAuditDao {

    public void writeAuditEntry(String entry) throws
            ClassRosterPersistenceException;
}