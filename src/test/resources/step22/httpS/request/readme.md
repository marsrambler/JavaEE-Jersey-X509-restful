. deploy the war into tomcat, run cert_self.ksh to get cerficates, and copy them in tomcat & curl
. just run below:

curl -v -k https://localhost:8443/restful/reqrest/demo/hello 
		(FAILED, as clientAuth="true" configurated in tomcat)
		
curl -v --cert localhost_client.pfx.pem https://localhost:8443/restful/reqrest/demo/hello
		(FAILED, as no ca certifact provided)
		
curl -v --cert localhost_client.pfx.pem --cacert localhost_pubkey.pem https://localhost:8443/restful/reqrest/demo/hello
		(SUCCESSFUL)

curl -v -k --cert localhost_client.pfx.pem https://localhost:8443/restful/reqrest/demo/hello
		(SUCCESSFUL, as -k will ignore the client's verification)

the https request would works.

the client & server has a self-signed certificate.