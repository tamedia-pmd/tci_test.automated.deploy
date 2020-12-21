Shared Module Workday Error Codes
ErrorCode;ErrorMessage;ErrorType;CreateCase;CircuitBreaker;HTTPStatusCode;APIname;BSLProcess
BDOC000003;Authentication Error in Workday.;System;false;;;BillingDoc
BDOC000004;Error invoking the validation of the worktag in Workday.;System;false;false;;BillingDoc
BDOC000005;Authentication Error in SwissBilling;System;false;;;BillingDoc
BDOC000014;Generic Technical error during the invoking to Workday;System;false;false;;BillingDoc
BDOC000014;Generic Technical error during the invoking to Workday;System;false;false;;BillingDoc


EHWD000001;System error occured invoking the Async Processing Messages service-Workday;System;false;false;500;;
EHWD000002;Generic error occured invoking the Async Processing Messages service-Workday;System;false;false;500;;

BACL000001;[ERROR] System Exception time out inside BankAccountList process ;System;false;false;;
BACL000002;[ERROR] System Exception Http Client Exception inside BankAccountList process;System;false;false;;
BACL000003;[ERROR] System exception error in http communication inside BankAccountList process;System;false;false;;
BACL000004;[ERROR] System Exception Http Server Exception inside BankAccountList process;System;false;false;;
BACL000005;[ERROR] Unknow error inside BankAccountList process;Business;false;false;;
BACL000006;[ERROR  Business Error inside BankAccountList process;Business;false;false;;
BACL000007;[ERROR] Business error occured parsing the json string response received from Workday API BankAccountList call;Business;false;false;;