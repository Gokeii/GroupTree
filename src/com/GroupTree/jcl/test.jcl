//S992024A JOB NOTIFY=&SYSUID                                           00010000
//CALLREXX EXEC PGM=IKJEFT01                                            00020000
//SYSEXEC DD DSN='S992025.REXX',DISP=SHR                                00030000
//SYSTSPRT DD SYSOUT=*,HOLD=YES                                         00040000
//SYSTSIN DD *                                                          00050000
%REPORT                                                                 00060000
/*                                                                      00070000
