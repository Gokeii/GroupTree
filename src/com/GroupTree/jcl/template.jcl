//usernameZ JOB NOTIFY=&SYSUID                  
//CALLREXX EXEC PGM=IKJEFT01                   
//SYSEXEC DD *                                 
  /* REXX EXEC TO PRINT COMMAND OUTPUT */             
  PARSE ARG COMMAND                            
  O = OUTTRAP("OUTPUT.",,"CONCAT")             
  ADDRESS TSO COMMAND                          
  O = OUTTRAP(OFF)                             
  DO I=1 TO OUTPUT.0                           
  SAY OUTPUT.I                                 
  END                                          
  RETURN                                       
//SYSTSPRT DD SYSOUT=*,HOLD=YES                
//SYSTSIN DD *                                 
  command                                           
/*