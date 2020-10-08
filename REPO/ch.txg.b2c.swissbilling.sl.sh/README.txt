ErrorCode;ErrorMessage;ErrorType;CreateCase;CircuitBreaker;HTTPStatusCode;APIname
BDOC000005;Authentication Error in SwissBilling;System;TRUE;;;
BDOC000006;Technical Error invoking Swissbilling;System;TRUE;TRUE;;
BDOC000007;Validation Error invoking Swissbilling;Business;TRUE;;;
BDLS000002;Business Error invoking Swissbilling - getBillingDocList;Business;false;;400;
BDLS000003;System Error invoking Swissbilling - getBillingDocList;System;false;;500;
BDLS000004;Generic Error invoking Swissbilling - getBillingDocList;System;false;;500;
BENG000001;System Error occured invoking Swissbilling - BillingEngine;System;false;false;404;
BENG000002;Business Error occured invoking Swissbilling - BillingEngine;Business;false;false;400;
BENG000003;System Error Invoking Swissbilling. HTTP Request timed out;System;false;false;500;
BENG000004;Generic System Error Invoking Swissbilling - Billing Engine;System;false;false;;
BENG000005;Server System Error Invoking Swissbilling - Billing Engine;System;false;false;500;
AUTH000001;System error inside the Authorization Manager;System;false;;;
AUTH000002;System error calling Get Authorization Configuration;System;false;;;
SWSS000008;[ERROR]  Business Error inside PUTPostingDocsByID subprocess;Business;TRUE;FALSE;;
SWSS000009;[ERROR]  System exception error in http communication inside PUTPostingDocsByID subprocess;System;TRUE;FALSE;;
SWSS000010;[ERROR]  System Exception Http Server Exception inside PUTPostingDocsByID subprocess;System;TRUE;FALSE;;
SWSS000011;[ERROR]  Business Error JSON PARSE inside PUTPostingDocsByID subprocess;Business;TRUE;FALSE;;
SWSS000012;[ERROR]  Unknow error inside PUTPostingDocsByID subprocess;Business;TRUE;FALSE;;