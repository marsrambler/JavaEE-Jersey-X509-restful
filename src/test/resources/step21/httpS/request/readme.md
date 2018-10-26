. deploy the war into tomcat, run cert.ksh to get cerficates, and copy them in tomcat & curl
. just run below:

curl -v -k https://localhost:8443/restful/reqrest/demo/hello 
		(FAILED, as clientAuth="true" configurated in tomcat)
		
curl -v --cert client.pem https://localhost:8443/restful/reqrest/demo/hello
		(FAILED, as no ca certifact provided)
		
curl -v --cert client.pem --cacert ca.crt https://localhost:8443/restful/reqrest/demo/hello
		(SUCCESSFUL)

curl -v -k --cert client.pem -k https://localhost:8443/restful/reqrest/demo/hello
		(SUCCESSFUL, as -k will ignore the client's verification)

the https request would works.

the client & server has a common CA certificate.