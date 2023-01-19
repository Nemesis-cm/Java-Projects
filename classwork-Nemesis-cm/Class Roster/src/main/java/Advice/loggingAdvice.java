package Advice;


import classroster.dao.ClassRosterAuditDao;
import classroster.dao.ClassRosterPersistenceException;
import aspectj.lang.JoinPoint;


public class loggingAdvice {
    ClassRosterAuditDao auditDao;

    public loggingAdvice(ClassRosterAuditDao audit){
        this.auditDao = audit;
    }

    public void createAuditEntry(JoinPoint jp){
        Object[] args = jp.getArgs();

        String auditEntry = jp.getSignature().getName() + ": ";

        for (Object currentArg: args){
            auditEntry += currentArg;
        }

        try{
            auditDao.writeAuditEntry(auditEntry);
        } catch (ClassRosterPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }


    }
}
